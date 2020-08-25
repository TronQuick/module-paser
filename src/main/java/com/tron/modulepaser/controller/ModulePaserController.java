package com.tron.modulepaser.controller;

import com.tron.modulepaser.entity.arg.FindByLevelArg;
import com.tron.modulepaser.entity.arg.FindDependenciesArg;
import com.tron.modulepaser.entity.arg.ScanPomArg;
import com.tron.modulepaser.entity.response.RespEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/mp")
public class ModulePaserController {

    /**
     * 扫描模块信息与模块依赖关系
     */
    @PostMapping("/scanPom")
    @ResponseBody
    public RespEntity scanPom(@RequestBody ScanPomArg scanPomArg) {
        return null;
    }

    /**
     *  根据指定模块名称，版本号查询所有依赖此模块的所有模块
     */
    @PostMapping("/findDependencies")
    public RespEntity findDependencies(@RequestBody FindDependenciesArg findDependenciesArg) {
        return null;
    }

    /**
     *  根据指定模块名称、版本号获取上级依赖层数
     */
    @PostMapping("/getLevel")
    public RespEntity getLevel(@RequestBody FindDependenciesArg findDependenciesArg) {
        return null;
    }

    /**
     *  根据指定模块名称、版本号、上级依赖层级级数，获取依赖此模块的模块
     */
    @PostMapping("/findByLevel")
    public RespEntity findByLevel(@RequestBody FindByLevelArg findByLevelArg) {
        return null;
    }
}
