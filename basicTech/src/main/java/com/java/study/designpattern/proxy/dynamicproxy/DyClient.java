package com.java.study.designpattern.proxy.dynamicproxy;

/**
 * @Author： yijun
 * @DATE: 2023/11/16 23:59
 * @Description
 */
public class DyClient {
    public static void main(String[] args) {
        SmsService smsService = (SmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
        smsService.send("java");
        System.out.println("=====");
        smsService.send2("C++");
    }
}
