package com.zsz.darryring.controller;

import com.zsz.darryring.domain.User;
import com.zsz.darryring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 登录
 */
@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    /**
     * 登录功能
     * @param uphone 电话
     * @param upassword 密码
     * @param remember 是否记住密码 1-记住 null-不记住
     * @return 登录结果
     */
    @RequestMapping("/log")
    public String userlog(String uphone, String upassword, Integer remember, Map map, HttpSession session, HttpServletResponse response){
//        System.out.println(uphone+"+"+upassword+"+"+remember);
        User user = userService.findUserByUPhone(uphone);
        if (user!=null&&upassword.equals(user.getUpassword())){
            if (remember!=null&&remember==1){//记住密码
                session.setMaxInactiveInterval(60*60*24*365*20);
                session.setAttribute("user",user);
                Cookie jSessionId = new Cookie("JSESSIONID",session.getId());
                jSessionId.setMaxAge(60*60*24*365*20);
                response.addCookie(jSessionId);
                return "redirect:index";
            }else {
                session.setAttribute("user",user);
                return "redirect:index";
            }
        }else {
            map.put("errorMsg","用户名或密码错误");
            return "login";
        }
    }

    /**
     * 用户退出登录
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String userLogout(HttpSession session, HttpServletRequest request){
        session.removeAttribute("user");
        return "redirect:index";
    }
}
