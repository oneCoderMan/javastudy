package com.java.study.idGenerator.snowFlake;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author： yijun
 * @DATE: 2023/9/17 09:10
 * @Description
 * id生成器，缓冲池存储
 */
public class IdGenWorker implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(IdGenWorker.class);
    /**
     * 缓冲池，存储生成的id
     */
    private final BlockingQueue<Long> queue;
    /**
     * 缓冲池存储id最大容量
     */
    private final Integer capacity;
    /**
     * 雪花id生成器
     */
    private final SnowflakeIdGenerator generator;
    /**
     * 锁
     */
    private final ReentrantLock lock;
    /**
     * 缓冲池没有满的信号量
     */
    private final Condition notFull;
    /**
     * 缓冲池不是空的信号量
     */
    private final Condition notEmpty;

    public IdGenWorker(BlockingQueue<Long> queue,
                       SnowflakeIdGenerator generator,
                       Integer capacity,
                       ReentrantLock lock,
                       Condition notFull,
                       Condition notEmpty) {
        this.queue = queue;
        this.generator = generator;
        this.capacity = capacity;
        this.lock = lock;
        this.notFull = notFull;
        this.notEmpty = notEmpty;
    }

    /**
     * 缓冲池是否满
     * @return
     */
    private boolean isFull() {
        return queue.size() >= capacity;
    }

    /**
     * 线程生成id
     */
    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                while (isFull()) {
                    try {
                        notFull.await();
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                LOGGER.debug("worker:{} is generating", generator.getWorkerId());
                int size = Math.min(capacity / 2, capacity - queue.size());
                for (int i = 0; i < size; i++) {
                    queue.add(generator.nextId());
                }
                notEmpty.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

}
