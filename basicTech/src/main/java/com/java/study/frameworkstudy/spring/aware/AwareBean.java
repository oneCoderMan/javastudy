package com.java.study.frameworkstudy.spring.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;

/**
 * @Author： yijun
 * @DATE: 2023/12/4 22:52
 * @Description
 */
public class AwareBean implements BeanNameAware,
        ApplicationContextAware,
        InitializingBean {
    @Override
    public void setBeanName(String name) {
        System.out.println("当前 Bean: " + this + "名字叫: " + name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("当前 Bean: " + this + "容器是: " + applicationContext);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("当前 Bean: " + this + "执行初始化操作");
    }


    /**
     * AppAwareTest中的GenericApplicationContext容器使用的时候是
     * 不回调的，因为@Autowired需要一些Bean的后置处理器
     */
    @Autowired
    public void setApplicationContextWithAutowired(ApplicationContext applicationContext) {
        System.out.println("当前 Bean: " + this + " 使用 @Autowired 注解，容器是: " + applicationContext);
    }

    @PostConstruct
    public void init() {
        System.out.println("当前 Bean: " + this + " 使用 @PostConstruct 注解初始化");
    }

}
