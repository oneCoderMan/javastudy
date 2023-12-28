package com.java.study.frameworkstudy.springboot.autoconfig;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.SimpleCommandLinePropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author： yijun
 * @DATE: 2023/12/28 21:51
 * @Description
 */
public class TestDatasourceConfig {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        StandardEnvironment env = new StandardEnvironment();
        env.getPropertySources().addLast(new SimpleCommandLinePropertySource(
                "--spring.datasource.url=jdbc:mysql://localhost:3306/advanced_spring",
                "--spring.datasource.username=root",
                "--spring.datasource.password=123456"
        ));
        context.setEnvironment(env);

        // 注册常用后置处理器
        AnnotationConfigUtils.registerAnnotationConfigProcessors(context.getDefaultListableBeanFactory());
        context.registerBean(Config.class);
        context.refresh();
        System.out.println("====");
        // 可以看到这些装配进来的bean及其来源
        for (String name : context.getBeanDefinitionNames()) {
            String resourceDescription = context.getBeanDefinition(name).getResourceDescription();
            if (resourceDescription != null) {
                System.out.println(name + " \n来源: " + resourceDescription + "\n");
            }
        }
        // Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

        // DataSourceAutoConfiguration.class 中会绑带数据源的键值对
        DataSourceProperties properties = context.getBean(DataSourceProperties.class);
        System.out.println(properties.getUrl());
        System.out.println(properties.getUsername());
        System.out.println(properties.getPassword());
    }

    @Configuration
    @Import(MyImportSelector.class)
    static class Config {
    }

    static class MyImportSelector implements DeferredImportSelector {
        @Override
        public String[] selectImports(AnnotationMetadata importingClassMetadata) {
            return new String[]{
                    DataSourceAutoConfiguration.class.getName(),
                    MybatisAutoConfiguration.class.getName(),
                    DataSourceTransactionManagerAutoConfiguration.class.getName(),
                    TransactionAutoConfiguration.class.getName()
            };

        }
    }
    /**
     * MybatisAutoConfiguration 生效的条件有两个：
     * 1. 类路径下存在 SqlSessionFactory 和 SqlSessionFactoryBean
     * 2. Spring 容器中有且仅有一个 DataSource 类型的 Bean
     * 它还添加了 MybatisProperties 类型的 Bean 到 Spring 容器中，并与配置文件中以 mybatis 为前缀的信息绑定。
     */
}
