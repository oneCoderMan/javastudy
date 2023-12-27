package com.java.study.frameworkstudy.springboot.autoconfig;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author： yijun
 * @DATE: 2023/12/27 08:50
 * @Description
 */
public class AutoConfigTest2 {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("config", Config.class);
        // Configuration注解的后置处理器
        context.registerBean(ConfigurationClassPostProcessor.class);
        context.refresh();

        // 可以看到bean3 和bean4 被导入进来了
        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
    }

    @Configuration
    @Import(MyImportSelector.class)
    static class Config {

    }

    /**
     * 可以使用导入选择器 ImportSelector，重写 selectImports() 方法，返回需要自动装配的 Bean 的全限定类名数组
     */
    static class MyImportSelector implements ImportSelector {
        @Override
        public String[] selectImports(AnnotationMetadata importingClassMetadata) {
            return new String[]{AutoConfiguration1.class.getName(),
                    AutoConfiguration2.class.getName()};
        }
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
        public Bean4 bean4() {
            return new Bean4();
        }
    }

    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    static class Bean4 {
        private String name;
    }

    /**
     * 模拟第三方配置类
     */
    static class AutoConfiguration2 {
        @Bean
        public Bean3 bean3() {
            return new Bean3();
        }
    }

    static class Bean3 {

    }
}
