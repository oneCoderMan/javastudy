package com.java.study.javastudy.redisTest;

import com.java.study.javastudy.middleware.redis.RedisClient;
import com.java.study.javastudy.middleware.redis.RedisLockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author： yijun
 * @DATE: 2023/9/17 12:18
 * @Description
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisTest.class);
    @Autowired
    private RedisClient redisOpService;

    @Autowired
    private RedisLockService redisLockService;

    @Test
    public void test() {
        redisOpService.saveStr("test", "yijun");
        String test = redisOpService.getStr("test");
        LOGGER.info("value from redis: {}", test);
    }

    @Test
    public void testLock() throws InterruptedException {
        String name = "lockTest2";
        boolean lock = redisLockService.tryLock(name, 1000 * 600);
        if (lock) {
            System.out.println("===get redis lock");
        } else {
            System.out.println("=== not get redis lock");
        }
        System.out.println("sleep ==");
        Thread.sleep(5000);
        boolean lock2 = redisLockService.tryLock(name, 1000 * 600);
        if (lock2) {
            System.out.println("===get redis lock");
        } else {
            System.out.println("=== not get redis lock");
        }
        Thread.sleep(5000);
        System.out.println("release lock ==");
        redisLockService.releaseLock(name);
        Thread.sleep(5000);
        System.out.println("continue to get lock ==");
        boolean lock3 = redisLockService.tryLock(name, 1000 * 600);
        if (lock3) {
            System.out.println("===get redis lock");
        } else {
            System.out.println("=== not get redis lock");
        }


    }
}
