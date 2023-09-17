package com.java.study.javastudy.basic;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @Author： yijun
 * @DATE: 2023/9/17 18:02
 * @Description
 */
public class RetBodySupplier {
    // 使用supplier无参数
    // 返回任意类型
    public static <T> T doSth(String op, Supplier<T> supplier) {
        System.out.println(op);
        return supplier.get();
    }

    public static <T> T doSthSelf(CommonSupplier<T> supplier) {
        try {
            return supplier.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Boolean returnBoolean(BooleanSupplier booleanSupplier) {
        return booleanSupplier.getAsBoolean();
    }

    public static <T> void doSth(Consumer<T> consumer, T arg) {
        consumer.accept(arg);
    }

    public static <T, R> R doSthFun(Function<T, R> function, T t)  {
        R re = function.apply(t);
        return re;
    }

    public static <T> Boolean testPredict(Predicate<T> tPredicate, T t) {
        return tPredicate.test(t);
    }
}
