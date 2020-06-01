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
     * 名称
     */
    String name() default "";

    /**
     * 组别
     */
    String group() default XInjectConstant.LIN_INJECT_GROUP_DEFAULT;

    /**
     * 优先级（优先级越高，越早执行）
     */
    int priority() default 0;

    /**
     * 是否在主线程里进行
     */
    boolean inMainThread() default true;

}
