package com.yz.webdemo.controller;

import com.yz.webdemo.entity.Users;
import com.yz.webdemo.service.CommonServiceI;
import com.yz.webdemo.service.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * Validated // 注意！1) 如果想在参数中使用 @NotNull 这种注解校验，就必须在类上添加 @Validated； 2)
 * 如果方法中的参数是对象类型，则必须要在参数对象前面添加 @Validated
 */
@Validated
@Controller
@RequestMapping("/")
public class loginController {

    @Autowired
    CommonServiceI commonServiceI;

    @Autowired
    UserServiceI userServiceI;

    @RequestMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        Users userinfo = null;
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication auth = securityContext.getAuthentication();

        if (auth != null) {
            userinfo = (Users) auth.getPrincipal();

        }
        if (StringUtils.isEmpty(userinfo)) {
            modelAndView.setViewName("error");
            modelAndView.addObject("message", "user is not find");
            return modelAndView;
        }
//		modelAndView.addObject("user", userinfo);
//		modelAndView.setViewName("index");
        modelAndView.setViewName("redirect:/adrmen01/init");

        return modelAndView;
    }

    @RequestMapping("/tologin")
    public String tologin() {
        List<LinkedHashMap<String, Object>> stringList = commonServiceI.checkAllLocks();
        System.out.println(stringList);
        return "index";
    }

    @RequestMapping("/index")
    public String toIndex() {
        List<LinkedHashMap<String, Object>> stringList = commonServiceI.checkAllLocks();
        System.out.println(stringList);
        return "index";
    }

    @RequestMapping("/rcIndex")
    public ModelAndView rcIndex() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("rcIndex");
        return modelAndView;
    }

    @RequestMapping("/logout")
    public String logout() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication auth = securityContext.getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(null, null, auth);

            // 推出登錄 刪除緩存
            Users userinfo = (Users) auth.getPrincipal();
            userServiceI.clearQtempByUserId(userinfo.getId());
        }
        return "redirect:/login";
    }

    @RequestMapping("/session/invalid")
    public String sessionInvalid() {
        return "redirect:/logout";
    }

    @RequestMapping("/toUser")
    public ModelAndView init(Model model) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        Users userinfo = null;
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication auth = securityContext.getAuthentication();

        if (auth != null) {
            userinfo = (Users) auth.getPrincipal();
        }
        if (StringUtils.isEmpty(userinfo)) {
            modelAndView.setViewName("error");
            modelAndView.addObject("message", "user is not find");
            return modelAndView;
        }
        modelAndView.addObject("user", userinfo);
        modelAndView.setViewName("user");
        if (userinfo != null) {
            //改造页面，所有menu放在页面，根据role开放
            if ("alluser".equals(userinfo.getMenuus().trim().toLowerCase())) {

                modelAndView = new ModelAndView("redirect:/user/list");
                modelAndView.addObject("pageNum", 1);
                modelAndView.addObject("pageSize", 5);
            }

            if ("touser".equals(userinfo.getMenuus().trim().toLowerCase())) {

                modelAndView = new ModelAndView("redirect:/user/toUser");
                modelAndView.addObject("id", userinfo.getId());
            }

        }
        return modelAndView;
    }
}
