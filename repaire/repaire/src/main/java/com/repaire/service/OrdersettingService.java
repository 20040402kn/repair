package com.repaire.service;

import com.repaire.util.Result;

import java.util.List;

public interface OrdersettingService {
    Result saveOrdersettingInfo(List<String[]> lists);

    Result getOrdersettingInfo(String date);

    Result updateOrdersettingInfo(String date, Integer number);
}
