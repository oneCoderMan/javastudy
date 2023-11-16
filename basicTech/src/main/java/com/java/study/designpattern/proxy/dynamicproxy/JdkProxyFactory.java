package com.java.study.designpattern.proxy.dynamicproxy;

import java.lang.reflect.Proxy;

/**
 * @Author： yijun
 * @DATE: 2023/11/16 23:58
 * @Description 获取代理对象的工厂类
 */
public class JdkProxyFactory {
    public static Object getProxy(Object target) {
        return Proxy.newProxyInstance(
                // 目标类的类加载器
                target.getClass().getClassLoader(),
                // 代理需要实现的接口，可指定多个
                target.getClass().getInterfaces(),
                // 代理对象对应的自定义 InvocationHandler
                new DebugInvocationHandler(target)
        );
    }
}
