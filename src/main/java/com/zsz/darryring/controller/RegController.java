package com.zsz.darryring.controller;

import com.zsz.darryring.domain.User;
import com.zsz.darryring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 注册
 */
@Controller
public class RegController {
    @Autowired
    private UserService userService;

    /**
     * 注册功能
     * @param uphone 电话
     * @param upassword 密码
     * @param model
     * @return
     */
    @RequestMapping("/register")
    public String register(String uphone, String upassword, Model model){
        System.out.println(uphone+"+"+upassword);
        if (userService.findUserByUPhone(uphone)==null){
            userService.InsertNewUser(new User(uphone,upassword));
            return "redirect:login";
        }else {
            model.addAttribute("errorMsg","该账号已被注册！");
            return "reg";
        }
    }
}
