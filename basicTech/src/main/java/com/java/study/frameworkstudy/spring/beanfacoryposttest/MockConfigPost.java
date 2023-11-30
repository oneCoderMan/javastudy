package com.java.study.frameworkstudy.spring.beanfacoryposttest;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author： yijun
 * @DATE: 2023/11/29 23:13
 * @Description
 */
public class MockConfigPost {
    public static void main(String[] args) throws IOException {
        test02();
    }

    /**
     * 模拟ConfigurationClassPostProcessor后处理器处理解析@ComponentScan注解的功能
     */
    public static void test02() throws IOException {
        // GenericApplicationContext 是一个干净的容器(没有加载过很多类，并且可以方便的进行Bean的注册 )
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("config", Config.class);

        // 初始化容器
        context.refresh();

        ComponentScan componentScan = AnnotationUtils.findAnnotation(Config.class, ComponentScan.class);
        if (componentScan != null) {
            for (String packageName : componentScan.basePackages()) {
                System.out.println("获取到包名：" + packageName);
                // indi.mofan.bean.a05.component ->
                // com.java.study.frameworkstudy.spring.beanfacoryposttest.component
                // classpath*:com/java/study/frameworkstudy/spring/beanfacoryposttest/component/**/**.class
                String path = "classpath*:" + packageName.replace(".", "/") + "/**/**.class";
                //  Resource[] resources = context.getResources(path);
                Resource[] resources = new PathMatchingResourcePatternResolver().getResources(path);
                // 可以获取到类的元信息
                CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();
                AnnotationBeanNameGenerator generator = new AnnotationBeanNameGenerator();

                for (Resource resource : resources) {
                    System.out.println("\nget resource info: " + resource);
                    // 通过MetadataReader对象可以判断是否加了某个注解
                    MetadataReader reader = factory.getMetadataReader(resource);
                    AnnotationMetadata annotationMetadata = reader.getAnnotationMetadata();
                    System.out.println("类名: " + reader.getClassMetadata().getClassName());
                    System.out.println("是否加了 @Component: " + annotationMetadata.hasAnnotation(Component.class.getName()));
                    System.out.println("是否加了 @Component 派生: " + annotationMetadata.hasMetaAnnotation(Component.class.getName()));

                    if (annotationMetadata.hasAnnotation(Component.class.getName())
                            || annotationMetadata.hasMetaAnnotation(Component.class.getName())) {
                        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder
                                .genericBeanDefinition(reader.getClassMetadata().getClassName())
                                .getBeanDefinition();
                        // 获取到bean的名字
                        DefaultListableBeanFactory beanFactory = context.getDefaultListableBeanFactory();
                        String name = generator.generateBeanName(beanDefinition, beanFactory);
                        beanFactory.registerBeanDefinition(name, beanDefinition);
                    }
                }
            }
        }

        System.out.println("\n该容器里面已经存在的Bean定义:");
        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }

        context.close();
    }
}
