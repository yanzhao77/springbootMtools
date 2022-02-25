package com.yz.javamail.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "email")
// 顺序读取
@PropertySource(value = {"classpath:emailConfig.properties", "file:${config.path}"}, encoding = "utf-8", ignoreResourceNotFound = true)
public class EMailProperties {
    //邮件接收者的地址
    private String toAddress;
    // 是否需要身份验证
    private boolean validate = false;

    private static final Charset DEFAULT_CHARSET;
    private String host;
    private Integer port;
    private String username;
    private String password;
    private String protocol = "smtp";
    private Charset defaultEncoding;
    private Map<String, String> properties;
    private String jndiName;

    public EMailProperties() {
        this.defaultEncoding = DEFAULT_CHARSET;
        this.properties = new HashMap<>();
    }

    static {
        DEFAULT_CHARSET = StandardCharsets.UTF_8;
    }

}
