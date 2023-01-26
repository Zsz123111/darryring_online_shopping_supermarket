package com.zsz.darryring.domain.mypojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyOrder {
    private String oid;
    private Date odate;
    private Integer ostatues;
    private String otrans;
    private List<MyOrderRing> oRings = new ArrayList<>();
}
