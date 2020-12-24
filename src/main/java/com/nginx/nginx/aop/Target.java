package com.nginx.nginx.aop;

import org.springframework.stereotype.Component;

/**
 * @author WindShadow
 * @date 2020/12/16
 * @since
 */
@Component
public class Target {

    public void todo() {

        System.out.println("Target todo");
        todo2();
    }

    private void todo2() {

        System.out.println("Target private todo2");
    }
}
