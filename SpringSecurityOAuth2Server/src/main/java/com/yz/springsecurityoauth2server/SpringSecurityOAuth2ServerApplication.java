package com.yz.springsecurityoauth2server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
@EnableEurekaClient
public class SpringSecurityOAuth2ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityOAuth2ServerApplication.class, args);
    }

}
