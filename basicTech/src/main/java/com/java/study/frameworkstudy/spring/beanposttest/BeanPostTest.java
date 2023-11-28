package com.java.study.frameworkstudy.spring.beanposttest;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @Author： yijun
 * @DATE: 2023/11/27 22:53
 * @Description
 */
public class BeanPostTest {
    public static void main(String[] args) {
//        test01();
        test02();


    }

    /**
     * 控制台中只打印了与 Spring 相关的日志信息，也就是说 Bean1 中使用的注解并没有生效
     */
    public static void test01() {
        // GenericApplicationContext 是一个干净的容器(没有加载过很多类，并且可以方便的进行Bean的注册 )
        GenericApplicationContext context = new GenericApplicationContext();
        // 用原始方式注册三个 bean
        // Bean2 和 Bean3 很简单，而在 Bean1 中使用了多个注解以实现 Bean 注入和值注入。
        context.registerBean("bean1", Bean1.class);
        context.registerBean("bean2", Bean2.class);
        context.registerBean("bean3", Bean3.class);

        // 初始化容器。执行 beanFactory 后置处理器，添加 bean 后置处理器，初始化所有单例
        context.refresh();
        // 销毁容器
        context.close();
    }

    /**
     *
     */
    public static void test02() {
        // GenericApplicationContext 是一个干净的容器(没有加载过很多类，并且可以方便的进行Bean的注册 )
        GenericApplicationContext context = new GenericApplicationContext();
        // 用原始方式注册三个 bean
        // Bean2 和 Bean3 很简单，而在 Bean1 中使用了多个注解以实现 Bean 注入和值注入。
        context.registerBean("bean1", Bean1.class);
        context.registerBean("bean2", Bean2.class);
        context.registerBean("bean3", Bean3.class);

        // 解析值注入内容
        context.getDefaultListableBeanFactory().setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());
        // @Autowired @Value 注解的解析
        context.registerBean(AutowiredAnnotationBeanPostProcessor.class);
        // @Resource @PostConstruct @PreDestroy注解的解析
        context.registerBean(CommonAnnotationBeanPostProcessor.class);

        // 初始化容器。执行 beanFactory 后置处理器，添加 bean 后置处理器，初始化所有单例
        context.refresh();
        // 销毁容器
        context.close();
    }
}
