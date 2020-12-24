package com.nginx.nginx.primary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author WindShadow
 * @date 2020/12/23
 * @since
 */

//@Configuration
public class Con1 {

    @Bean
    public Pt pt() {

        return new Pt();
    }
}
