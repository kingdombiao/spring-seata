package com.kingdombiao.monolithic.mutiple.datasource.bean;

import lombok.Builder;
import lombok.Data;

/**
 * @Authror biao
 * @Description: 订单实体
 * @Date: 2020-05-31 下午 3:14
 */
@Builder
@Data
public class Order {

    /** 订单编号 **/
    private Integer id;

    /** 用户编号 **/
    private Integer userId;

    /** 产品编号 **/
    private Integer productId;

    /** 支付金额 **/
    private Integer payAmount;
}
