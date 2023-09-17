package com.java.study.javastudy.middleware.redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @Author： yijun
 * @DATE: 2023/9/17 19:22
 * @Description
 * 基于redis实现分布式锁
 */
@Component
public class RedisLockService {
    private static final String COMPARE_AND_DELETE_CMD = "if redis.call('get', KEYS[1]) == ARGV[1] "
            + "then redis.call('del', KEYS[1]) return '1' "
            + "else return '0' "
            + "end";
    private static final RedisScript<String> COMPARE_AND_DELETE =
            new DefaultRedisScript<>(COMPARE_AND_DELETE_CMD, String.class);

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private ThreadLocal<String> threadLocal = new ThreadLocal<>();

    /**
     * 返回true，说明获取到锁
     * 返回false，说明获取锁失败
     * @param lockName
     * @param millSecond 锁失效时间
     * @return
     */
    public boolean tryLock(String lockName, long millSecond) {
        String lockValue = "redisValue" + System.currentTimeMillis();
        Boolean success = stringRedisTemplate.opsForValue()
                .setIfAbsent(lockName, lockValue,
                        millSecond, TimeUnit.MILLISECONDS);
        if (success != null && success) {
            threadLocal.set(lockValue);
            return true;
        }
        return false;
    }

    /**
     * 释放锁
     * 需要确保原子性，防止误删其它线程的锁
     * 如果式两行命令执行：有可能下一秒锁就失效了，然后其它线程加锁了，该线程把其它线程锁删除了
     * @param lockName
     */
    public void releaseLock(String lockName) {
        stringRedisTemplate.execute(COMPARE_AND_DELETE,
                Collections.singletonList(lockName),
                threadLocal.get());
        threadLocal.remove();
    }
}
