package com.java.study.frameworkstudy.springmvc.init;

import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;

/**
 * @Author： yijun
 * @DATE: 2023/12/23 10:13
 * @Description
 */
public class AppTest {
    public static void main(String[] args) {
        AnnotationConfigServletWebServerApplicationContext context =
                new AnnotationConfigServletWebServerApplicationContext(WebConfig.class);
    }
}
