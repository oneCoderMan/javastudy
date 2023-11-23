package com.java.study.frameworkstudy.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author： yijun
 * @DATE: 2023/11/23 21:54
 * @Description
 */
public class AnnotationConfigAppCtxDemo {
    public static void main(String[] args) {
        // 基于配置类创建容器
        // 运行之后，发现bean的定义和bean的创建都已经完成了
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(Config.class);
        for (String beanDefinitionName : ctx.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }

    }

    @Configuration
    static class Config {
        @Bean
        public Bean1 bean1() {
            return new Bean1();
        }
        @Bean
        public Bean2 bean2(Bean1 bean1) {
            Bean2 bean2 = new Bean2();
            bean2.setBean1(bean1);
            return bean2;
        }


    }
    static class Bean1 {
        public Bean1() {
            System.out.println("构造bean1");
        }
    }

    static class Bean2 {
        private Bean1 bean1;
        public Bean2() {
            System.out.println("构造bean2");
        }

        public void setBean1(Bean1 bean1) {
            this.bean1 = bean1;
        }
    }
}
