package com.yz.interceptor;

/**
 * @author yanzhao
 * @version 1.0
 * TODO
 * @date 2022/2/25 10:43
 */
public class DebugFilter implements Filter{
    @Override
    public void execute(String request) {
        System.out.println("request log: " + request);
    }
}
