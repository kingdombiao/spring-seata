package com.kingdombiao.monolithic.mutiple.datasource.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.kingdombiao.monolithic.mutiple.datasource.Dao.ProductDao;
import com.kingdombiao.monolithic.mutiple.datasource.service.ProductService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Authror biao
 * @Description: 商品 Service 实现
 * @Date: 2020-05-31 上午 11:14
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @DS("storage-ds")
    @Transactional
    @Override
    public void reduceStock(Integer productId, Integer amount) throws Exception {
        log.info("[reduceStock] 当前XID:{}", RootContext.getXID());

        checkStock(productId,amount);

        log.info("[reduceStock] 开始扣减商品productId:{}库存", productId);
        final int reduceStock = productDao.reduceStock(productId, amount);
        if(reduceStock==0){
            log.info("[reduceStock] 扣减商品productId:{}库存失败", productId);
            throw new RuntimeException("当前库存不足");
        }
        log.info("[reduceStock] 扣减商品productId:{}库存成功", productId);
    }

    private void checkStock(Integer productId, Integer requiedAmount) {
        log.info("[checkStock] 检查商品productId:{}的库存",productId);
        final Integer stockAmount = productDao.getStock(productId);
        if(stockAmount<requiedAmount){
            log.info("商品productId:{},库存不足，当前库存stockAmount:{},下单数量requiedAmount:{}",productId,stockAmount,requiedAmount);
            throw new RuntimeException("当前库存不足");
        }
    }
}
