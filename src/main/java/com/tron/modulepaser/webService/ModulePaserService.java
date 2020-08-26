package com.tron.modulepaser.webService;

import com.tron.modulepaser.controller.arg.FindByLevelArg;
import com.tron.modulepaser.controller.arg.FindUpModulesArg;
import com.tron.modulepaser.controller.arg.ScanPomArg;
import com.tron.modulepaser.entity.ModuleInfo;
import com.tron.modulepaser.entity.ModuleRelation;
import com.tron.modulepaser.controller.response.Result;
import com.tron.modulepaser.controller.response.ResultCode;
import com.tron.modulepaser.service.ModuleInfoService;
import com.tron.modulepaser.service.ModuleRelationService;
import com.tron.modulepaser.utils.PomDom4j;
import com.tron.modulepaser.utils.SearchPomPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Title:       ModulePaserService
 * Description: service服务类
 */

@Service
public class ModulePaserService {

    @Autowired
    private ModuleInfoService moduleInfoService;

    @Autowired
    private ModuleRelationService moduleRelationService;

    /**
     *   1.扫描模块信息与模块依赖关系
     */
    public Result scanPom(ScanPomArg scanPomArg) {
        // 获取项目路径
        String projectPath = scanPomArg.getProjectPath();

        /*
         *  获得所有pom.xml文件的路径
         */
        // 导入目录
        File folder = new File(projectPath);

        // 如果文件夹不存在
        if (!folder.exists()) {
            System.out.println("目录不存在：" + folder.getAbsolutePath());
            return new Result(ResultCode.WARN,"项目目录不存在");
        }
        // 调用方法获得文件数组
        File[] pomXmls = SearchPomPath.searchFile(folder);
        // 控制台输出结果
        System.out.println("在 " + folder + " 以及所有子文件下查找对象" + "pom.xml");
        System.out.println("共找到 " + pomXmls.length + " 个符合条件的文件：");

        // 循环显示搜索所得的所有pom.xml文件路径
        for (int i = 0; i < pomXmls.length; i++) {
            System.out.println(pomXmls[i].getAbsolutePath() + "");
        }

        /*      遍历所有pom.xml文件
         *       -【模块信息】储存到【module_info】表中
         *       -【模块依赖关系】储存到【module_dependency_relation】表中
         */

        // 遍历每个pom.xml
        for (int i = 0; i < pomXmls.length; i++) {

            String pomXmlPath = pomXmls[i].getAbsolutePath() + "";

            System.out.println("正在处理：" + pomXmlPath); // 显示正在处理的pom.xml路径

            try {

                // 获取选中模块的模块信息 selectedModuleInfo
                ModuleInfo selectedModuleInfo = PomDom4j.getSelectedModuleInfo(pomXmlPath);

                // 搜索表中是否已有selectedModuleInfo的记录，若不存在则新增记录
                List<ModuleInfo> selectedModuleInfoFlag = moduleInfoService.findByArtifactidAndVersion(selectedModuleInfo.getArtifactid(), selectedModuleInfo.getVersion());
                if (selectedModuleInfoFlag.size() == 0) {
                    moduleInfoService.save(selectedModuleInfo);
                }
                Long selectedModuleInfoId = moduleInfoService.findByArtifactidAndVersion(selectedModuleInfo.getArtifactid(), selectedModuleInfo.getVersion()).get(0).getId();
                // 获取选中模块所依赖的模块 dependentModuleInfos
                List<ModuleInfo> dependentModuleInfos;
                dependentModuleInfos = PomDom4j.getDependencies(pomXmlPath);
                for (ModuleInfo dependentModuleInfo : dependentModuleInfos) {
                    // 搜索是否已有记录，若不存在则save()
                    List<ModuleInfo> dependentModuleInfoFlag = moduleInfoService.findByArtifactidAndVersion(dependentModuleInfo.getArtifactid(), dependentModuleInfo.getVersion());
                    if (dependentModuleInfoFlag.size() == 0) {
                        moduleInfoService.save(dependentModuleInfo);
                    }
                    Long dependentModuleInfoId = moduleInfoService.findByArtifactidAndVersion(dependentModuleInfo.getArtifactid(), dependentModuleInfo.getVersion()).get(0).getId();
                    ModuleRelation moduleRelation = new ModuleRelation();
                    moduleRelation.setSelectedModuleInfoId(selectedModuleInfoId);
                    moduleRelation.setDependentModuleInfoId(dependentModuleInfoId);
                    // 搜索是否已有记录，若不存在则save()
                    List<ModuleRelation> relationFlag = moduleRelationService.findBySelectedModuleInfoIdAndDependentModuleInfoId(moduleRelation.getSelectedModuleInfoId(), moduleRelation.getDependentModuleInfoId());
                    if (relationFlag.size() == 0) {
                        moduleRelationService.save(moduleRelation);
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new Result(ResultCode.SUCCESS);
    }


    /**
     * 2.根据指定模块名称、版本号获取上级依赖层级总级数
     */
    public Result getLevel(FindUpModulesArg findUpModulesArg) {

        // 根据 Artifactid，Version 获取模块信息表记录，取得主键id
        ModuleInfo indexModuleInfo = moduleInfoService.findByArtifactidAndVersion(findUpModulesArg.getArtifactid(), findUpModulesArg.getVersion()).get(0);
        Long indexModuleInfoId = indexModuleInfo.getId();

        // 根据id递归查询所有上级依赖关系
        List<ModuleRelation> relations = moduleRelationService.findDependenciesByDependentModuleInfoId(indexModuleInfoId);

        // 计算层数
        Integer level = countLevel(indexModuleInfoId, relations);
        return new Result(ResultCode.SUCCESS,level);
    }

    /*
     *  3.根据指定模块名称，版本号查询所有依赖此模块的模块
     */
    public Result findUpModules(FindUpModulesArg findUpModulesArg) {

        // 根据 Artifactid，Version 获取模块信息表记录，取得主键id
        ModuleInfo indexModuleInfo = moduleInfoService.findByArtifactidAndVersion(findUpModulesArg.getArtifactid(), findUpModulesArg.getVersion()).get(0);
        Long indexModuleInfoId = indexModuleInfo.getId();

        // 根据id递归查询所有上级依赖关系
        List<ModuleRelation> relations = moduleRelationService.findDependenciesByDependentModuleInfoId(indexModuleInfoId);

        // 取出上级依赖id,根据id遍历模块信息表，查询返回List<ModuleInfo>
        List<ModuleInfo> upModules = new ArrayList<>();
        for (ModuleRelation relation : relations) {
            ModuleInfo moduleInfo = moduleInfoService.findById(relation.getSelectedModuleInfoId());

            // 遍历比较有没已经存在的模块
            boolean existFlag = true;
            for (ModuleInfo upModule : upModules) {
                if(moduleInfo.getId() == upModule.getId()){
                    existFlag = false;
                }
            }
            if (existFlag) {
                upModules.add(moduleInfo);
            }
        }

        return new Result(ResultCode.SUCCESS,upModules);
    }

    /**
     * 4.根据指定模块名称、版本号、上级依赖层级级数，获取依赖此模块的模块
     */
    public Result findByLevel(FindByLevelArg findByLevelArg) {

        // 根据 Artifactid，Version 获取模块信息表记录，取得主键id
        ModuleInfo indexModuleInfo = moduleInfoService.findByArtifactidAndVersion(findByLevelArg.getArtifactid(), findByLevelArg.getVersion()).get(0);
        Long indexModuleInfoId = indexModuleInfo.getId();

        // 根据id递归查询所有上级依赖关系
        List<ModuleRelation> relations = moduleRelationService.findDependenciesByDependentModuleInfoId(indexModuleInfoId);

        // 调用递归方法
        List<Long> ids = findIdByLevel(indexModuleInfoId, relations, findByLevelArg.getLevel(), 1);

        List<ModuleInfo> upModules = new ArrayList<>();
        for (Long id : ids) {
            ModuleInfo moduleInfo = moduleInfoService.findById(id);

            // 遍历比较有没已经存在的模块
            boolean existFlag = true;
            for (ModuleInfo upModule : upModules) {
                if(moduleInfo.getId() == upModule.getId()){
                    existFlag = false;
                }
            }
            if (existFlag) {
                upModules.add(moduleInfo);
            }
        }

        return new Result(ResultCode.SUCCESS,upModules);
    }



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*
     *   递归计算依赖层级总级数
     * */
    public Integer countLevel(Long indexId, List<ModuleRelation> relations) {
        Integer level = null;
        List<Long> selectedModuleIds = new ArrayList<>();

        for (ModuleRelation relation : relations) {
            if (relation.getDependentModuleInfoId() == indexId) {
                selectedModuleIds.add(relation.getSelectedModuleInfoId());
            }
        }

        if (selectedModuleIds.size() == 0) {
            level = 0;
        } else {
            List<Integer> levels = new ArrayList<>();
            for (Long id : selectedModuleIds) {
                // 递归累加level
                Integer item = countLevel(id, relations);
                levels.add(item);
            }
            Collections.sort(levels);
            level = levels.get(levels.size()-1) + 1;
        }
        return level;
    }

    /**
     * 按层级递归获取上级依赖的模块信息表主键id
     */
    public List<Long> findIdByLevel(Long indexId, List<ModuleRelation> relations, Integer level,Integer idxLevel) {

        List<Long> selectedModuleIds = new ArrayList<>();

        for (ModuleRelation relation : relations) {
            if (relation.getDependentModuleInfoId() == indexId) {
                selectedModuleIds.add(relation.getSelectedModuleInfoId());
            }
        }

        List<Long> result = new ArrayList<>();


        if (idxLevel != level) {
            for (Long selectedModuleId : selectedModuleIds) {
                List<Long> ids= findIdByLevel(selectedModuleId, relations, level, idxLevel + 1);
                if (ids.size() != 0) {
                    for (Long id : ids) {
                        result.add(id);
                    }
                }
            }
        } else {
            if (selectedModuleIds.size() != 0) {
                for (Long id : selectedModuleIds) {
                    result.add(id);
                }
            }
        }

        return result;

    }


}
