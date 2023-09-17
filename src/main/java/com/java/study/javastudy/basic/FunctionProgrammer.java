package com.java.study.javastudy.basic;

import com.java.study.javastudy.model.Person;

/**
 * @Author： yijun
 * @DATE: 2023/9/17 18:01
 * @Description
 * JDK 8 中提供了大量的函数接口，这些接口定义在 java.util.function 中，
 * 因此我们一般情况下不需再定义自己的接口，同时，各个接口的作用和名字都是相对应的，
 * 所以，了解函数式接口的命名模式就是很有必要的了。
 *
 * 以下是基本命名准则：
 *
 * 1. 如果只处理对象而非基本类型，名称则为 Function ， Consumer ， Predicate 等。参数类型通过泛型添加。
 * 2. 如果接收的参数是基本类型，则由名称的第一部分表示，如 LongConsumer，DoubleFunction，IntPredicate 等，但基本 Supplier 类型例外。
 * 3. 如果返回值为基本类型，则用 To 表示，如 ToLongFunction <T> 和 IntToLongFunction 。
 * 4. 如果返回值类型与参数类型一致，则是一个运算符：单个参数使用 UnaryOperator ，两个参数使用 BinaryOperator 。
 * 5. 如果接收两个参数且返回值为布尔值，则是一个谓词（Predicate）。
 * 6. 如果接收的两个参数类型不同，则名称中有一个 Bi 。
 */
public class FunctionProgrammer {
    public static void main(String[] args) {
        FunctionProgrammer functionProgrammer = new FunctionProgrammer();
//        functionProgrammer.testSelfSupplier();
//        Boolean aBoolean = RetBodySupplier.returnBoolean(() -> isZero(5));
//        System.out.println(aBoolean);
//        functionProgrammer.testConsumer();
//        functionProgrammer.testFunction();
        functionProgrammer.testPredict();;
    }

    /**
     * 无参数，但是有返回值
     * Supplier
     */
    public void testNoArg() {
        Integer exec = RetBodySupplier.doSth("exec", () -> calculate(1, 2));
        System.out.println(exec);

    }

    /**
     * 无参数，但是有返回值,
     * 自定义supplier
     */
    public void testSelfSupplier() {
        Integer exec = RetBodySupplier.doSth("exec", () -> calculate(1, 2));
        System.out.println(exec);
    }

    /**
     * 一个参数，无返回值
     * Consumer
     */
    public void testConsumer() {
        Person person = new Person(1, "Jim", "male");
        RetBodySupplier.doSth((p) -> printPeople(p), person);
    }

    /**
     * 一个参数，一个返回值
     * Function
     */
    public void testFunction() {
        Integer raw = 3;
        Integer rest = RetBodySupplier.doSthFun((a) -> a + 3, raw);
        System.out.println(rest);
    }

    /**
     * 一个参数 返回boolean
     * Predicate
     */
    public void testPredict() {
        Integer a = 0;
        Boolean aBoolean = RetBodySupplier.testPredict((t) -> isZero(t), a);
        System.out.println(aBoolean);
    }


    public void printPeople(Person person) {
        System.out.println(person);
    }


    public static Integer calculate(int a, int b) {
        return a + b;
    }

    public static Boolean isZero(int a) {
        if (a != 0) {
            return false;
        }
        return true;
    }
}
