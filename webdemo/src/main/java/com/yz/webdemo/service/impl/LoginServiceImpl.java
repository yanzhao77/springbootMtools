package com.yz.webdemo.service.impl;

import com.yz.webdemo.service.LoginServiceI;
import org.springframework.stereotype.Service;


@Service
public class LoginServiceImpl implements LoginServiceI {


    @Override
    public String hello() {
        return "hello world";
    }
}
