package com.kingdombiao.monolithic.mutiple.datasource.service;

/**
 * @Authror biao
 * @Description: 账户 Service
 * @Date: 2020-05-31 上午 11:23
 */
public interface AccountService {

    void reduceBalance(Integer userId, Integer price) throws Exception;
}
