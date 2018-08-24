package com.national.security.community.injection.qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 定义过滤登录事件注解
 * @author: ljn
 * @time: 2018/8/24
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LoginFilter {

    int userDefine() default 0;

}

