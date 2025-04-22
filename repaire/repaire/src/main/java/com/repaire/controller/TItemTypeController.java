package com.repaire.controller;

import com.repaire.service.TItemTypeService;
import com.repaire.util.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/itemtype")
public class TItemTypeController {
    @Resource
    private TItemTypeService itemTypeService;

    @RequestMapping("/getItemids")
    public Result getItemids(Integer typeId) {
        Result result = itemTypeService.getItemids(typeId);
        return result;
    }
}
