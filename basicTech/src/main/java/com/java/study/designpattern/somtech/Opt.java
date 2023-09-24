package com.java.study.designpattern.somtech;

/**
 * @Author： yijun
 * @DATE: 2023/9/23 21:39
 * @Description
 */
public interface Opt {
    /**
     * 抽象的方法，用于定义策略接口
     * @param a
     * @param b
     * @return
     */
    int apply(int a, int b);
}
