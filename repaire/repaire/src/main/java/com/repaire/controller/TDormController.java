package com.repaire.controller;


import com.repaire.pojo.TDorm;
import com.repaire.service.DormService;
import com.repaire.util.PageResult;
import com.repaire.util.QueryPageBean;
import com.repaire.util.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/dorm")
public class TDormController {
    @Resource
    private DormService dormService;
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = dormService.findPage(queryPageBean);
        return pageResult;
    }
    @RequestMapping("/saveDorm")
    public Result saveDorm(@RequestBody TDorm dorm,Integer id) {
        Result result = dormService.saveDorm(dorm, id);
        return result;
    }

    @RequestMapping("/deleteDormById")
    public Result deleteDormById(Integer id) {
        Result result = dormService.deleteDormById(id);
        return result;
    }

}

