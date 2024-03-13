package com.yz.interceptordemo.controller;

import com.yz.interceptordemo.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

/**
 * @author yanzhao
 * @version 1.0
 * TODO
 * @date 2022/2/25 15:01
 */

@RestController
@RequestMapping("/")
public class loginController {
    @RequestMapping("/login")
    public String login(HttpSession session) {
        //模拟登录效果,不查询数据库
        User user = new User("admin", "男");
        session.setAttribute("user", user);
        return "登录成功";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        //登出
        session.removeAttribute("user");
        return "登出成功";
    }
}
