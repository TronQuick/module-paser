package com.tron.modulepaser.entity.arg;

import lombok.Data;

/**
 * Title:       FindDependenciesArg
 * Description: 根据指定模块名称，版本号查询所有依赖此模块的所有模块接口入参
 *
 * @author : zhongzhaojun
 * date         : 2020/2/14 15:55
 */
@Data
public class FindDependenciesArg {

    private String artifactid;

    private String version;

}
