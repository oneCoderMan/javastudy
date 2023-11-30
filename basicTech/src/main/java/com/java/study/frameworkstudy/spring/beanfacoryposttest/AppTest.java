package com.java.study.frameworkstudy.spring.beanfacoryposttest;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @Author： yijun
 * @DATE: 2023/11/29 22:21
 * @Description
 */
public class AppTest {
    public static void main(String[] args) {
        // test01();
        test02();

    }

    /**
     * 结果是只有一个Bean：config
     * 也就是说 Config 类中的 @ComponentScan 和 @Bean 注解都没有生效。
     * 根据经验，显然是因为缺少某个后置处理器。
     */
    public static void test01() {
        // GenericApplicationContext 是一个干净的容器(没有加载过很多类，并且可以方便的进行Bean的注册 )
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("config", Config.class);

        // 初始化容器
        context.refresh();

        // 结果是只有一个Bean：config
        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }

        context.close();
    }

    public static void test02() {
        // GenericApplicationContext 是一个干净的容器(没有加载过很多类，并且可以方便的进行Bean的注册 )
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("config", Config.class);
        // 完成@ComponentScan，@Bean， @Import，@ImportResource注解的解析工作
        context.registerBean(ConfigurationClassPostProcessor.class);
        // 完成@Mapper注解的解析
        context.registerBean(MapperScannerConfigurer.class,
                i -> i.getPropertyValues()
                        .add("basePackage",
                                "com.java.study.frameworkstudy.spring.beanfacoryposttest.mapper"));


        // 初始化容器
        context.refresh();

        // 结果是有很多Bean
        // config
        //org.springframework.context.annotation.ConfigurationClassPostProcessor
        //BFBean
        //bfBean1
        //sqlSessionFactoryBean
        //dataSource
        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }

        context.close();
    }

}
