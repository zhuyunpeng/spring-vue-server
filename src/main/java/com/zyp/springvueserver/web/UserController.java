package com.zyp.springvueserver.web;

import com.zyp.springvueserver.model.User;
import com.zyp.springvueserver.security.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;

/**
 * Created by zhuyunpeng on 2018/06/01
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/login")
    public String login(User user) throws ServletException {
        String name = user.getUsername();
        String pass = user.getPassword();
        if (!"admin".equals(name)) {
            throw new ServletException("no such user");
        }
        if (!"1234".equals(pass)) {
            throw new ServletException("wrong password");
        }
        return JwtUtil.getToken(name);
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