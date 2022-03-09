package com.yz.securitydemo.base;

import com.yz.securitydemo.entity.SysInfoDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
public class SysInfoConfig {

	@Bean
	public SysInfoDto sysinfodto() {
		return new SysInfoDto();
	}
	   @Bean
	    public BCryptPasswordEncoder bCryptPasswordEncoder(){ 
	        return new BCryptPasswordEncoder();
	    }
}
