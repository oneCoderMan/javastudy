package com.java.study.frameworkstudy.spring.beanposttest;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.core.MethodParameter;
import org.springframework.core.env.StandardEnvironment;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Author： yijun
 * @DATE: 2023/11/28 22:15
 * @Description
 */
public class AutowiredBeanPostTest {
    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 注册成品 Bean，不再进行 Bean 的创建、依赖注入、初始化等操作
        beanFactory.registerSingleton("bean2", new Bean2());
        beanFactory.registerSingleton("bean3", new Bean3());
        // @Value
        beanFactory.setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());
        beanFactory.addEmbeddedValueResolver(new StandardEnvironment()::resolvePlaceholders);

        // 查看哪些属性、方法加了 @Autowired，这称之为 InjectionMetadata
        AutowiredAnnotationBeanPostProcessor postProcessor = new AutowiredAnnotationBeanPostProcessor();
        postProcessor.setBeanFactory(beanFactory);

        Bean1 bean1 = new Bean1();
        System.out.println(bean1);
        // 执行依赖注入，@Autowired、@Value
        // 其中里面的findAutowiringMetadata() 用于查找指定的 bean 对象中哪些地方使用了 @Autowired、@Value
        // 等与注入相关的注解，并将这些信息封装在 InjectionMetadata 对象中，之后调用其 inject() 方法利用反射完成注入。
        // 注入时按类型在容器中查找值，并且通过反射赋予值
        postProcessor.postProcessProperties(null, bean1, "bean1");
        System.out.println(bean1);

        // 下面是模拟如何使用反射来完成@Autowired、@Value注解解析的
        Field bean4 = Bean1.class.getDeclaredField("bean4");
        DependencyDescriptor dd1 = new DependencyDescriptor(bean4, false);
        Object o1 = beanFactory.doResolveDependency(dd1, null, null, null);
        System.out.println("get o1 " + o1);

        Method setBean2 = Bean1.class.getDeclaredMethod("setBean2", Bean2.class);
        // MethodParameter 构造方法的第二个参数表示需要解析的方法中参数的索引
        DependencyDescriptor dd2 = new DependencyDescriptor(new MethodParameter(setBean2, 0), false);
        Object o2 = beanFactory.doResolveDependency(dd2, null, null, null);
        System.out.println("get o2 " + o2);

        Method setHome = Bean1.class.getDeclaredMethod("setHome", String.class);
        DependencyDescriptor dd3 = new DependencyDescriptor(new MethodParameter(setHome, 0), true);
        Object o3 = beanFactory.doResolveDependency(dd3, null, null, null);
        System.out.println("get o3 " + o3);

    }
}
