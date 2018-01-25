package com.dang.demo.rpc.api.service;

import com.dang.demo.rpc.api.bean.Order;

/**
 * api  正常使用中应该是 在一个单独的jar 包中  consumer （来实现接口） 和 provider （来调用）
 * @Date Create in 2018/1/18
 */
public interface OrderService {
    Order queryOrder(Integer orderId);
}
