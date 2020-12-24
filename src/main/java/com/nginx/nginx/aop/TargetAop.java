package com.nginx.nginx.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author WindShadow
 * @date 2020/12/16
 * @since
 */

@Slf4j
@Aspect
@Component
public class TargetAop {

    @Pointcut("execution(private * com.nginx.nginx.aop.Target.*(..))")
    public void a(){}

    @Around("a()")
    public Object round(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        log.info("target" + proceedingJoinPoint.getTarget().getClass().toString());
        log.info("this" + proceedingJoinPoint.getThis().getClass().toString());

        Signature signature = proceedingJoinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        log.info("method" + methodSignature.getMethod().toString());
        return proceedingJoinPoint.proceed();
    }
}
