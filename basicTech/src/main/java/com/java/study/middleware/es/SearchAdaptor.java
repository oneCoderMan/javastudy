package com.java.study.middleware.es;

/**
 * @Author： yijun
 * @DATE: 2025/3/19 23:19
 * @Description 原始的抽象查询方法
 * T 是查询的参数
 * U 是返回的数据
 */
public interface SearchAdaptor<T, U> {
    /**
     * 定义一个通用的查询方法
     * @param data
     * @return
     */
    U query(T data);

}
