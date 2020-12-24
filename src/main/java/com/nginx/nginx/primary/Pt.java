package com.nginx.nginx.primary;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @author WindShadow
 * @date 2020/12/23
 * @since
 */

//@Primary
//@Service
public class Pt implements TestInt{

    @Override
    public String wo() {
        return "ptpt";
    }
}
