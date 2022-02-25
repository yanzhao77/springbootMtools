package com.yz.interceptor;

/**
 * @author yanzhao
 * @version 1.0
 * TODO
 * @date 2022/2/25 10:46
 */
public class Client {
    FilterManager filterManager;

    public void setFilterManager(FilterManager filterManager) {
        this.filterManager = filterManager;
    }

    public void sendRequest(String request) {
        filterManager.filterRequest(request);
    }
}
