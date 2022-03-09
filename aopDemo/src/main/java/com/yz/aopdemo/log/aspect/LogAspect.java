package com.yz.aopdemo.log.aspect;

import com.yz.aopdemo.log.base.ConstantUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LogAspect {

    private static Logger traceLogger = LoggerFactory.getLogger("traceLogger");

    @Pointcut("@annotation(com.yz.aopdemo.log.annotation.AspectLogAnnotation)")
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
        sb.append(ConstantUtil.EMPTY);
        sb.append(joinPoint.getSignature().getName());
        sb.append(ConstantUtil.EMPTY);
        sb.append(ConstantUtil.CHAR_START);
        traceLogger.info(sb.toString());
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
        sb.append(ConstantUtil.EMPTY);
        sb.append(joinPoint.getSignature().getName());
        sb.append(ConstantUtil.EMPTY);
        sb.append(ConstantUtil.CHAR_END);
        traceLogger.info(sb.toString());
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
        sb.append(ConstantUtil.TAB);
        sb.append(ConstantUtil.CHAR_ERRORCODE);
        sb.append(ConstantUtil.COLON);
        sb.append(e.getClass().getName());
        sb.append(ConstantUtil.LINE_BREAK);
        sb.append(ConstantUtil.TAB);
        sb.append(ConstantUtil.TAB);
        sb.append(ConstantUtil.CHAR_ERRORINFO);
        sb.append(ConstantUtil.COLON);
        sb.append(getStackMsg(e));
        traceLogger.info(sb.toString());
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
