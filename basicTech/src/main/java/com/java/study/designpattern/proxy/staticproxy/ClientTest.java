package com.java.study.designpattern.proxy.staticproxy;

/**
 * @Author： yijun
 * @DATE: 2023/11/16 23:43
 * @Description
 */
public class ClientTest {
    public static void main(String[] args) {
        SmsService smsService = new SmsServiceImpl();
        SmsProxy smsProxy = new SmsProxy(smsService);
        smsProxy.send("java proxy");
    }
}
