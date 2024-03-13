package com.softroad.testdbdemo;

import com.softroad.testdbdemo.entity.Login;
import com.softroad.testdbdemo.mapper.LoginMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Test {

    private final ApplicationContext applicationContext;

    static LoginMapper loginMapper;

    public Test(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void getAllUser() {
        loginMapper = applicationContext.getBean(LoginMapper.class);
        List<Login> logins = loginMapper.selectAllUser();
        logins.forEach(System.out::println);
        SpringApplication.exit(applicationContext);
    }

    void contextLoads() {
        String username = "PRF101C";
        String pwd = "0";
        List<Login> logins = loginMapper.selectByKeys(username, pwd);
        logins.forEach(System.out::println);
    }


    void test_1() {
        String logins = loginMapper.selectMaxSeqNo();
        System.out.println(logins);
    }


    void test_2() {
        List<Login> logins = loginMapper.selectAllUser();
        logins.forEach(System.out::println);
    }
}
