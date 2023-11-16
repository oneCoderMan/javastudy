package com.java.study.designpattern.proxy.staticproxy;

/**
 * @Author： yijun
 * @DATE: 2023/11/16 23:37
 * @Description
 */
public interface SmsService {
    /**
     * 发送短信的接口
     * @param message 发送的消息
     * @return
     */
    String send(String message);
}
