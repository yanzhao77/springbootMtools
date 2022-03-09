package com.yz.securitydemo.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionTimeoutFilter implements Filter {

    public void destroy() {
    }

    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String url = httpRequest.getRequestURL().toString();
        HttpSession session = httpRequest.getSession(false);
        if (url.contains("login.html") || url.contains("/login") || url.contains("/filterError")
                || url.contains("/resources")) {
            chain.doFilter(request, response);
        } else if (session == null) {
            throw new ServletException();
        } else {
            chain.doFilter(request, response);
        }
    }
}
