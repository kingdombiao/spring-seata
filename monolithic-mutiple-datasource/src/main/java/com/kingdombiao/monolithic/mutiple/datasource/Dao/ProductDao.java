package com.kingdombiao.monolithic.mutiple.datasource.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @Authror biao
 * @Description: 商品dao
 * @Date: 2020-05-31 上午 11:17
 */
@Mapper
@Repository
public interface ProductDao {

    @Select("select stock from product where id=#{productId}")
    Integer getStock(Integer productId);

    @Update("update product set stock=stock-#{amount} where id=#{productId} and stock>=#{amount}")
    int reduceStock(Integer productId,Integer amount);
}
