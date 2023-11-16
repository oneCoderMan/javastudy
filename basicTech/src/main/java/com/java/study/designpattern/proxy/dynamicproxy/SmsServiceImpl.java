package com.java.study.designpattern.proxy.dynamicproxy;

/**
 * @Author： yijun
 * @DATE: 2023/11/16 23:55
 * @Description
 */
public class SmsServiceImpl implements SmsService {
    @Override
    public String send(String message) {
        System.out.println("[jdk proxy] real object send message:" + message);
        return message;
    }
}
