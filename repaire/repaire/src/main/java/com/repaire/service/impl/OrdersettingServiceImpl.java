package com.repaire.service.impl;

import com.repaire.mapper.TOrdersettingMapper;
import com.repaire.pojo.TOrdersetting;
import com.repaire.service.OrdersettingService;
import com.repaire.util.DateUtils;
import com.repaire.util.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrdersettingServiceImpl implements OrdersettingService {

    @Resource
    private TOrdersettingMapper tOrdersettingMapper;

    //保存我们的预约服务方法
    @Override
    public Result saveOrdersettingInfo(List<String[]> lists) {
        // List<String[]> lists [['2024/12/31',40],['2025/01/01',40],[],...]
        boolean flag = false;
        for (String[] list : lists) {
            // ['2024/12/31',40]
            String dateStr = list[0]; // '2024/12/31'
            //LocalDate
            LocalDate od = DateUtils.strToLocalDate(dateStr,"yyyy/MM/dd");

            String numStr = list[1];  // '40'
            Integer num = Integer.parseInt(numStr);

            LambdaQueryWrapper<TOrdersetting> wrapper =
                    new LambdaQueryWrapper<>();
            wrapper.eq(TOrdersetting::getOrderDate,od);
            //根据预约日期获取预约表中数据
            TOrdersetting tOrdersetting
                    = tOrdersettingMapper.selectOne(wrapper);
            if (tOrdersetting != null){
                //如果该日期设置预约信息 执行更新
                tOrdersetting.setNumber(num);
                flag = tOrdersettingMapper.updateById(tOrdersetting)>0;
            }else{
                //如果该日期没有设置预约信息  执行添加
                tOrdersetting = new TOrdersetting();
                tOrdersetting.setNumber(num);
                tOrdersetting.setOrderDate(od);
                flag = tOrdersettingMapper.insert(tOrdersetting)>0;
            }
        }
        if (flag){
            return  new Result(flag,"上传文件成功");
        }else{
            return  new Result(flag,"上传文件失败");
        }
    }

    @Override
    public Result getOrdersettingInfo(String date) {
        //"2024-12"
        String start = date +"-01"; // "2024-12-01"
        String end = date +"-31"; // "2024-12-31"
        LambdaQueryWrapper<TOrdersetting> wrapper
                = new LambdaQueryWrapper<>();
        //select *  from t_ordersetting where order_date between '2024-12-01' and '2024-12-31'
        wrapper.between(TOrdersetting::getOrderDate,start,end);
        //数据库中获取数据集合
        List<TOrdersetting> list = tOrdersettingMapper.selectList(wrapper);
        //给前端展示的数据集合
        List<Map<String,Object>> uilist = new ArrayList<>();

        //遍历数据库查询的集合
        for (TOrdersetting tOrdersetting : list) {
            Integer number = tOrdersetting.getNumber();
            Integer reservations = tOrdersetting.getReservations();
            LocalDate orderDate = tOrdersetting.getOrderDate();
            int dayOfMonth = orderDate.getDayOfMonth();
            Map<String,Object> map = new HashMap<>();
            map.put("date",dayOfMonth);
            map.put("number",number);
            map.put("reservations",reservations);
            uilist.add(map);
        }
        return new Result(true,null,uilist);
    }

    @Override
    public Result updateOrdersettingInfo(String date, Integer number) {
        boolean flag = false;
        LocalDate od = DateUtils.strToLocalDate(date, "yyyy-MM-dd");
        LambdaQueryWrapper<TOrdersetting> wrapper =
                new LambdaQueryWrapper<>();
        wrapper.eq(TOrdersetting::getOrderDate,od);
        //根据预约日期获取预约表中数据
        TOrdersetting tOrdersetting
                = tOrdersettingMapper.selectOne(wrapper);
        if (tOrdersetting !=null){
            //更新
            if (number >= tOrdersetting.getReservations()){
                tOrdersetting.setNumber(number);
            }else{
                tOrdersetting.setNumber(tOrdersetting.getReservations());
            }
            flag = tOrdersettingMapper.updateById(tOrdersetting)>0;
        }else{
            //添加
            tOrdersetting = new TOrdersetting();
            tOrdersetting.setOrderDate(od);
            tOrdersetting.setNumber(number);
            flag = tOrdersettingMapper.insert(tOrdersetting)>0;
        }
        if (flag){
            return  new Result(flag,"修改人数成功");
        }else{
            return  new Result(flag,"修改人数失败");
        }
    }
}
