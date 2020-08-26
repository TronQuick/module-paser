package com.tron.modulepaser.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * Title:       ModuleRelation
 * Description: 模块依赖关系 表实体类
 */

@Entity
@org.hibernate.annotations.Table(appliesTo = "module_relation", comment = "模块依赖关系表")
@Table(name = "module_relation", schema = "mp",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "pk_id", name = "pk_id"),
        })
@Data
@ToString
public class ModuleRelation {

        @Id
        @Column(name = "pk_id", columnDefinition = "bigint unsigned not null ")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "selected_module_info_id", columnDefinition = "bigint unsigned not null comment '当前模块的主键id' ")
        private Long selectedModuleInfoId;

        @Column(name = "dependent_module_info_id", columnDefinition = "bigint unsigned not null comment '当前模块依赖的模块的主键id' ")
        private Long dependentModuleInfoId;

}
