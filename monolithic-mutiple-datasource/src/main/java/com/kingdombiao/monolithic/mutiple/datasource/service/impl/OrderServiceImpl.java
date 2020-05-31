package com.kingdombiao.monolithic.mutiple.datasource.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.kingdombiao.monolithic.mutiple.datasource.Dao.AccountDao;
import com.kingdombiao.monolithic.mutiple.datasource.Dao.OrderDao;
import com.kingdombiao.monolithic.mutiple.datasource.bean.Order;
import com.kingdombiao.monolithic.mutiple.datasource.service.AccountService;
import com.kingdombiao.monolithic.mutiple.datasource.service.OrderService;
import com.kingdombiao.monolithic.mutiple.datasource.service.ProductService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Authror biao
 * @Description: 订单 Service实现
 * @Date: 2020-05-31 上午 11:10
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductService productService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private OrderDao orderDao;

    @DS("order-ds")
    @GlobalTransactional
    @Override
    public Integer createOrder(Integer userId, Integer productId, Integer price, Integer amount) throws Exception {
        log.info("[createOrder] 当前xid:{}", RootContext.getXID());

        //扣减库存
        productService.reduceStock(productId, amount);
        //扣减余额
        accountService.reduceBalance(userId, price);
        //创建订单
        final Order order = Order.builder()
                .userId(userId)
                .productId(productId)
                .payAmount(amount * price)
                .build();

        orderDao.savaOrder(order);
        log.info("[createOrder] 保存订单成功,订单Id:{}",order.getId());
        throw new RuntimeException("测试seta是否回滚正常");
        //return order.getId();
    }
}
