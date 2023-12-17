package com.java.study.frameworkstudy.spring.aspectdemo;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * @Author： yijun
 * @DATE: 2023/12/16 22:53
 * @Description
 */
public class AdvisorTest {
    /**
     * Advisor切面的使用
     * @param args
     */
    public static void main(String[] args) {
        // 1. 备好切点（根据 AspectJ 表达式进行匹配）
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* foo())");
        // 2. 备好通知
        MethodInterceptor advice = invocation -> {
            System.out.println("before...");
            Object result = invocation.proceed();
            System.out.println("after...");
            return result;
        };
        // 3. 备好切面
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, advice);
        // 4. 创建代理
        //当ProxyFactory 的父类 ProxyConfig 中有个名为 proxyTargetClass 的布尔类型成员变量：
        //当 proxyTargetClass == false，并且目标对象所在类实现了接口时，将选择 JDK 动态代理；
        //当 proxyTargetClass == false，但目标对象所在类未实现接口时，将选择 CGLib 动态代理；
        //当 proxyTargetClass == true，总是选择 CGLib 动态代理。
        Target1 target = new Target1();
        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(target);
        factory.addAdvisor(advisor);

        I1 proxy = (I1) factory.getProxy();
        System.out.println("proxy class:" + proxy.getClass());
        proxy.foo();
        System.out.println("====");
        proxy.bar();

    }
    interface I1 {
        void foo();

        void bar();
    }

    static class Target1 implements I1 {

        @Override
        public void foo() {
            System.out.println("target1 foo");
        }

        @Override
        public void bar() {
            System.out.println("target1 bar");
        }
    }

    static class Target2 {
        public void foo() {
            System.out.println("target2 foo");
        }
        public void bar() {
            System.out.println("target2 bar");
        }
    }

}
