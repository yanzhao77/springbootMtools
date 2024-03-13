package com.yz.securitydemo.processor;

import com.yz.securitydemo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * 配置
 *
 * @author yanzhao
 */
@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
	UserServiceImpl uServiceImpl;

	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//		.antMatchers("/login","/toLogin","/login.html").permitAll()
//		.anyRequest().authenticated()
//		.and()
//		.formLogin()
//				.defaultSuccessUrl("/index", true)
//		.and().sessionManagement().invalidSessionUrl("/session/invalid") //session过期后跳转的URL
//    	.and().csrf().disable(); // 关闭csrf防护
//
//	}
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(uServiceImpl).passwordEncoder(bcryptPasswordEncoder);
//	}
//
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		// TODO Auto-generated method stub
//		web.ignoring().antMatchers("/css/**","/js/**","/img/**");
//	}
	

}
