package com.xu.miniapp.config;

import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author CharleyXu Created on 2019/2/15.
 *
 * 接口耗时统计,只在dev环境下使用
 */
@Aspect
@Component
@Profile("dev")
@Order(5)
@Slf4j
public class LogAspectConfig {

    private ThreadLocal<Long> logTime = new ThreadLocal<>();

    /**
     * 定义Pointcut
     */
    @Pointcut("execution(public * com.xu.miniapp.controller.*.*(..))")
    public void webLog() {

    }

    /**
     * 前置通知
     */
    @Before("webLog()")
    public void invokeBefore() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
            .getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("URL : " + request.getRequestURL().toString());
        logTime.set(System.currentTimeMillis());
    }

    /**
     * 后置通知
     */
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void invokeAfter(Object ret) {
        log.info("SPEND_TIME : " + (System.currentTimeMillis() - logTime.get()));
        logTime.set(null);
    }
}
