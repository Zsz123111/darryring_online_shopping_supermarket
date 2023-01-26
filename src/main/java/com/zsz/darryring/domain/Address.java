package com.zsz.darryring.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 地址
 * @TableName address
 */
@TableName(value ="address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable {

    /**
     * 地址id
     */
    @TableId(type = IdType.AUTO)
    private Integer aid;

    /**
     * 收件人姓名
     */
    private String aname;

    /**
     * 详细地址信息
     */
    private String aaddress;

    /**
     * 收件电话
     */
    private String aphone;

    /**
     * 邮编
     */
    private String apostcode;

    /**
     * 所属用户id
     */
    private Integer uid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}