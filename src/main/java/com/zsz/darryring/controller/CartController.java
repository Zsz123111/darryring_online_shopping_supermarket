package com.zsz.darryring.controller;

import com.zsz.darryring.domain.Address;
import com.zsz.darryring.domain.Ring;
import com.zsz.darryring.domain.User;
import com.zsz.darryring.domain.mypojo.Result;
import com.zsz.darryring.service.OrderService;
import com.zsz.darryring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 购物车
 */
@Controller
public class CartController {
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    @RequestMapping("/cart")
    public String getUserCart(HttpSession session, Map map){
        User user = (User) session.getAttribute("user");
        User userAllInfoByUid = userService.findUserAllInfoByUid(user.getUid());
        session.setAttribute("user",userAllInfoByUid);
        double sum =0;
        for (Ring ring:userAllInfoByUid.getShopCart()){
            sum+=ring.getRprice();
        }
        map.put("sum",sum);
        return "cart";
    }
    @RequestMapping("/cartdelete")
    @ResponseBody
    public void cartDelete(HttpSession session, @RequestParam(defaultValue = "0") Integer rid){
        User user = (User) session.getAttribute("user");
        userService.deleteCartByRidAndUid(rid,user.getUid());
    }

    @RequestMapping("/cart_order_msg")
    @ResponseBody
    public void getMsg(HttpSession session, HttpServletRequest request, HttpServletResponse response){
        User user = (User) session.getAttribute("user");
        Integer uid = user.getUid();
        List<Address> allAddressByUid = userService.findAllAddressByUid(uid);
        System.out.println(allAddressByUid);
        request.setAttribute("addresses",allAddressByUid);
        double sum =0;
        User userAllInfoByUid = userService.findUserAllInfoByUid(user.getUid());
        for (Ring ring:userAllInfoByUid.getShopCart()){
            sum+=ring.getRprice();
        }
        request.setAttribute("sum",sum);
        try {
            request.getRequestDispatcher("cart_order").forward(request,response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @RequestMapping("/cart_order_success_before")
    @ResponseBody
    public void beforeSuccess(HttpSession session, HttpServletRequest request, HttpServletResponse response,Integer aid){
//        System.out.println(aid);
        User user = (User) session.getAttribute("user");
        String[] transPool = {"顺丰快递","圆通快递" ,"中通快递", "京东快递", "丹鸟速递"};
        Random random = new Random();
        Date date = new Date();
        String oid = orderService.addOrder(date,user, aid, transPool[random.nextInt(5)]);
        session.setAttribute("user",userService.findUserAllInfoByUid(user.getUid()));
        request.setAttribute("newoid",oid);
        try {
            request.getRequestDispatcher("cart_order_success").forward(request,response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping("/paysuccess")
    @ResponseBody
    public void pay(String oid,HttpServletResponse response){
        orderService.changeOrderStatues(oid);
        try {
            response.sendRedirect("member_index_before");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @RequestMapping("/addcart")
    @ResponseBody
    public String addCart(Integer uid, Integer rid){
        userService.insertCartByRidAndUid(rid,uid);
        return "加入成功！";
    }
}
