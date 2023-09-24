package com.java.study.designpattern.somtech;

import org.springframework.stereotype.Component;

/**
 * @Author： yijun
 * @DATE: 2023/9/23 21:41
 * @Description
 */
@Component(value = "devideOpt")
public class DevideOpt implements Opt {
    @Override
    public int apply(int a, int b) {
        return a / b;
    }
}
