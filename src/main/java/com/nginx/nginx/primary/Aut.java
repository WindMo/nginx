package com.nginx.nginx.primary;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author WindShadow
 * @date 2020/12/23
 * @since
 */
@Slf4j
//@Service
public class Aut {

//    @Autowired
    @Resource
    private TestInt testInt;

    @PostConstruct
    public void init() {

        log.info(testInt.wo());
    }
}
