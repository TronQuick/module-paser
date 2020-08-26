package com.tron.modulepaser.service.impl;

import com.tron.modulepaser.controller.response.ResultCode;
import com.tron.modulepaser.entity.ModuleRelation;
import com.tron.modulepaser.controller.response.Result;
import com.tron.modulepaser.repository.ModuleRelationRepository;
import com.tron.modulepaser.service.ModuleRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Title:       ModuleRelationServiceImpl
 * Description:  模块依赖关系表服务类impl
 *
 */
@Service
public class ModuleRelationServiceImpl implements ModuleRelationService {

    @Autowired
    private ModuleRelationRepository moduleRelationRepository;

    /**
     * 递归查询指定"当前模块所依赖的模块id"的所有上级依赖关系记录
     * @param dependentModuleInfoId
     * @return
     */
    @Override
    public List<ModuleRelation> findDependenciesByDependentModuleInfoId(long dependentModuleInfoId) {
        return moduleRelationRepository.findDependenciesByDependentModuleInfoId(dependentModuleInfoId);
    }

    /**
     *  查询指定"当前模块信息id"，"当前模块所依赖的模块id"的模块依赖关系表记录
     * @param selectedModuleInfoId
     * @param dependentModuleInfoId
     * @return
     */
    @Override
    public List<ModuleRelation> findBySelectedModuleInfoIdAndDependentModuleInfoId(long selectedModuleInfoId, long dependentModuleInfoId) {
        return moduleRelationRepository.findBySelectedModuleInfoIdAndDependentModuleInfoId(selectedModuleInfoId, dependentModuleInfoId);
    }

    /**
     * 新增
     *
     * @param moduleRelation
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result save(ModuleRelation moduleRelation) {
        moduleRelation.setId(null);
        moduleRelationRepository.save(moduleRelation);
        return new Result(ResultCode.SUCCESS);
    }
}
