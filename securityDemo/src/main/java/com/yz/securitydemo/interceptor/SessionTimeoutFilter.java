package com.yz.securitydemo.interceptor;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

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
