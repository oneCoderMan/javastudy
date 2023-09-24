package com.java.study.multithread;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author： yijun
 * @DATE: 2023/9/9 11:17
 * @Description
 */
public class ThreadPool {
    public static final ThreadPoolExecutor TEST_EXECUTOR;
    static {
        TEST_EXECUTOR = new ThreadPoolExecutor(10, 10,
                2, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(),
                r -> new Thread(r, "test_executor_" + r.hashCode()));
    }
}
