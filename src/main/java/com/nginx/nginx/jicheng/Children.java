package com.nginx.nginx.jicheng;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author WindShadow
 * @date 2020/12/18
 * @since
 */

@Slf4j
@Component
public class Children extends Parent{

    @PostConstruct
    private void init2() {

        log.info("===Children init===");
    }

    @PostConstruct
    private void init0() {

        log.info("===Children333 init===");
    }
}
