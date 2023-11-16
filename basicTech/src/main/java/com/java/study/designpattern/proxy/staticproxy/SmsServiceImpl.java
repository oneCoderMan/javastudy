package com.java.study.designpattern.proxy.staticproxy;

/**
 * @Author： yijun
 * @DATE: 2023/11/16 23:37
 * @Description
 */
public class SmsServiceImpl implements SmsService {
    @Override
    public String send(String message) {
        System.out.println("real object send message:" + message);
        return message;
    }
}
