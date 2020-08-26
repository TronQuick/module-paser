package com.tron.modulepaser.service;

import com.tron.modulepaser.entity.ModuleRelation;
import com.tron.modulepaser.controller.response.Result;

import java.util.List;

/**
 * Title:       ModuleRelationService
 * Description:  模块依赖关系表服务类
 *
 */
public interface ModuleRelationService {

    /**
     * 递归查询指定"当前模块所依赖的模块id"的所有上级依赖关系记录
     * @param dependentModuleInfoId
     * @return
     */
    List<ModuleRelation> findDependenciesByDependentModuleInfoId(long dependentModuleInfoId);

    /**
     *  查询指定"当前模块信息id"，"当前模块所依赖的模块id"的模块依赖关系表记录
     * @param selectedModuleInfoId
     * @param dependentModuleInfoId
     * @return
     */
    List<ModuleRelation> findBySelectedModuleInfoIdAndDependentModuleInfoId(long selectedModuleInfoId,long dependentModuleInfoId);

    /**
     * 新增
     * @param moduleRelation
     * @return
     */
    Result save(ModuleRelation moduleRelation) throws Exception;
}
