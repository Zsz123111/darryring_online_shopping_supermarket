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
 * 戒指
 * @TableName ring
 */
@TableName(value ="ring")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ring implements Serializable {
    /**
     * 戒指id
     */
    @TableId(type = IdType.AUTO)
    private Integer rid;

    /**
     * 戒指名字
     */
    private String rname;

    /**
     * 戒指单价
     */
    private Double rprice;

    /**
     * 戒指销量
     */
    private Integer rsales;

    /**
     * 戒指图片url
     */
    private String rurl;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}