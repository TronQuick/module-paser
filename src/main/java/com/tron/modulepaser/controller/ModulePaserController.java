package com.tron.modulepaser.controller;

import com.tron.modulepaser.controller.arg.FindByLevelArg;
import com.tron.modulepaser.controller.arg.FindUpModulesArg;
import com.tron.modulepaser.controller.arg.ScanPomArg;
import com.tron.modulepaser.controller.response.Result;
import com.tron.modulepaser.controller.response.ResultCode;
import com.tron.modulepaser.webService.ModulePaserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/mp")
public class ModulePaserController {

    @Autowired
    private ModulePaserService modulePaserService;
    
    /**
     * 扫描模块信息与模块依赖关系
     */
    @PostMapping("/scanPom")
    public Result scanPom(@RequestBody @Valid ScanPomArg scanPomArg) {
        return modulePaserService.scanPom(scanPomArg);
    }

    /**
     *  根据指定模块名称、版本号获取上级依赖层数
     */
    @PostMapping("/getLevel")
    public Result getLevel(@RequestBody @Valid FindUpModulesArg findUpModulesArg) {
        return modulePaserService.getLevel(findUpModulesArg);
    }

    /**
     *  根据指定模块名称，版本号查询所有上级依赖模块（依赖此模块的模块）
     */
    @PostMapping("/findUpModules")
    public Result findUpModules(@RequestBody @Valid FindUpModulesArg findUpModulesArg) {
        return modulePaserService.findUpModules(findUpModulesArg);
    }

    /**
     *  根据指定模块名称、版本号、上级依赖层级级数，获取指定的上级依赖模块（依赖此模块的模块）
     */
    @PostMapping("/findByLevel")
    public Result findByLevel(@RequestBody @Valid FindByLevelArg findByLevelArg) {
        return modulePaserService.findByLevel(findByLevelArg);
    }
}
