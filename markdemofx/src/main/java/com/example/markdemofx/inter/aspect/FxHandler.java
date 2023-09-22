package com.example.markdemofx.inter.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class FxHandler {

    @Pointcut("@annotation(com.example.markdemofx.inter.FxInvocationHandler)")
    public void fxHandler() {
        System.out.println(111);
    }
    /**
     * Method start log.
     *
     * @param joinPoint JoinPoint
     * @throws Exception java.lang.Exception
     */
    @Before("fxHandler()")
    public void doBefore(JoinPoint joinPoint) throws Exception {
        System.out.println(joinPoint.getArgs());
    }

    /**
     * Method end log.
     *
     * @param joinPoint JoinPoint
     * @throws Exception java.lang.Exception
     */
    @After("fxHandler()")
    public void doAfter(JoinPoint joinPoint) throws Exception {
        System.out.println(joinPoint.getArgs());
    }
}
