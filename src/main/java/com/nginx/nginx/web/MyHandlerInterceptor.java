package com.nginx.nginx.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author WindShadow
 * @version 2020/11/18.
 */

@Slf4j
public class MyHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("请求处理前=========================");
//        log.info("remoteIP: {}",request.getRemoteAddr());
        log.info("NGINX: {}",request.getHeader("NGINX"));
        log.info("URL: {}",request.getRequestURL());
        log.info("URI: {}",request.getRequestURI());
        log.info("ServletPath: {}",request.getServletPath());
        log.info("ContextPath: {}",request.getContextPath());
        log.info("RealPath: {}",request.getRealPath("/"));
        request.getParameterMap().forEach((k,v) -> log.info("param: k = {}, v = {}",k,v));

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        log.info("响应Location: {}",response.getHeader("Location"));
        log.info("===================================");
    }
}
