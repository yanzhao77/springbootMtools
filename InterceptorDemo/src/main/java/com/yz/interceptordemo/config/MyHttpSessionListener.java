package com.yz.interceptordemo.config;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

/**
 * @author yanzhao
 * @version 1.0
 * TODO
 * @date 2022/2/25 14:01
 */
public class MyHttpSessionListener implements HttpSessionListener {
    public static int online = 0;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("创建session");
        online++;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("销毁session");

    }

}
