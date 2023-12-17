package com.java.study.frameworkstudy.spring.aspectdemo;

import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;

/**
 * @Author： yijun
 * @DATE: 2023/12/17 08:59
 * @Description
 */
public class PointCutMatchTest {
    public static void main(String[] args) throws NoSuchMethodException {
        // test00();
        test01();

    }

    /**
     * 模拟普通方法名和表达式的匹配
     * @throws NoSuchMethodException
     */
    public static void test00() throws NoSuchMethodException {
        AspectJExpressionPointcut pt1 = new AspectJExpressionPointcut();
        // 根据方法名进行匹配
        pt1.setExpression("execution(* bar())");
        // 通过matches方法来判断某一个类是否命中切点，将来代理的时候会做这个检查
        System.out.println(pt1.matches(T1.class.getMethod("foo"), T1.class));
        System.out.println(pt1.matches(T1.class.getMethod("bar"), T1.class));

        System.out.println("===");
        AspectJExpressionPointcut pt2 = new AspectJExpressionPointcut();
        // 根据注解进行匹配
        pt2.setExpression("@annotation(org.springframework.transaction.annotation.Transactional)");
        System.out.println(pt2.matches(T1.class.getMethod("foo"), T1.class));
        System.out.println(pt2.matches(T1.class.getMethod("bar"), T1.class));

    }

    /**
     * 模拟Transactional注解增强的匹配
     * @throws NoSuchMethodException
     */
    public static void test01() throws NoSuchMethodException {
        StaticMethodMatcherPointcut pt3 = new StaticMethodMatcherPointcut() {
            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                // 检查方法上是否添加了 @Transactional 注解
                MergedAnnotations annotations = MergedAnnotations.from(method);
                if (annotations.isPresent(Transactional.class)) {
                    return true;
                }
                // 检查类上或所实现的接口是否添加了 @Transactional 注解
                annotations = MergedAnnotations.from(targetClass, MergedAnnotations.SearchStrategy.TYPE_HIERARCHY);
                return annotations.isPresent(Transactional.class);
            }
        };

        // 方法foo有Transactional
        System.out.println(pt3.matches(T1.class.getMethod("foo"), T1.class));
        // 方法bar没有Transactional
        System.out.println(pt3.matches(T1.class.getMethod("bar"), T1.class));
        // 类T2有Transactional
        System.out.println(pt3.matches(T2.class.getMethod("foo"), T2.class));
        // T3实现的接口有Transactional
        System.out.println(pt3.matches(T3.class.getMethod("foo"), T3.class));

    }


    static class T1 {
        @Transactional
        public void foo() {

        }

        public void bar() {

        }
    }

    @Transactional
    static class T2 {
        public void foo() {

        }
    }

    @Transactional
    interface I3 {
        void foo();
    }

    static class T3 implements I3 {
        @Override
        public void foo() {

        }
    }


}
