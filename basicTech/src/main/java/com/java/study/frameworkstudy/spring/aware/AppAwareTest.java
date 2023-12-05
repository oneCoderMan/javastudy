package com.java.study.frameworkstudy.spring.aware;

import org.springframework.context.support.GenericApplicationContext;

/**
 * @Author： yijun
 * @DATE: 2023/12/4 22:51
 * @Description
 */
public class AppAwareTest {
    /**
     * ApplicationContextAware
     * @param args
     */
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        // 首先创建AwareBean对象，然后会调用BeanNameAware接口
        // ApplicationContextAware将注册的容器传递进去
        // 先回调实现的aware接口，在回调InitializingBean接口的方法
        context.registerBean("myBean", AwareBean.class);

        context.refresh();
        context.close();

    }
}
