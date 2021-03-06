package com.kotall.learn.dubbo.api;

import com.kotall.learn.dubbo.api.dto.OrderDto;

/**
 * @author: aracwong
 * @email: aracwong@163.com
 * @datetime: 2017/7/1 0001 下午 3:00
 * @version: 1.0.0
 */
public interface PaymentService {

    void pay(long trxAmt);

    void pay(OrderDto order);
}
