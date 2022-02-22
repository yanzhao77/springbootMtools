package com.example.eurekaconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Aaron
 * @Date 2019/7/10 21:23
 **/
@RestController
@RequestMapping("/hi")
public class HelloConsumer {
    @Autowired
    private RestTemplate restTemplate;
    private final String providerUrl = "http://PROVIDER1/hello";
    private final String providerUrl2 = "http://PROVIDER2/hello";

    @RequestMapping("/pro")
    public String provider() {
        return restTemplate.getForObject(providerUrl, String.class);
    }
    @RequestMapping("/pro2")
    public String provider2() {
        return restTemplate.getForObject(providerUrl2, String.class);
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello,I am consumer,nice to meet you!";
    }
}

