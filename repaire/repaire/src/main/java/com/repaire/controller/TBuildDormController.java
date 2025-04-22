package com.repaire.controller;


import com.repaire.service.BuildDormService;
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
@RequestMapping("/builddorm")
public class TBuildDormController {
    @Resource
    private BuildDormService buildDormService;

    @RequestMapping("/getBuildids")
    public Result getBuildids(Integer id) {
        Result result = buildDormService.getBuildids(id);
        return result;
    }

    @RequestMapping("/getDormids")
    public Result getDormids(Integer buildId) {
        Result result = buildDormService.getDormids(buildId);
        return result;
    }
}


