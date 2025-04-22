package com.repaire.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.repaire.mapper.TItemMapper;
import com.repaire.mapper.TRequestMapper;
import com.repaire.pojo.TItem;
import com.repaire.pojo.TRequest;
import com.repaire.service.ReportService;
import com.repaire.util.DateUtils;
import com.repaire.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ReportServiceImpl implements ReportService {
    @Resource
    private TItemMapper itemMapper;
    @Resource
    private TRequestMapper requestMapper;

    @Override
    public Result getItemReport() {
        Map<String, Object> map = new HashMap<>();
        Map<String, Long> itemTypeCount = new HashMap<>(); // 报修项类型和数量
        List<Map<String, Object>> itemCount = new ArrayList<>();

        // 查询所有报修项
        List<TItem> items = itemMapper.selectList(null);
        for (TItem item : items) {
            LambdaQueryWrapper<TRequest> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(TRequest::getItemId, item.getId());
            Long count = requestMapper.selectCount(wrapper);

            // 根据报修项类型累加报修数量
            itemTypeCount.put(item.getItemName(), itemTypeCount.getOrDefault(item.getItemName(), 0L) + count);
        }

        // 准备数据给前端
        List<String> itemTypes = new ArrayList<>(itemTypeCount.keySet());
        for (Map.Entry<String, Long> entry : itemTypeCount.entrySet()) {
            Map<String, Object> mapCount = new HashMap<>();
            mapCount.put("name", entry.getKey());
            mapCount.put("value", entry.getValue());
            itemCount.add(mapCount);
        }

        map.put("itemTypes", itemTypes);
        map.put("itemCount", itemCount);
        return new Result(true, null, map);
    }

    // 查询报告数据
    @Override
    public Result getBusinessReportData() {
        Map<String, Object> map = new HashMap<>();
        try {
            // 获取今日日期信息
            Date today = DateUtils.getToday();
            String strToday = DateUtils.parseDate2String(today, "yyyy-MM-dd");
            map.put("reportDate", strToday);

            // 当前日期所在周的第一天的日期
            Date firstDayOfWeek = DateUtils.getFirstDayOfWeek(today);
            String strFirstDayOfWeek = DateUtils.parseDate2String(firstDayOfWeek, "yyyy-MM-dd");

            // 当前日期所在月份的第一天
            Date firstDayThisMonth = DateUtils.getFirstDay4ThisMonth();
            String strFirstDayThisMonth = DateUtils.parseDate2String(firstDayThisMonth, "yyyy-MM-dd");

            // 今日报修数
            long countToday = requestMapper.getCountToday(strToday);
            long countWeek = requestMapper.getCountWeek(strToday, strFirstDayOfWeek);
            long countMonth = requestMapper.getCountMonth(strToday, strFirstDayThisMonth);

            map.put("todayRepairNumber", countToday);
            map.put("thisWeekRepairNumber", countWeek);
            map.put("thisMonthRepairNumber", countMonth);

            // 今日维修数
            long countTodayEnd = requestMapper.getCountTodayEnd(strToday);
            // 本周维修数
            long countWeekEnd = requestMapper.getCountWeekEnd(strToday, strFirstDayOfWeek);
            // 本月维修数
            long countMonthEnd = requestMapper.getCountMonthEnd(strToday, strFirstDayThisMonth);

            map.put("todayCompletedNumber", countTodayEnd);
            map.put("thisWeekCompletedNumber", countWeekEnd);
            map.put("thisMonthCompletedNumber", countMonthEnd);

            // 获取热门报修项
            List<Map<String, Object>> list = requestMapper.hotRepairItems();
            map.put("hotRepairItems", list);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new Result(true, null, map);
    }
}
