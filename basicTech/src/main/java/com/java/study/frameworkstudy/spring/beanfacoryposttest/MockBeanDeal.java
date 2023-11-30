package com.java.study.frameworkstudy.spring.beanfacoryposttest;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Set;

/**
 * @Author： yijun
 * @DATE: 2023/11/30 22:42
 * @Description
 */
public class MockBeanDeal {
    public static void main(String[] args) throws IOException {
        test01();
    }

    /**
     * 模拟ConfigurationClassPostProcessor后处理器处理解析@Bean注解的功能
     */
    public static void test01() throws IOException {
        // GenericApplicationContext 是一个干净的容器(没有加载过很多类，并且可以方便的进行Bean的注册 )
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("config", Config.class);

        CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();
        MetadataReader reader = factory.getMetadataReader(new ClassPathResource("com/java/study/frameworkstudy/spring/beanfacoryposttest/Config.class"));
        Set<MethodMetadata> methods = reader.getAnnotationMetadata().getAnnotatedMethods(Bean.class.getName());

        for (MethodMetadata method : methods) {
            System.out.println("获取到的方法：" + method);
            String initMethod = method.getAnnotationAttributes(Bean.class.getName())
                    .get("initMethod")
                    .toString();

            String methodName = method.getMethodName();
            BeanDefinitionBuilder builder = BeanDefinitionBuilder
                    .genericBeanDefinition()
                    .setFactoryMethodOnBean(methodName, "config")
                    // 工厂方法、构造方法的注入模式使用构造器模式
                    .setAutowireMode(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR);

            if (StringUtils.hasLength(initMethod)) {
                builder.setInitMethodName(initMethod);
            }

            AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
            DefaultListableBeanFactory beanFactory = context.getDefaultListableBeanFactory();
            beanFactory.registerBeanDefinition(methodName, beanDefinition);
        }


        // 初始化容器
        context.refresh();
        System.out.println("\n该容器里面已经存在的Bean定义:");
        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }

        context.close();
    }
}
