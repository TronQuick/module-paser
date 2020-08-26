package com.tron.modulepaser.service.impl;

import com.tron.modulepaser.controller.response.ResultCode;
import com.tron.modulepaser.entity.ModuleInfo;
import com.tron.modulepaser.controller.response.Result;
import com.tron.modulepaser.repository.ModuleInfoRepository;
import com.tron.modulepaser.service.ModuleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Title:       ModuleInfoServiceImpl
 * Description:  模块信息表服务类Impl
 *
 */
@Service
public class ModuleInfoServiceImpl implements ModuleInfoService {

    @Autowired
    private ModuleInfoRepository moduleInfoRepository;

    /**
     * 查询指定模块名，指定版本号的模块信息表记录
     *
     * @param artifactid         模块名
     * @param version            模块版本号
     * @return
     */
    @Override
    public List<ModuleInfo> findByArtifactidAndVersion(String artifactid, String version) {
        return moduleInfoRepository.findByArtifactidAndVersion(artifactid,version);
    }

    /**
     * 新增
     * @param moduleInfo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result save(ModuleInfo moduleInfo) throws Exception {
        moduleInfo.setId(null);
        moduleInfoRepository.save(moduleInfo);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 查询指定主键id的记录是否存在
     * @param id
     * @return
     */
    @Override
    public Result getIsExistById(long id) {
        return new Result(ResultCode.SUCCESS,moduleInfoRepository.existsById(id));
    }

    /**
     * 查询指定id的记录
     * @param id
     * @return
     */
    @Override
    public ModuleInfo findById(long id) {
        getIsExistById(id);
        ModuleInfo moduleInfo = moduleInfoRepository.findById(id);
        return moduleInfo;
    }
}
