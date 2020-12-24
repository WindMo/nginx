package com.nginx.nginx.primary;

import org.springframework.stereotype.Service;

/**
 * @author WindShadow
 * @date 2020/12/23
 * @since
 */
@Service
public class Pt3 extends APt2 implements TestInt{
    @Override
    public String wo() {
        return "pt3pt3";
    }
}
