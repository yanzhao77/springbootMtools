package com.yz.aopdemo.log.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MethodRynTimeAop {

    @Around("@annotation(com.yz.aopdemo.log.annotation.MethordRunTime)")
    public Object interceptor(ProceedingJoinPoint point) {
        long startTime = System.currentTimeMillis();
        Object result = null;
        try {
            result = point.proceed();
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            log.error(e.getMessage(), e);
            throw new RuntimeException();
        }

        //记录程序运行时间
        log.info(point.getTarget().getClass().getName() + "." + point.getSignature().getName() + " じっこうじかん： " + (System.currentTimeMillis() - startTime) + "ms");

        if ((System.currentTimeMillis() - startTime) > 1500) {
            log.warn("[" + point.getTarget().getClass().getName() + "." + point.getSignature().getName() + "]" + " 実行時間がより大きい[1500ms]");
        }

        System.out.println(12334456);
        return result;
    }

}
