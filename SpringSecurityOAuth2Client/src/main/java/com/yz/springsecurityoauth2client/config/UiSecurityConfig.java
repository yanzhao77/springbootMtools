package com.yz.springsecurityoauth2client.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * @author yanzhao
 * @version 1.0
 * TODO
 * @date 2022/3/3 9:19
 */
@Configuration
@EnableOAuth2Sso
public class UiSecurityConfig {

//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.antMatcher("/**")
//                .authorizeRequests()
//                .antMatchers("/", "/login**")
//                .permitAll()
//                .anyRequest()
//                .authenticated();
//    }
}

