package com.zsz.darryring.controller;

import com.zsz.darryring.domain.Ring;
import com.zsz.darryring.service.RingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 页面跳转控制器
 */
@Controller
public class PageController {
    @Autowired
    private RingService ringService;
    /**
     * 页面跳转
     * @param page 跳转界面
     * @return 跳转界面
     */
    @RequestMapping("/{page}")
    public String page(@PathVariable String page, Map map, @RequestParam(defaultValue = "all") String type){
        if ("index".equals(page)){
            List<Ring> hotRings = ringService.findHotRings(8);//前八个最火商品
            map.put("indexrings",hotRings);
        }
        if ("lists".equals(page)){
            List<Ring> ringList = ringService.findRingsByLikeRName(type);
            map.put("ringlist",ringList);
        }
        return page;
    }

    /**
     * 防止图标报错
     */
    @RequestMapping("/favicon.ico")
    @ResponseBody
    void returnNOFavicon(){}
}
