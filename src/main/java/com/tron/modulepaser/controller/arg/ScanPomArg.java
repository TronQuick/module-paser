package com.tron.modulepaser.controller.arg;

import lombok.Data;

import javax.validation.constraints.NotNull;


/**
 * Title:       ScanPomArg
 * Description: FindAllArg接口入参
 *
 */
@Data
public class ScanPomArg {

    @NotNull(message = "项目路径不能为空")
    private String projectPath;

}
