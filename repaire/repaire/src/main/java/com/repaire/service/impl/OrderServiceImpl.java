package com.repaire.service.impl;

import com.repaire.mapper.TOrderMapper;
import com.repaire.mapper.TUserMapper;
import com.repaire.pojo.TOrder;
import com.repaire.pojo.TUser;
import com.repaire.service.OrderService;
import com.repaire.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private TOrderMapper tOrderMapper;
    @Resource
    private TUserMapper tUserMapper;


    @Override
    public Result showOrderDetailInfo() {
        /*
        List<Map<String,Object>> orderDetailInfo = tOrderMapper.getOrderDetailInfo();
        return new Result(true,null,orderDetailInfo);
        */

        List<TOrder> tOrders = tOrderMapper.selectList(null);
        for (TOrder tOrder : tOrders) {
            Integer userId = tOrder.getUserId();
            TUser tUser = tUserMapper.selectById(userId);
            tOrder.setTUser(tUser);
        }
        return  new Result(true,null,tOrders);
    }
}
