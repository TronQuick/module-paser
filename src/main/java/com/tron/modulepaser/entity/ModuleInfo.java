package com.tron.modulepaser.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * Title:       ModuleInfo
 * Description: 模块信息表实体类
 *
 */

@Entity
@org.hibernate.annotations.Table(appliesTo = "module_info",comment = "模块信息表")
@Table(name = "module_info", schema = "mp",
        uniqueConstraints = {@UniqueConstraint(columnNames = "pk_id", name = "pk_id")})
@Data
@ToString
public class ModuleInfo {


    @Id
    @Column(name = "pk_id", columnDefinition = "bigint unsigned not null ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "artifactid",columnDefinition = "varchar(256) not null comment '模块名称' ")
    private String artifactid;

    @Column(name = "groupid", columnDefinition = "varchar(256) not null comment '模块组织名称'")
    private String groupid;

    @Column(name = "version", columnDefinition = "varchar(256) comment '模块版本号'")
    private String version;

}
