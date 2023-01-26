package com.zsz.darryring.domain.mypojo;

import com.zsz.darryring.domain.Ring;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单戒指
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRing {
    /**
     * 戒指
     */
    private Integer rid;
    /**
     * 购买数量
     */
    private Integer rNum;
    /**
     * 总价
     */
    private Double rSumPrice;
}
