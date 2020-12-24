package com.nginx.nginx.aop;

import org.springframework.stereotype.Component;

/**
 * @author WindShadow
 * @date 2020/12/16
 * @since
 */
//@Component
public class Target222 extends Target{

    @Override
    public void todo() {

        System.out.println("Target2222 todo");
        todo2();
    }

    private void todo2() {

        System.out.println("Target2222 private todo2");
    }
}
