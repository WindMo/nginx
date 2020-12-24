package com.nginx.nginx.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author WindShadow
 * @date 2020/12/23
 * @since
 */

public class CondB implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return ConTest.n2 == 1;
    }
}
