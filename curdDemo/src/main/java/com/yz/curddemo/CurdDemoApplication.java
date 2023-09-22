package com.yz.curddemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@MapperScan("com.yz.curddemo.mapper")
@ComponentScan(basePackages = {"com.yz.curddemo.bean"})
public class CurdDemoApplication
{

    public static void main(String[] args) {
        SpringApplication.run(CurdDemoApplication.class, args);
    }

}
