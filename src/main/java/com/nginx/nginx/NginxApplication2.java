package com.nginx.nginx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

//@ServletComponentScan(basePackages = "com.nginx.nginx.web")
//@SpringBootApplication
public class NginxApplication2 {

    public static void main(String[] args) {
        SpringApplication.run(NginxApplication2.class, args);
    }

}
