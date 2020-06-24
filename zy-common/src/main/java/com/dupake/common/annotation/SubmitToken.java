package com.dupake.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName SubmitToken
 * @Description 防重复提交注解
 * @Author dupake
 * @Date 2020/6/24 11:30
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SubmitToken {

    boolean value() default true;
}