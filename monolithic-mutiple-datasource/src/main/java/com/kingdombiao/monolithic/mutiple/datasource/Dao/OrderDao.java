package com.kingdombiao.monolithic.mutiple.datasource.Dao;

import com.kingdombiao.monolithic.mutiple.datasource.bean.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

/**
 * @Authror biao
 * @Description: 订单dao
 * @Date: 2020-05-31 下午 3:13
 */
@Mapper
@Repository
public interface OrderDao {

    @Options(useGeneratedKeys=true,keyColumn = "id",keyProperty = "id")
    @Insert("insert into orders(user_id,product_id,pay_amount) values (#{userId},#{productId}, #{payAmount})")
    int savaOrder(Order order);
}
