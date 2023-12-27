package com.java.study.frameworkstudy.springboot.autoconfig;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @Author： yijun
 * @DATE: 2023/12/27 08:24
 * @Description
 */
public class AutoConfigTest {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("config", Config.class);
        // Configuration注解的后置处理器
        context.registerBean(ConfigurationClassPostProcessor.class);
        context.refresh();

        // 可以看到bean1 和bean2被导入进来了
        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
    }

    /**
     * 编写自己的配置类，使用 @Import 注解将第三方配置类添加到 Spring 容器中：
     */
    @Configuration
    @Import({AutoConfiguration1.class, AutoConfiguration2.class})
    static class Config {
    }

    /**
     * 模拟第三方配置类
     * 其中 AutoConfiguration1 和 AutoConfiguration2 用来模拟第三方配置类，
     * 注意它们并没有被 @Configuration 注解标记，因此在未进行其他操作时，不会被添加到 Spring 容器中
     *
     *
     */
    static class AutoConfiguration1 {
        @Bean
        public Bean1 bean1() {
            return new Bean1();
        }
    }

    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    static class Bean1 {
        private String name;
    }

    /**
     * 模拟第三方配置类
     */
    static class AutoConfiguration2 {
        @Bean
        public Bean2 bean2() {
            return new Bean2();
        }
    }

    static class Bean2 {

    }

}
