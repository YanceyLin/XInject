package com.lin.inject.annotation;

import com.lin.inject.annotation.common.XInjectConstant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface XInject {

    /**
     * 键值
     */
    String id() default "";

    /**
     * 组别
     */
    String group() default XInjectConstant.LIN_INJECT_GROUP_DEFAULT;

}
