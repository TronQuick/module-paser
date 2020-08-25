package com.tron.modulepaser.entity.arg;

import lombok.Data;

/**
 * Title:       FindByLevelArg
 * Description: 根据指定模块名称、版本号、上级依赖层级级数，获取依赖此模块的模块接口的入参
 *
 * @author : zhongzhaojun
 * date         : 2020/2/17 15:55
 */
@Data
public class FindByLevelArg {

    private String artifactid;

    private String version;

    private Integer level;
}
