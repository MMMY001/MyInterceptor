package com.example.singledemo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class MyInterceptor implements HandlerInterceptor {


    @Autowired
    private cookieUtil ckUtil;
    @Autowired
    private redisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        HttpSession session=request.getSession();
        String token_key_name= (String) session.getAttribute("uname");

        Cookie[] cookies=request.getCookies();
        String ckToken;
        String redis_token;


        // 登录不做拦截
        if (request.getRequestURI().equals("/api/login")){
            return true;
        }else {
            if (cookies != null){
                for (Cookie cookie:cookies){
                    if (cookie.getName().equals(token_key_name)){ // 判断当前cookie是否为需要的cookie
                        ckToken=ckUtil.getCookieByName(request,token_key_name);
                        System.out.println("cktoken=============="+ckToken);
                        redis_token=redisUtil.getMsg(token_key_name);
                        System.out.println("redis_token================"+redis_token);
                        if (ckToken.equals(redis_token)){ // 对比cookie值和redis中的token是否一致
                            return true;
                        }else{
                            System.out.println("拦截器=======:结果不匹配");
                            response.sendRedirect("/api/login_view");
                            return false;
                        }

                    }
                }
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }


}
