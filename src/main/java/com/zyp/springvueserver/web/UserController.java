package com.zyp.springvueserver.web;

import com.zyp.springvueserver.model.User;
import com.zyp.springvueserver.security.JwtUtil;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhuyunpeng on 2018/06/01
 **/
@RestController
@RequestMapping("/user")
public class UserController {

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
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("uid".equals(cookie.getName())) {
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
}