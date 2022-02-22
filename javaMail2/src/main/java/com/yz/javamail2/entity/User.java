package com.yz.javamail2.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "user", ignoreUnknownFields = false)
@PropertySource("classpath:user.properties")
public class User {
    private String name;
    private String age;
    private String pwd;
}
