package com.repaire.controller;

import com.repaire.pojo.TType;
import com.repaire.service.TypeService;
import com.repaire.util.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/type")
public class TTypeController {
    @Resource
    private TypeService typeService;
    // 获取所有类别
    @RequestMapping("/getAllTypes")
    public Result getAllTypes() {
        List<TType> types = typeService.getAllTypes();
        return new Result(true, "查询成功", types);
    }
}
