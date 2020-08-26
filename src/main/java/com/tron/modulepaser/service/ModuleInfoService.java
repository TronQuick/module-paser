package com.tron.modulepaser.service;

import com.tron.modulepaser.entity.ModuleInfo;
import com.tron.modulepaser.controller.response.Result;

import java.util.List;

/**
 * Title:       ModuleInfoService
 * Description:  模块信息表服务类
 *
 */
public interface ModuleInfoService {

    /**
     * 查询指定模块名，指定版本号的模块信息表记录
     *
     * @param artifactid         模块名
     * @param version            模块版本号
     * @return List<ModuleInfo>
     */
    List<ModuleInfo> findByArtifactidAndVersion(String artifactid, String version);

    /**
     * 新增
     * @param moduleInfo
     * @return
     */
    Result save(ModuleInfo moduleInfo) throws Exception;

    /**
     * 查询指定主键id的记录是否存在
     * @param id
     * @return
     */
    Result getIsExistById(long id);


    /**
     * 查询指定id的记录
     * @param id
     * @return
     */
    ModuleInfo findById(long id);
}
