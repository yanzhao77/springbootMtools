package com.yz.interceptor;

/**
 * @author yanzhao
 * @version 1.0
 * TODO 实体过滤器
 * @date 2022/2/25 10:42
 */
public class AuthenticationFilter implements Filter{
    @Override
    public void execute(String request) {
        System.out.println("Authenticating request: " + request);
    }
}
