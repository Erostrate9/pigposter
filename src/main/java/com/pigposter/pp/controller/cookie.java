package com.pigposter.pp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.*;
import java.util.*;

@RestController
@RequestMapping("/cookie")
public class cookie{
    static public String invParse(String s) throws Exception
    {
        byte[] sd = Base64.getDecoder().decode(s);
        String k = new String(sd, "UTF-8");

        k = new StringBuffer(k).reverse().toString();

        sd = Base64.getDecoder().decode(k);
        k = new String(sd,"UTF-8");
        sd = Base64.getDecoder().decode(k);
        k = new String(sd,"UTF-8");
        return k;
    }
    @GetMapping("/")
    static public String setCookie(String username,String password,HttpServletResponse response) throws Exception
    {
        // 创建一个 cookie对象
        //if(username.equals("")) username = "123";
        username = Base64.getEncoder().encodeToString(username.getBytes("UTF-8"));
        username = Base64.getEncoder().encodeToString(username.getBytes("UTF-8"));
        username = new StringBuffer(username).reverse().toString();
        username = Base64.getEncoder().encodeToString(username.getBytes("UTF-8"));
        Cookie cookie = new Cookie("unm", username);
        //将cookie对象加入response响应
        cookie.setPath("/");
        response.addCookie(cookie);
        password = Base64.getEncoder().encodeToString(password.getBytes("UTF-8"));
        password = Base64.getEncoder().encodeToString(password.getBytes("UTF-8"));
        password = new StringBuffer(password).reverse().toString();
        password = Base64.getEncoder().encodeToString(password.getBytes("UTF-8"));
        cookie = new Cookie("ppp",password);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "Username is changed!";
    }
}
