package com.java.study.frameworkstudy.spring;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author： yijun
 * @DATE: 2023/11/25 11:10
 * @Description
 */
public class TemplateMethodTest {
    public static void main(String[] args) {
//        MyBeanFactory beanFactory = new MyBeanFactory();
//        beanFactory.getBean();
        My2BeanFactory beanFactory2 = new My2BeanFactory();
        beanFactory2.addBeanPostProcess(new AutowiredBeanPostProcessor());
        beanFactory2.getBean();
    }

    /**
     * 模拟BeanFactory
     * 完成一个Bean的构造，这扩展性很差
     * 比如目前只有一个基本的依赖注入功能，如果有其他依赖注入的功能改动大
     * 因此可以使用模版方法模式
     */
    static class MyBeanFactory {
        public Object getBean() {
            Object bean = new Object();
            System.out.println("构造" + bean);
            System.out.println("依赖注入" + bean);
            System.out.println("初始化" + bean);
            return bean;
        }

    }

    /**
     * 模拟BeanFactory：模版方法模式
     */
    static class My2BeanFactory {
        private List<BeanPostProcessor> injectProcess = new ArrayList<>();

        /**
         * 构造Bean的流程不变，具体每一个步骤可以有不同的处理
         * @return
         */
        public Object getBean() {
            Object bean = new Object();
            System.out.println("构造" + bean);
            System.out.println("依赖注入" + bean);
            for (BeanPostProcessor process : injectProcess) {
                process.inject(bean);
            }
            System.out.println("初始化" + bean);
            return bean;
        }

        /**
         * 提供给客户端加入的后置处理器
         * @param processor
         */
        public void addBeanPostProcess(BeanPostProcessor processor) {
            injectProcess.add(processor);
        }
    }


    static interface BeanPostProcessor  {
        /**
         * 定义方法接口
         * @param bean 处理的类
         */
        public void inject(Object bean);
    }

    /**
     * 不同的实现
     */
    static class AutowiredBeanPostProcessor implements BeanPostProcessor {

        @Override
        public void inject(Object bean) {
            System.out.println("完成Autowired注入");
        }
    }

}
