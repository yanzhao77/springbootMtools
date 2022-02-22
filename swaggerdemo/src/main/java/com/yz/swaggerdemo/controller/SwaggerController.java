package com.yz.swaggerdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tool/swagger")
public class SwaggerController {

    @GetMapping()
    public String index() {
        return "/swagger-ui/index.html";
    }

}