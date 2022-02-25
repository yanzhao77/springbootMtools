package com.yz.interceptor;

/**
 * @author yanzhao
 * @version 1.0
 * TODO 过滤管理器。
 * @date 2022/2/25 10:45
 */
public class FilterManager {

    FilterChain filterChain;

    public FilterManager(Target target) {
        filterChain = new FilterChain();
        filterChain.setTarget(target);
    }

    public void setFilter(Filter filter) {
        filterChain.addFilter(filter);
    }

    public void filterRequest(String request) {
        filterChain.execute(request);
    }
}
