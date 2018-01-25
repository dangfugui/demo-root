package com.dang.demo.orders.service.impl;

import com.dang.demo.rpc.api.bean.Order;
import com.dang.demo.rpc.api.service.OrderService;

/**
 * @Date Create in 2018/1/18
 */
public class OrderServiceImpl implements OrderService {
    public Order queryOrder(Integer orderId) {
        Order order = new Order();
        order.setId(orderId);
        order.setBuyName("iphone");
        order.setGoodsName("goodsName");
        order.setAddress("address");
        order.setPrice(10.0);
        return order;
    }
}
