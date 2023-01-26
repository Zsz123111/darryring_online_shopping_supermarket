package com.zsz.darryring.controller;

import com.zsz.darryring.domain.Address;
import com.zsz.darryring.domain.Order;
import com.zsz.darryring.domain.Ring;
import com.zsz.darryring.domain.User;
import com.zsz.darryring.domain.mypojo.MyOrder;
import com.zsz.darryring.domain.mypojo.MyOrderRing;
import com.zsz.darryring.domain.mypojo.OrderRing;
import com.zsz.darryring.domain.mypojo.Result;
import com.zsz.darryring.service.AddressService;
import com.zsz.darryring.service.RingService;
import com.zsz.darryring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 用户个人中心
 */
@Controller
public class MemberController {
    @Autowired
    private UserService userService;
    @Autowired
    private RingService ringService;
    @Autowired
    private AddressService addressService;


    /**
     * 个人中心
     * @param session
     * @return
     */
    @RequestMapping({"/mycenter","/member_index_before"})
    public String userCenter(HttpSession session){
        User user = (User)session.getAttribute("user");
        User userAllInfoByUid = userService.findUserAllInfoByUid(user.getUid());
        session.setAttribute("user",userAllInfoByUid);
        return "member_index";
    }

    /**
     * 我的订单
     * @param session
     * @param map
     * @return
     */
    @RequestMapping("/member_order_before")
    public String mob(HttpSession session,Map map){
        User user = (User)session.getAttribute("user");
        System.out.println(user);
        List<MyOrder> myOrderList = new ArrayList<>();
        for (Order order: user.getOrdersList()) {
            MyOrder myOrder = new MyOrder();
            myOrder.setOid(order.getOid());
            myOrder.setOdate(order.getOdate());
            myOrder.setOstatues(order.getOstatues());
            myOrder.setOtrans(order.getOtransports());
            List<MyOrderRing> myOrderRingList = new ArrayList<>();
            for (OrderRing orderRing:order.getOrderRings()){
                Ring ringByRid = ringService.findRingByRid(orderRing.getRid());
                MyOrderRing myOrderRing = new MyOrderRing();
                myOrderRing.setRname(ringByRid.getRname());
                myOrderRing.setRurl(ringByRid.getRurl());
                myOrderRing.setRnum(orderRing.getRNum());
                myOrderRing.setRsumprice(orderRing.getRSumPrice());
                myOrderRingList.add(myOrderRing);
            }
            myOrder.setORings(myOrderRingList);
            myOrderList.add(myOrder);
        }
        map.put("orders",myOrderList);
        System.out.println(myOrderList);
        User userAllInfoByUid = userService.findUserAllInfoByUid(user.getUid());
        session.setAttribute("user",userAllInfoByUid);
        return "member_order";
    }

    /**
     * 收货地址
     * @param session
     * @param map
     * @return
     */
    @RequestMapping("/member_addr_before")
    public String mab(HttpSession session,Map map){
        User user = (User)session.getAttribute("user");
        List<Address> allAddressByUid = addressService.findAllAddressByUid(user.getUid());
        map.put("addresses",allAddressByUid);
        User userAllInfoByUid = userService.findUserAllInfoByUid(user.getUid());
        session.setAttribute("user",userAllInfoByUid);
        return "member_addr";
    }

    /**
     * 上传头像
     * @param session
     * @param map
     * @return
     */
    @RequestMapping("/member_avatar_before")
    public String mavb(HttpSession session,Map map){
        User user = (User)session.getAttribute("user");
        User userAllInfoByUid = userService.findUserAllInfoByUid(user.getUid());
        session.setAttribute("user",userAllInfoByUid);
        return "member_avatar";
    }

    /**
     * 个人信息
     * @param session
     * @param map
     * @return
     */
    @RequestMapping("/member_info_before")
    public String mib(HttpSession session,Map map){
        User user = (User)session.getAttribute("user");
        User userAllInfoByUid = userService.findUserAllInfoByUid(user.getUid());
        session.setAttribute("user",userAllInfoByUid);
        return "member_info";
    }

    /**
     * 修改密码
     * @param session
     * @param map
     * @return
     */
    @RequestMapping("/member_pwd_before")
    public String mpb(HttpSession session,Map map){
        User user = (User)session.getAttribute("user");
        User userAllInfoByUid = userService.findUserAllInfoByUid(user.getUid());
        session.setAttribute("user",userAllInfoByUid);
        return "member_pwd";
    }

    @RequestMapping("/addressdelete")
    public String deleteAddress(Integer aid){
        addressService.deleteAddressByAid(aid);
        return "redirect:member_addr_before";
    }

    @RequestMapping("/addressadd")
    @ResponseBody
    public Result addAddress(String aname,String aaddress,String aphone,String apostcode,Integer uid){
        Address address = new Address(null, aname, aaddress, aphone, apostcode, uid);
        addressService.addNewAddress(address);
        return new Result("添加成功！");
    }
    @RequestMapping("/updatepwd")
    public String changePwd(HttpSession session, String oldpwd,String newpwd,Map map){
        User user = (User)session.getAttribute("user");
        if (!user.getUpassword().equals(oldpwd)){
            map.put("result","密码错误！");
            return "forward:member_pwd_before";
        }else {
            userService.updatePwd(user.getUid(),newpwd);
            session.removeAttribute("user");
            return "redirect:index";
        }
    }

    @RequestMapping("/savepersonmsg")
    @ResponseBody
    //"uphone":uphone,"urealname":urealname,"ugender":ugender,"uyear":uyear,"umonth":umonth,"uday":uday
    public Result savePersonMsg(String uphone,String urealname,String ugender,Integer uyear,Integer umonth,Integer uday,HttpSession session){
        User user = (User)session.getAttribute("user");
        Date date = null;
        String ubirthday = ""+uyear+"-"+umonth+"-"+uday;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = format.parse(ubirthday);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        User user1 = new User(user.getUid(), uphone, null, urealname, ugender, date, null, null, null);
        userService.updateUserMsg(user1);
        return new Result("修改个人信息成功！");
    }

    @PostMapping("changeUserPhoto")
    @ResponseBody
    public String changePhoto(HttpServletRequest request, MultipartFile file) throws Exception{
        //创建文件夹存放上传文件
        //1.设置上传文件夹的真实路径
        String realPath = ResourceUtils.getURL("classpath:").getPath()+"/static/images/person";
        //2.判断该文件夹是否存在，如果不存在新建文件夹
        File dir = new File(realPath);
        if (!dir.exists()){
            dir.mkdirs();
        }
        User user = (User) request.getSession().getAttribute("user");
        String uphone = user.getUphone();
        String photoname = uphone+".png";
        File newFile = new File(dir,photoname);
        file.transferTo(newFile);
        user.setUurl("images/person/"+photoname);
        userService.changePhoto(user);
        return "images/person/"+photoname;
    }
}
