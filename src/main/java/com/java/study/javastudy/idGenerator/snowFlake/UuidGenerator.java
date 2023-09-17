package com.java.study.javastudy.idGenerator.snowFlake;

import java.util.UUID;

/**
 * @Author： yijun
 * @DATE: 2023/9/17 08:58
 * @Description
 */
public class UuidGenerator {
    public static String genUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
