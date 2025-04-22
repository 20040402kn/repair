package com.repaire.controller;


import com.repaire.service.BuildService;
import com.repaire.util.Result;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lzy
 * @since 2024-12-26
 */
@RestController
@RequestMapping("/build")
public class TBuildController {
    @Resource
    private BuildService buildService;

    @RequestMapping("/getAllBuildInfo")
    public Result getAllBuildInfo(){
        Result result=buildService.getAllBuildInfo();
        return result;
    }
}

