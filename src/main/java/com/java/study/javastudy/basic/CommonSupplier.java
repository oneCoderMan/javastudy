package com.java.study.javastudy.basic;

/**
 * @Author： yijun
 * @DATE: 2023/9/17 18:23
 * @Description
 * 自定义Supplie，只能有一个方法
 */
@FunctionalInterface
public interface CommonSupplier<T> {
    T get() throws Exception;
}
