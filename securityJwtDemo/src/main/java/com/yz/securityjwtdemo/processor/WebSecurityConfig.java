package com.yz.securityjwtdemo.processor;

import com.yz.securityjwtdemo.filter.TokenAuthenticationFilter;
import com.yz.securityjwtdemo.filter.TokenLoginFilter;
import com.yz.securityjwtdemo.handler.UnauthorizedEntryPoint;
import com.yz.securityjwtdemo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * 配置
 *
 * @author yanzhao
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserServiceImpl uServiceImpl;

    @Autowired
    private BCryptPasswordEncoder bcryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                //未授权处理
                .authenticationEntryPoint(new UnauthorizedEntryPoint())
                .and().formLogin().defaultSuccessUrl("/index", true)
                .and().authorizeRequests()
                .anyRequest().authenticated()
                .and().csrf().disable()// 关闭csrf防护
                .logout().logoutUrl("/logout")
                .and()
                //.addLogoutHandler(new TokenLogoutHandler(tokenManager))
                .addFilter(new TokenLoginFilter(authenticationManager()))
                .addFilter(new TokenAuthenticationFilter(authenticationManager())).httpBasic();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(uServiceImpl).passwordEncoder(bcryptPasswordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // TODO Auto-generated method stub
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
    }


}
