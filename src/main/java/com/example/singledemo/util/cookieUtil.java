package com.example.singledemo.util;

import org.springframework.context.annotation.Configuration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class cookieUtil {


    /**
     * 设置Cookie
     * @param response
     * @param key cookie 名字
     * @param value cookie 值
//     * @param maxAge cookie生命周期
//     * @param path    cookie传递路径
//     * @param domain  cookie域
     */
    public static void addCookie(HttpServletResponse response, String key, String value/*,int maxAge,String path,String domain*/) {

        Cookie cookie = new Cookie(key, value);
//        cookie.setPath(path);
//        cookie.setDomain(domain);
//        if (maxAge > 0) {
//            cookie.setMaxAge(maxAge);
//        }
        response.addCookie(cookie);
    }


    /**
     * 根据cookiename查询cookie值
     * @param request
     * @param name
     * @return  返回cookie值
     */
    public static String getCookieByName(HttpServletRequest request, String name){
        Cookie cookies[]=request.getCookies();
        if (cookies != null){
            for (Cookie cookie:cookies){
                if (cookie.getName().equals(name)){
                    return cookie.getValue();
                }
            }
        }
        return null;

    }


}
