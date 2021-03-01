package com.nginx.nginx.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author WindShadow
 * @date 2021-3-1 0001
 * @since
 */

@Slf4j
@WebFilter(urlPatterns = "/**")
@Component
public class CorossFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

        log.info("CorossFilter==========");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 响应标头指定 指定可以访问资源的URI路径
//        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Origin", "*");
        //响应标头指定响应访问所述资源到时允许的一种或多种方法
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");

        //设置 缓存可以生存的最大秒数
        response.setHeader("Access-Control-Max-Age", "3600");

        //设置  受支持请求标头
        response.setHeader("Access-Control-Allow-Headers", "*");

        // 指示的请求的响应是否可以暴露于该页面。当true值返回时它可以被暴露
        response.setHeader("Access-Control-Allow-Credentials","true");

        chain.doFilter(servletRequest, servletResponse);
    }
}
