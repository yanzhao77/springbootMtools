package com.yz.aopdemo.controller;

import com.yz.aopdemo.log.annotation.AspectLogAnnotation;
import com.yz.aopdemo.log.annotation.MethordRunTime;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanzhao
 * @version 1.0
 * TODO
 * @date 2022/3/7 16:43
 */
@RestController
@RequestMapping("/")
public class testController {

    @AspectLogAnnotation
    @RequestMapping("/test")
    public String getStr() {
        return "hello";
    }

    @MethordRunTime
    @RequestMapping("/test2")
    public String getTest() {
        return "hello";
    }
}
