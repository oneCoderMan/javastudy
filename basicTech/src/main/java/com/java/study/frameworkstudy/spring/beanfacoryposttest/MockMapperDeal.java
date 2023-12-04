package com.java.study.frameworkstudy.spring.beanfacoryposttest;

import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.IOException;

/**
 * @Author： yijun
 * @DATE: 2023/12/3 09:22
 * @Description
 */
public class MockMapperDeal {
    public static void main(String[] args) throws IOException {
        testMapper();

    }

    /**
     * 模拟MapperScannerConfigurer后处理器处理解析@Mapper注解扫描的功能
     */
    public static void testMapper() throws IOException {
        // GenericApplicationContext 是一个干净的容器(没有加载过很多类，并且可以方便的进行Bean的注册 )
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("config", Config.class);
        // 需要将@Bean也解析出来
        context.registerBean(AtBeanPostProcessor.class);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:com/java/study/frameworkstudy/spring/beanfacoryposttest/mapper/**/*.class");
        AnnotationBeanNameGenerator generator = new AnnotationBeanNameGenerator();
        CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();
        for (Resource resource : resources) {
            MetadataReader reader = factory.getMetadataReader(resource);
            ClassMetadata classMetadata = reader.getClassMetadata();
            if (classMetadata.isInterface()) {
                // AbstractBeanDefinition的工作和下面这个代码一样
                //    @Bean
                //    public MapperFactoryBean<Mapper2> mapper2(SqlSessionFactory sqlSessionFactory) {
                //        MapperFactoryBean<Mapper2> factoryBean = new MapperFactoryBean<>(Mapper2.class);
                //        factoryBean.setSqlSessionFactory(sqlSessionFactory);
                //        return factoryBean;
                //    }
                AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder
                        .genericBeanDefinition(MapperFactoryBean.class)
                        .addConstructorArgValue(classMetadata.getClassName())
                        .setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE)
                        .getBeanDefinition();
                AbstractBeanDefinition bd = BeanDefinitionBuilder
                        .genericBeanDefinition(classMetadata.getClassName())
                        .getBeanDefinition();

                DefaultListableBeanFactory beanFactory = context.getDefaultListableBeanFactory();
                String name = generator.generateBeanName(bd, beanFactory);
                beanFactory.registerBeanDefinition(name, beanDefinition);
            }
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
