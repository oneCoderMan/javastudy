package com.java.study.javastudy.idGenerator;

import com.java.study.javastudy.idGenerator.snowFlake.SnowflakeIdGenerator;
import com.java.study.javastudy.idGenerator.snowFlake.UuidGenerator;
import org.junit.jupiter.api.Test;

/**
 * @Author： yijun
 * @DATE: 2023/9/17 08:54
 * @Description
 */
public class SnowflakeId {
    @Test
    public void testSnowflakeId() {
        SnowflakeIdGenerator idWorker = new SnowflakeIdGenerator(0, 0);
        for (int i = 0; i < 1000; i++) {
            long id = idWorker.nextId();
            System.out.println(id);
        }
    }

    @Test
    public void testUUid() {
        String s = UuidGenerator.genUuid();
        System.out.println(s);
    }
}
