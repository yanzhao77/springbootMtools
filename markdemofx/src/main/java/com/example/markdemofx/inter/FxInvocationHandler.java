package com.example.markdemofx.inter;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;
import java.lang.reflect.InvocationHandler;

@Component
@Inherited
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FxInvocationHandler {

}
