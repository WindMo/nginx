package com.nginx.nginx.primary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author WindShadow
 * @date 2020/12/23
 * @since
 */

//@Configuration
public class Con2 {

    @Primary
    @Bean("con2pt")
    public Pt pt() {

        return new Pt();
    }
}
