package com.java.study.basic.annotation;

import java.lang.reflect.Field;

/**
 * @Author： yijun
 * @DATE: 2023/11/2 23:46
 * @Description
 */
public class AnnotationTest {
    public static void main(String[] args) throws NoSuchFieldException {
        System.out.println("test");
        // 获取类上的注解
        Class<AnnotationDemo> clazz = AnnotationDemo.class;
        MyAnnotation annotationOnClass = clazz.getAnnotation(MyAnnotation.class);
        System.out.println(annotationOnClass.getValue());

        // 获取成员变量上的注解
        Field name = clazz.getField("name");
        MyAnnotation annotationOnField = name.getAnnotation(MyAnnotation.class);
        System.out.println(annotationOnField.getValue());

    }
}
