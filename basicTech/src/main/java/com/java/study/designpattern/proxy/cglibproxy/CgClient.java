package com.java.study.designpattern.proxy.cglibproxy;

/**
 * @Author： yijun
 * @DATE: 2023/11/17 08:09
 * @Description
 */
public class CgClient {
    public static void main(String[] args) {
        AliSmsService aliSmsService = (AliSmsService) CglibProxyFactory
                .getProxy(AliSmsService.class);
        aliSmsService.send("Java");
        aliSmsService.send2("C++");
    }
}
