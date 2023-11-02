package com.java.study.basic.annotation;

/**
 * @Author： yijun
 * @DATE: 2023/10/15 15:49
 * @Description
 */
@MyAnnotation(getValue = "annotation on class")
public class AnnotationDemo {
    @MyAnnotation(getValue = "annotation on field")
    public String name;

    @MyAnnotation(getValue = "annotation on method")
    public String getName() {
        return name;
    }
}
