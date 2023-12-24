package com.java.study.frameworkstudy.springboot.launch;

import org.springframework.boot.DefaultBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * @Author： yijun
 * @DATE: 2023/12/24 17:07
 * @Description
 */
public class TestBootRun {
    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication();
        // 添加APP事件监听器
        app.addListeners(i -> System.out.println("当前发布的事件：" + i.getClass()));

        // 获取事件发送器实现类名
        List<String> names = SpringFactoriesLoader.loadFactoryNames(
                SpringApplicationRunListener.class,
                TestBootRun.class.getClassLoader()
        );
        for (String name : names) {
            System.out.println("当前事件发布器名：" + name);

            // 通过构造器获取事件发送器的实例
            Class<?> clazz = Class.forName(name);
            Constructor<?> constructor = clazz.getConstructor(SpringApplication.class, String[].class);
            SpringApplicationRunListener publisher = (SpringApplicationRunListener) constructor.newInstance(app, args);

            // 发布事件 7大事件
            DefaultBootstrapContext bootstrapContext = new DefaultBootstrapContext();
            // 1spring boot 开始启动
            publisher.starting(bootstrapContext);
            // 2环境信息准备完毕
            publisher.environmentPrepared(bootstrapContext, new StandardEnvironment());
            // 3创建 spring 容器，调用初始化器之后发布此事件
            GenericApplicationContext context = new GenericApplicationContext();
            publisher.contextPrepared(context);
            // 4所有 bean definition 加载完毕
            publisher.contextLoaded(context);
            // 5spring 容器初始化完毕（调用 refresh() 方法后）
            context.refresh();
            publisher.started(context, null);
            // 6spring boot 启动完毕
            publisher.ready(context, null);
            // 7启动过程中出现异常，spring boot 启动出错
            publisher.failed(context, new Exception("出错了"));
        }

    }
}
