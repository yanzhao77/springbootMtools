package com.yz.securityjwtdemo.handler;

import com.yz.securityjwtdemo.util.ResponseUtil;
import com.yz.securityjwtdemo.util.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: UnauthorizedEntryPoint
 * @Description: UnauthorizedEntryPoint
 * @Author oyc
 * @Date 2021/1/18 10:58
 * @Version 1.0
 */
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseUtil.out(response, Result.error("未授权统一处理"));
    }
}
