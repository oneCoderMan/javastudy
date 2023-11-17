package com.java.study.designpattern.proxy.cglibproxy;

/**
 * @Author： yijun
 * @DATE: 2023/11/17 08:04
 * @Description
 */
public class AliSmsService {
    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }
    public String send2(String message) {
        System.out.println("send message v2:" + message);
        return message;
    }
}
