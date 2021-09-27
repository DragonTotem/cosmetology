package com.zbt.cosmetology.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestTargetMethod {
    /**
     * 定义注解的一个元素，并给定一个默认值
     * @return
     */
    String value() default "我是定义在方法上的注解元素value的默认值";
}
