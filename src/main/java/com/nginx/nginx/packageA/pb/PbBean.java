package com.nginx.nginx.packageA.pb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author WindShadow
 * @date 2020/12/16
 * @since
 */

@Slf4j
@Component
public class PbBean {

    private void todo(String name) {

        System.out.println("todo" + name);
    }

    public void todo2(String name) {

        System.out.println("todo2"+name);
    }

    @PostConstruct
    public void init() {

        log.info("PbBean===初始化===");
    }

}
