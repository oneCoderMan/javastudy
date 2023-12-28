package com.java.study.frameworkstudy.springboot.autoconfig;

import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Arrays;

/**
 * @Author： yijun
 * @DATE: 2023/12/28 21:37
 * @Description
 */
public class TestAopConfig {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
//        StandardEnvironment env = new StandardEnvironment();
//        env.getPropertySources().addLast(
//                new SimpleCommandLinePropertySource("--spring.aop.auto=false")
//        );
//        context.setEnvironment(env);

        // 注册常用后置处理器
        AnnotationConfigUtils.registerAnnotationConfigProcessors(context.getDefaultListableBeanFactory());
        context.registerBean(Config.class);
        context.refresh();
        System.out.println("====");
        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
        System.out.println("**************");
        for (String name : context.getBeanDefinitionNames()) {
            String resourceDescription = context.getBeanDefinition(name).getResourceDescription();
            if (resourceDescription != null) {
                System.out.println(name + " \n来源: " + resourceDescription + "\n");
            }
        }
        /**
         * 由于导入了 mybatis-spring-boot-starter，
         * 其内部依赖 mybatis-spring-boot-jdbc，而它又依赖了 HikariCP，因此最终数据库连接池 Hikari 生效：
         *
         */
    }

    @Configuration
    @Import(MyImportSelector.class)
    static class Config {
    }

    static class MyImportSelector implements DeferredImportSelector {
        @Override
        public String[] selectImports(AnnotationMetadata importingClassMetadata) {
            return new String[]{AopAutoConfiguration.class.getName()};
        }
    }
}
