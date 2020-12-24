package com.nginx.nginx.jicheng;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * @author WindShadow
 * @date 2020/12/18
 * @since
 */

@Slf4j
public class Parent {

    @Autowired
    private Temp temp;

    public void toSay() {

        temp.say();
    }

    @PostConstruct
    private void init() {

        log.info("===Parent init===");
    }
}
