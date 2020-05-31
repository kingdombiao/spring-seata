package com.kingdombiao.monolithic.mutiple.datasource.service;

/**
 * @Authror biao
 * @Description: 订单 Service
 * @Date: 2020-05-31 上午 11:09
 */
public interface OrderService {

    Integer createOrder(Integer userId, Integer productId, Integer price,Integer amount) throws Exception;

}
