package com.yz.springsecurityoauth2server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author yanzhao
 * @version 1.0
 * TODO
 * @date 2022/3/3 9:34
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //拦截所有请求
                .anyRequest()
                .authenticated()
                .and()
                //spring secuity提供了requestMatchers接口，等价于http.authorizeRequests().anyRequest().access("permitAll");
                //提供资源，访问/user需要权限认证
                .requestMatchers()
                .antMatchers("/user/**");
    }
}

