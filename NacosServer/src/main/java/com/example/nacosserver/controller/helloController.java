package com.example.nacosserver.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class helloController {

    @NacosValue(value = "${service.name:1}", autoRefreshed = true)
    private String serverName;

    @RequestMapping(value = "/test", method = GET)
    @ResponseBody
    public String get() {
        return serverName;
    }
}
