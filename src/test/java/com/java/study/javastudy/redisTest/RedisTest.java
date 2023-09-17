package com.java.study.javastudy.redisTest;

import com.java.study.javastudy.middleware.es.RedisClient;
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

    @Test
    public void test() {
        redisOpService.saveStr("test", "yijun");
        String test = redisOpService.getStr("test");
        LOGGER.info("value from redis: {}", test);
    }
}
