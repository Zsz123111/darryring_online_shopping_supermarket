package com.zsz.darryring.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户
 * @TableName user
 */
@TableName(value ="user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    /**
     * 用户id
     */
    @TableId(type = IdType.AUTO)
    private Integer uid;

    /**
     * 用户电话
     */
    private String uphone;

    /**
     * 用户密码
     */
    private String upassword;

    /**
     * 用户真实姓名
     */
    private String urealname;

    /**
     * 用户性别
     */
    private String ugender;

    /**
     * 用户生日
     */
    private Date ubirthday;

    /**
     * 用户头像url
     */
    private String uurl;

    /**
     * 购物车
     */
    @TableField(exist = false)
    private List<Ring> shopCart;

    /**
     * 用户拥有的订单
     */
    @TableField(exist = false)
    private List<Order> ordersList;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public User(String uphone, String upassword) {
        this.uphone = uphone;
        this.upassword = upassword;
    }
}