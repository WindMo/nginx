package com.nginx.nginx.web;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author WindShadow
 * @version 2020/12/1.
 */

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class ErrFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info(this.getClass().toString() + "=== init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        log.info("抛异常");
        throw new RuntimeException("===err===");
    }
}
