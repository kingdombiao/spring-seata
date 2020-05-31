package com.kingdombiao.monolithic.mutiple.datasource.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @Authror biao
 * @Description: TODO
 * @Date: 2020-05-31 上午 11:28
 */
@Mapper
@Repository
public interface AccountDao {

    @Select("select balance from account where id=#{userId}")
    Integer getBlance(Integer userId);

    @Update("update account set balance=balance-#{price} where id=#{userId} and balance>=#{price}")
    int reduceBalance(Integer userId, Integer price);
}
