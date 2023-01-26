package com.zsz.darryring.domain.mypojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MyOrderRing {
    private String rname;
    private String rurl;
    private Integer rnum;
    private Double rsumprice;
}
