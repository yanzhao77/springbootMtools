package com.example.markdemofx.inter.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


@Slf4j
@Aspect
@Component
public class LogAspect {


    @Pointcut("@annotation(com.example.markdemofx.inter.AspectLogAnnotation)")
    public void logAspect() {

    }

    /**
     * Method start log.
     *
     * @param joinPoint JoinPoint
     * @throws Exception java.lang.Exception
     */
    @Before("logAspect()")
    public void doBefore(JoinPoint joinPoint) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(joinPoint.getTarget().getClass().getName());
        sb.append(joinPoint.getSignature().getName());
        log.info(sb.toString());
        System.out.println(1111);
    }

    /**
     * Method end log.
     *
     * @param joinPoint JoinPoint
     * @throws Exception java.lang.Exception
     */
    @After("logAspect()")
    public void doAfter(JoinPoint joinPoint) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(joinPoint.getTarget().getClass().getName());
        sb.append(joinPoint.getSignature().getName());
        log.info(sb.toString());
        System.out.println(2222);
    }

    /**
     * Exception log.
     *
     * @param joinPoint JoinPoint
     * @param e         Throwable
     * @throws Exception java.lang.Exception
     */
    @AfterThrowing(pointcut = "logAspect()", throwing = "e")
    public void doAfterException(JoinPoint joinPoint, Throwable e) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(e.getClass().getName());
        sb.append(getStackMsg(e));
        log.info(sb.toString());
        System.out.println(333333);

    }

    public static String getStackMsg(Throwable e) {
        StringBuffer sb = new StringBuffer();
        StackTraceElement[] stackArray = e.getStackTrace();
        for (int i = 0; i < stackArray.length; i++) {
            StackTraceElement element = stackArray[i];
            sb.append(element.toString());
            sb.append("\n\t");
        }
        return sb.toString();
    }
}
