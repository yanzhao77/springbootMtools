package com.softroad.testdbdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.softroad.testdbdemo.mapper")
@SpringBootApplication
public class TestdbdemoApplication implements CommandLineRunner {
    @Autowired
    Test test;

    public static void main(String[] args) {
        SpringApplication.run(TestdbdemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        test.getAllUser();
    }
}
