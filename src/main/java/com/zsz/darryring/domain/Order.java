package com.zsz.darryring.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.zsz.darryring.domain.mypojo.OrderRing;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单
 * @TableName order
 */
@TableName(value ="order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

    /**
     * 订单号
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String oid;

    /**
     * 订单日期
     */

    private Date odate;

    /**
     * 下单用户id
     */
    private Integer uid;

    /**
     * 支付状态 1-已支付 0-未支付
     */
    private Integer ostatues;


    /**
     * 运输方式
     */
    private String otransports;

    /**
     * 地址
     */
    @TableField(exist = false)
    private Address address;

    /**
     * 商品戒指列表
     */
    @TableField(exist = false)
    private List<OrderRing> orderRings;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}