package com.yz.securitydemo.service.impl;

import com.yz.securitydemo.service.LoginServiceI;
import org.springframework.stereotype.Service;


@Service
public class LoginServiceImpl implements LoginServiceI {


    @Override
    public String hello() {
        return "hello world";
    }
}
