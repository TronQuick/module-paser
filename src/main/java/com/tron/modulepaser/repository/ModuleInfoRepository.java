package com.tron.modulepaser.repository;


import com.tron.modulepaser.entity.ModuleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Title:       ModuleInfoRepository
 * Description: 模块信息表DAO
 *
 */

@Repository
public interface ModuleInfoRepository extends JpaRepository<ModuleInfo,Long> {

    ModuleInfo findById(long id);

    List<ModuleInfo> findByArtifactidAndVersion(String artifactid, String version);

}
