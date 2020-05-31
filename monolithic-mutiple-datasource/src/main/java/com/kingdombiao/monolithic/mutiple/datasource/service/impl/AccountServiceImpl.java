package com.kingdombiao.monolithic.mutiple.datasource.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.kingdombiao.monolithic.mutiple.datasource.Dao.AccountDao;
import com.kingdombiao.monolithic.mutiple.datasource.service.AccountService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Authror biao
 * @Description: 账户service实现
 * @Date: 2020-05-31 上午 11:24
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @DS("account-ds")
    @Transactional
    @Override
    public void reduceBalance(Integer userId, Integer price) throws Exception {
        log.info("[reduceBalance] 当前 XID:{}", RootContext.getXID());
        
        checkBlance(userId,price);
        log.info("[reduceBalance] 开始扣减用户userId:{}余额", userId);
        final int balance = accountDao.reduceBalance(userId, price);
        if(balance==0){
            log.info("[reduceBalance] 扣除用户userId:{}余额失败", userId);
            throw new RuntimeException("余额不足");
        }
        log.info("[reduceBalance] 扣减用户userId:{}余额成功", userId);
    }

    private void checkBlance(Integer userId, Integer price) {
        log.info("[checkBlance] 检查用户userId:{}余额",userId);
        final Integer balance = accountDao.getBlance(userId);
        if(balance<price){
            log.warn("[checkBalance] 用户userId:{} 余额不足，当前余额:{},下单金额:{}", userId, balance,price);
            throw new RuntimeException("余额不足");
        }
    }
}
