package com.tron.modulepaser.repository;

import com.tron.modulepaser.entity.ModuleRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Title:       ModuleDependencyRelationRepository
 * Description: 模块依赖关系表DAO
 */

@Repository
public interface ModuleRelationRepository extends JpaRepository<ModuleRelation, Long> {

    List<ModuleRelation> findBySelectedModuleInfoIdAndDependentModuleInfoId(long selectedModuleInfoId, long dependentModuleInfoId);

    @Query(value = "WITH RECURSIVE dependencies AS " +
            "( SELECT v.* FROM `module_relation` v WHERE v.dependent_module_info_id = ? " +
            "UNION ALL " +
            "SELECT v.* FROM dependencies, `module_relation` v WHERE v.dependent_module_info_id = dependencies.selected_module_info_id ) " +
            "SELECT * FROM dependencies;", nativeQuery = true)
    List<ModuleRelation> findDependenciesByDependentModuleInfoId(long dependentModuleInfoId);
}
