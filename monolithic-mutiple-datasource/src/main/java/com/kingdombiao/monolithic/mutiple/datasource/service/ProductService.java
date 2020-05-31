package com.kingdombiao.monolithic.mutiple.datasource.service;

import org.springframework.stereotype.Service;

/**
 * @Authror biao
 * @Description: 商品 Service
 * @Date: 2020-05-31 上午 11:13
 */
public interface ProductService {

    void reduceStock(Integer productId, Integer amount) throws Exception;

}
