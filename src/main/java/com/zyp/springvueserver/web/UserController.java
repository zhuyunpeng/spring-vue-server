package com.zyp.springvueserver.web;

import com.zyp.springvueserver.config.WeixinConfig;
import com.zyp.springvueserver.model.User;
import com.zyp.springvueserver.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhuyunpeng on 2018/06/01
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    WeixinConfig weixinConfig;

    @PostMapping("/login")
    public boolean login(User user, HttpServletResponse response) throws ServletException {
        String name = user.getUsername();
        String pass = user.getPassword();
        if (!"admin".equals(name)) {
//            throw new ServletException("no such user");
            return false;
        }
        if (!"1234".equals(pass)) {
//            throw new ServletException("wrong password");
            return false;
        }
        setCookies(JwtUtil.getToken(name), response);
        return true;
    }

    @PostMapping("/check")
    public boolean check(HttpServletRequest request) {
        System.out.println("****************");
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("test_vue".equals(cookie.getName())) {
                    return JwtUtil.checkToken(cookie.getValue());
                }
            }
        }
        return false;
    }

    private void setCookies(String token, HttpServletResponse response) {
        Cookie cookie = new Cookie("test_vue", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @GetMapping("/success")
    public String success() {
        return "login success";
    }

    @GetMapping("/getEmail")
    public String getEmail() {
        return "xxxx@qq.com";
    }

    @GetMapping("/getLoginUrl")
    public Map<String, String> getLoginUrl() {
        Map<String, String> result = new HashMap<>();
        result.put("weixinUrl", weixinConfig.getPcRequestUrl());
        result.put("gitlabUrl", "http://gitlab.com");
        return result;
    }

    @PostMapping("/logout")
    public boolean logout(HttpServletRequest request, HttpServletResponse response) {
        //退出系统，清空cookie信息，并跳转至登录页面
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("test_vue".equals(c.getName())) {
                    c.setPath("/");
                    c.setMaxAge(0);
                    c.setValue(null);
                    response.addCookie(c);
                    return true;
                }
            }
        }

        return false;
    }
}