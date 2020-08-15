package com.nbcb.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.ws.BindingType;

/**
 * CREATE TABLE `shop_seckill` (
 `seckill_id` bigint(20) NOT NULL COMMENT '商品库存id',
 `name` varchar(120) CHARACTER SET utf8 NOT NULL COMMENT '商品名称',
 `inventory` int(11) NOT NULL COMMENT '库存数量',
 `start_time` datetime NOT NULL COMMENT '秒杀开启时间',
 `end_time` datetime NOT NULL COMMENT '秒杀结束时间',
 `create_time` datetime NOT NULL COMMENT '创建时间',
 `version` bigint(20) NOT NULL DEFAULT '0',
 PRIMARY KEY (`seckill_id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='秒杀库存表';
 ————————————————
 版权声明：本文为CSDN博主「V瑞」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 原文链接：https://blog.csdn.net/RuiKe1400360107/article/details/104731775/
 */


@Entity
@Table(name = "SHOP_SKILL")
@Data
public class ShopSkill {
    @Id
    @Column(name = "SECKILL_ID")
    private String seckillId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PRODUCT_NUM")
    private String productNum;
    @Column(name = "start_time")
    private String startTime;
    @Column(name = "end_time")
    private String endTime;
    @Column(name = "create_time")
    private String createTime;
    @Column(name = "version")
    private String version;
}
