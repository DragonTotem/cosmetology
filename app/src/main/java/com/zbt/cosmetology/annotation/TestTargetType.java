package com.zbt.cosmetology.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TestTargetType {
    String value() default "定义在类、接口或枚举上注解元素value的默认值";
}
