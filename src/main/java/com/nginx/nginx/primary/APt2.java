package com.nginx.nginx.primary;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author WindShadow
 * @date 2020/12/23
 * @since
 */

@Component("aPt2")
//@Service
public class APt2 implements TestInt{
    @Override
    public String wo() {
        return "pt2pt2";
    }
}
