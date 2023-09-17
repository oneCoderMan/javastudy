package com.java.study.javastudy.idGenerator.snowFlake;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author： yijun
 * @DATE: 2023/9/17 09:06
 * @Description
 * 封装雪花算法生成
 * buffer的缓冲设计提高性能(多个场景下的id生成)
 */
public class IdGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(IdGenerator.class);

    /**
     * 缓冲容器容量
     */
    private final Integer capacity;

    private final List<IdGenWorker> workers = new ArrayList<>();

    private final BlockingQueue<Long> queue;

    private final ReentrantLock lock;

    private final Condition notFull;

    private final Condition notEmpty;

    private ExecutorService executorService;



    public IdGenerator(long[] workerIds, int capacity) {
        this.capacity = capacity;
        lock = new ReentrantLock();
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();
        queue = new ArrayBlockingQueue<>(capacity);
        for (long wid : workerIds) {
            workers.add(new IdGenWorker(queue,
                    new SnowflakeIdGenerator(wid, 1, 0), capacity, lock, notFull, notEmpty));
        }
        initWorkers();
    }

    public IdGenerator(long workerId) {
        this(new long[]{workerId}, 1000);
    }

    private void initWorkers() {
        executorService =  new ThreadPoolExecutor(workers.size(), workers.size(),
                10, TimeUnit.MINUTES, new SynchronousQueue<>());
        workers.forEach(worker -> executorService.submit(worker));
    }

    public Long nextId() {
        return get();
    }

    public Long get() {
        List<Long> ids = get(1);
        return ids.get(0);
    }

    public List<Long> get(int size) {
        if (size <= capacity) {
            throw new RuntimeException("`size` must be less than `capacity`");
        }
        while (true) {
            lock.lock();
            try {
                while (queue.size() < size) {
                    try {
                        notEmpty.await();
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
                List<Long> res = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    res.add(queue.poll());
                }
                notFull.signalAll();
                return res;
            } finally {
                lock.unlock();
            }
        }
    }



}
