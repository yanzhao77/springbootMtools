package com.yz.aopdemo.log.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Aspect log annotation interface.
 * 
 * @author SoftRoad
 */
@Target({ ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface AspectLogAnnotation {

  /**
   * Get description information.
   * 
   * @return description information
   */
  String description() default "";
}
