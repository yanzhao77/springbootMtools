package com.yz.interceptor;

/**
 * @author yanzhao
 * @version 1.0
 * TODO
 * @date 2022/2/25 10:44
 */
public class Target {
    public void execute(String request) {
        System.out.println("Executing request: " + request);
    }
}
