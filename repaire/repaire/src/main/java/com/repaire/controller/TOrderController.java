package com.repaire.controller;


import com.repaire.service.OrderService;
import com.repaire.util.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sdlg
 * @since 2024-12-26
 */
@RestController
@RequestMapping("/order")
public class TOrderController {

    @Resource
    private OrderService orderService;

    @RequestMapping("/showOrderDetailInfo")
    public Result showOrderDetailInfo(){
        Result result =  orderService.showOrderDetailInfo();
        return  result;
    }
}

