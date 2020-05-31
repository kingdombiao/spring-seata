package com.kingdombiao.monolithic.mutiple.datasource.controller;

import com.kingdombiao.monolithic.mutiple.datasource.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Authror biao
 * @Description: TODO
 * @Date: 2020-05-31 下午 3:20
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/create")
    public Integer createOrder(Integer userId,Integer productId,Integer price,Integer amount) throws Exception {
        log.info("[createOrder] 下单请求,用户Id:{},商品id:{},价格:{},数量:{}",userId,productId,price,amount);
       return orderService.createOrder(userId, productId, price,amount);
    }



}
