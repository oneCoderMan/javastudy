package com.java.study.frameworkstudy.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @Author： yijun
 * @DATE: 2023/11/22 22:11
 * @Description
 */
public class SpringBeanFactoryDemo {
    public static void main(String[] args) {
        // 一开始里面没有bean的定义
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // bean 的定义（class，scope，初始化，销毁）beanFactory可以根据bean的描述信息创建bean
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder
                .genericBeanDefinition(Config.class)
                .setScope("singleton")
                .getBeanDefinition();
        // 将bean的定义信息注册到factory
        beanFactory.registerBeanDefinition("config", beanDefinition);
        // 可以获取到当前factory里面所有定义的bean名称
        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
        // 目前beanFactory还没有解析注解的能力
        // 通过如下代码给beanFactory添加一些常用的后处理器，是对beanFactory能力的扩展
        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);
        System.out.println("\n添加后处理器之后beanFactory里面定义的bean");
        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
        // 拿到解析常用注解的bean，并且执行后处理器
        Map<String, BeanFactoryPostProcessor> beansOfType = beanFactory
                .getBeansOfType(BeanFactoryPostProcessor.class);
        beansOfType.values()
                .stream()
                .forEach(beanFactoryPostProcessor -> {
                    beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
                });
        System.out.println("\n执行处理器之后beanFactory里面定义的bean");
        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }

//        // 可以拿到对应的bean来用
//        System.out.println("start test bean1===");
//        Bean1 bean1 = (Bean1) beanFactory.getBean(Bean1.class);
//        // 目前这里还是没有依赖注入的功能，下面输出的结果是null， @Autowired的功能需要扩展
//        System.out.println(bean1.getBean2());
        // 下面加上对bean  后处理器的运行 （bean的生命周期，创建，销毁等提供扩展功能），例如@Autowired和@Resource的解析
        Map<String, BeanPostProcessor> beansOfType1 = beanFactory.getBeansOfType(BeanPostProcessor.class);
        beansOfType1.values().forEach(beanPostProcessor -> {
             beanFactory.addBeanPostProcessor(beanPostProcessor);
        });

        System.out.println("\n===start test bean1 after add bean post process===");
        // 当需要用到bean的时候才会创建bean
        Bean1 bean1Test = (Bean1) beanFactory.getBean(Bean1.class);
        // 目前这里还是没有依赖注入的功能，下面输出的结果是null， @Autowired的功能需要扩展
        System.out.println(bean1Test.getBean2());



    }
    @Configuration
    static class Config {
        @Bean
        public Bean1 bean1() {
            return new Bean1();
        }
        @Bean
        public Bean2 bean2() {
            return new Bean2();
        }


    }
    static class Bean1 {
        public Bean1() {
            System.out.println("构造bean1");
        }

        @Autowired
        private Bean2 bean2;

        public Bean2 getBean2() {
            return bean2;
        }
    }

    static class Bean2 {
        public Bean2() {
            System.out.println("构造bean2");
        }

    }

}
