package com.example.eurekaprovider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class HelloController {
    private final Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/text", method = RequestMethod.GET)
    public String text() {
        return "HELLO WORD";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello,I am num one provider,nice to meet you!";
    }
}