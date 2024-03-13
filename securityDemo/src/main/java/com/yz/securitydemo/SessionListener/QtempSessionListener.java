package com.yz.securitydemo.SessionListener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import com.yz.securitydemo.entity.SysInfoDto;
import com.yz.securitydemo.entity.Users;
import com.yz.securitydemo.handler.ExceptionHandler;
import com.yz.securitydemo.service.UserServiceI;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import cn.hutool.extra.spring.SpringUtil;


@WebListener
public class QtempSessionListener implements HttpSessionListener {

	@Autowired
	private SysInfoDto sysInfoDto = SpringUtil.getBean(SysInfoDto.class);
	
	private UserServiceI userServiceI = SpringUtil.getBean(UserServiceI.class);

	/**
	 * Logger.
	 */
	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

	/**
	 * session被销毁时触发,如下情况 1.主动调用session.invalidate() 2.session超时
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		// 获取当前用户信息

		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication auth = securityContext.getAuthentication();

		// 退出登錄 刪除緩存
		if (auth != null && auth.getPrincipal() instanceof UserDetails) {
			Users userinfo = (Users) auth.getPrincipal();
			userServiceI.clearQtempByUserId(userinfo.getId());
		} else {
			int userId = Integer.valueOf(sysInfoDto.getUserId());
			userServiceI.clearQtempByUserId(userId);
		}

	}

	/**
	 * session被创建时触发
	 */
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		// 获取当前用户信息
		Users userinfo = null;
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication auth = securityContext.getAuthentication();
		if (auth != null && auth.getPrincipal() instanceof UserDetails) {
			userinfo = (Users) auth.getPrincipal();
			// 添加在线登录记录
			if (userinfo != null && StringUtils.isNotEmpty(userinfo.getUsername())) {
				logger.info(userinfo.getUsername() + "\t" + "已登录");
			}
		}
	}

}
