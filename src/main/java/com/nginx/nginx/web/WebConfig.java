package com.nginx.nginx.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author WindShadow
 * @version 2020/11/18.
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new MyHandlerInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new MyHandlerInterceptor());
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/downloadpage").setViewName("js-redirect.html");
    }
}
