package com.nbcb.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 秒杀成功商品明细表
 */
@Entity
@Table(name = "SHOP_ORDER")
@Data
public class ShopOrder {


    /**
     * 订单号
     */
    @Id
    @Column(name="SHOP_ORDER_ID")
    private String shopOrderId;

    /**
     * 创建的时间
     */
    @Column(name = "CREATE_TIME")
    private String createTime;
    /**
     * 秒杀商品的Id
     */

    @Column(name = "SECKILL_ID")
    private String seckillid;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    /**
     * 枚举
     */
    public enum  OrderState{
        INVALID, //无效
        SUCCESS,//成功
        PAYSUCCESS,//支付成功
        DELIVERY //已经发货
    }
    @Column(name = "STATE")
    private OrderState state;

    /**
     * 创建的时间
     */

}
