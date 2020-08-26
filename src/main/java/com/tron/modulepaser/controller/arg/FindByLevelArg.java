package com.tron.modulepaser.controller.arg;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Title:       FindByLevelArg
 * Description: 根据指定模块名称、版本号、上级依赖层级级数，获取依赖此模块的模块接口的入参
 *
 */
@Data
public class FindByLevelArg {

    @NotBlank(message = "模块名称不能为空")
    private String artifactid;

    @NotBlank(message = "模块版本号不能为空")
    private String version;

    @NotNull(message = "maxLengthForField不能为空")
    private Integer level;
}
