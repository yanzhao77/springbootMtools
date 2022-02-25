package com.yz.interceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanzhao
 * @version 1.0
 * TODO 过滤器链
 * @date 2022/2/25 10:44
 */
public class FilterChain {
    private List<Filter> filters = new ArrayList<Filter>();
    private Target target;

    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    public void execute(String request) {
        for (Filter filter : filters) {
            filter.execute(request);
        }
        target.execute(request);
    }

    public void setTarget(Target target) {
        this.target = target;
    }
}
