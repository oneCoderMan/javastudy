package com.java.study.designpattern.proxy.dynamicproxy;

/**
 * @Author： yijun
 * @DATE: 2023/11/16 23:59
 * @Description
 */
public class DyClient {
    public static void main(String[] args) {
        // 将动态代理生成的 class 保存到磁盘
        // 可以看到生成的代理类对象，在com.sun.proxy路径下面
        // 也可以借助Arthas 反编译代理类字节码文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        SmsService smsService = (SmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
        smsService.send("java");
        System.out.println("=====");
        smsService.send2("C++");
    }
}
