package com.nginx.nginx.condition;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author WindShadow
 * @date 2020/12/23
 * @since
 */

@Slf4j
@Conditional(value = {CondA.class,CondB.class})
@Component
public class ConTest {

    public static final int n1 = 2;
    public static final int n2 = 1;

    @PostConstruct
    public void init() {

        log.info("===ConTestConTestConTestConTestConTest");
    }
}
