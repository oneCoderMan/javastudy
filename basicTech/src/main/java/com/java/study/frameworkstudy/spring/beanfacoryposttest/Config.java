package com.java.study.frameworkstudy.spring.beanfacoryposttest;

import com.alibaba.druid.pool.DruidDataSource;
import com.java.study.frameworkstudy.spring.beanfacoryposttest.component.BFBean3;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @Author： yijun
 * @DATE: 2023/11/29 22:25
 * @Description
 */
@Configuration
@ComponentScan("com.java.study.frameworkstudy.spring.beanfacoryposttest.component")
public class Config {
    @Bean
    public BFBean1 bfBean1() {
        return new BFBean1();
    }

    public BFBean3 bfBean3() {
        return new BFBean3();
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

    @Bean(initMethod = "init")
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/advanced_spring");
        dataSource.setName("root");
        dataSource.setPassword("123456");
        return dataSource;
    }

}
