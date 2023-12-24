package com.java.study.frameworkstudy.springboot.launch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Author： yijun
 * @DATE: 2023/12/23 15:23
 * @Description
 */
@Configuration
public class TestSpringApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication spring = new SpringApplication(TestSpringApplication.class);

        // 创建并初始化 Spring 容器
        // 获取 `Bean Definition` 源
        // 来源为 null 的 Bean 是由 Spring 提供的 “内置” Bean
        ConfigurableApplicationContext context = spring.run(args);
        Arrays.stream(context.getBeanDefinitionNames())
                .forEach(
                        i -> {
                            System.out.println("name: " + i +
                                    " 来源: " + context.getBeanFactory().getBeanDefinition(i)
                                    .getResourceDescription());
                        });

        // 测试应用类型推断
        Method deduceFromClasspath = WebApplicationType.class.getDeclaredMethod("deduceFromClasspath");
        deduceFromClasspath.setAccessible(true);
        System.out.println("\n应用类型为: " + deduceFromClasspath.invoke(null));

        // 输出所有事件信息
        System.out.println();
        spring.addListeners(event -> System.out.println("\t事件为: " + event));

        context.close();

    }

    static class Bean1 {
    }

    static class Bean2 {
    }

    @Bean
    public Bean2 bean2() {
        return new Bean2();
    }

    @Bean
    public TomcatServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }
}
