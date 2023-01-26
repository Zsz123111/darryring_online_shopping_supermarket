package com.zsz.darryring.controller;

import com.zsz.darryring.domain.Ring;
import com.zsz.darryring.service.RingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * 商品详情
 */
@Controller
public class DetailController {
    @Autowired
    private RingService ringService;
    @RequestMapping("/details")
    public String getDeatil(Integer rid, Map map){
        Ring ring = ringService.findRingByRid(rid);
        map.put("ring",ring);
        return "detail";
    }
}
