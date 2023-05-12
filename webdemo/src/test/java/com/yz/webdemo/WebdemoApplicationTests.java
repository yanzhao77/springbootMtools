package com.yz.webdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebdemoApplicationTests {

    @Test
    void contextLoads() {
        String aa="\"";

        System.out.println(aa);
    }
    @Test
    void strTest() {
        String aa=String.format("%1$"+12+ "s", " ");

        System.out.println(aa);
    }
}
