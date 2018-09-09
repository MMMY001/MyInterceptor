package com.example.singledemo.controller;


import com.example.singledemo.service.UserService;
import com.example.singledemo.util.cookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/api")
public class loginController {


    @Autowired
    private UserService userService;

    private cookieUtil ckUtil;

    @RequestMapping("/login_view")
    public String login_view() {
        return "login";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(String uname, String password, HttpServletRequest request, HttpSession session, HttpServletResponse response) {

        if (uname != null && password != null && uname != "" && password != "") {
            String keyMsg = userService.selectByUnameAndPassword(uname, password);
            if (keyMsg == null || keyMsg == "") {
                return "2";
            } else {
                request.getSession().setAttribute("uname",uname);
                ckUtil.addCookie(response,uname,keyMsg);
                return "ok";
            }
        }else {
            return "2";
        }
    }

    // 跳转hello页面
    @RequestMapping("/jump")
    @ResponseBody
    public String jump(){
        return "OK";
    }


    // 跳转error页面
    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }


    // 跳转error页面
    @RequestMapping("/error")
    public String error(){
        return "error";
    }

}
