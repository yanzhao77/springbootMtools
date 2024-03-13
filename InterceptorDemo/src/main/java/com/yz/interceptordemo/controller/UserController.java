package com.yz.interceptordemo.controller;

import com.yz.interceptordemo.config.MyHttpSessionListener;
import com.yz.interceptordemo.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${application.message:Hello World}")
    private String message;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    //模拟user的增删改查操作，假设增删改必须有用户且已登录才可执行
    @RequestMapping("/delete")
    public String delete() {
        return "delete操作";
    }

    @RequestMapping("/insert")
    public String insert() {
        return "insert操作";
    }

    @RequestMapping("/update")
    public String update() {
        return "update操作";
    }

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

    @RequestMapping("/online")
    @ResponseBody
    public Object online() {
        return "当前在线人数：" + MyHttpSessionListener.online + "人";
    }

    @RequestMapping("/logger")
    @ResponseBody
    public Object foo() {
        logger.info("打印日志----------------------");
        return "logger";
    }

    @RequestMapping("/index")
    @ResponseBody
    public Object index(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute("zxc", "zxc");
        return "index";
    }

    @GetMapping("/asd/{name}")
    public String welcome(@PathVariable String name, Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", this.message);
        return "welcome";
    }
}
