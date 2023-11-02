package com.java.study.basic.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Author： yijun
 * @DATE: 2023/10/15 15:40
 * @Description
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String getValue();
}
