package com.tron.modulepaser.controller.arg;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Title:       FindUpModulesArg
 * Description: 根据指定模块名称，版本号查询所有依赖此模块的所有模块接口入参
 *
 */
@Data
public class FindUpModulesArg {

    @NotBlank(message = "模块名称不能为空")
    private String artifactid;

    @NotBlank(message = "模块版本号不能为空")
    private String version;
}
