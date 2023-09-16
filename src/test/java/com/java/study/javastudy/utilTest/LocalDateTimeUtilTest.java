package com.java.study.javastudy.utilTest;

import com.java.study.javastudy.utils.LocalDateTimeUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author： yijun
 * @DATE: 2023/9/16 22:19
 * @Description
 */
public class LocalDateTimeUtilTest {
    private static final Logger log = LoggerFactory.getLogger(LocalDateTimeUtilTest.class);

    @Test
    public void testTime() {
        String nowStr = LocalDateTimeUtil.getNowStr();
        log.info(nowStr);
        LocalDateTime localDateTime =
                LocalDateTimeUtil.str2LocalDateTime("2023-09-09 10:00:00");
        System.out.println(localDateTime);
        //timestamp2LocalDateTime
        System.out.println("timestamp");
        LocalDateTime localDateTime1 = LocalDateTimeUtil.long2LocalDateTime(System.currentTimeMillis());
        System.out.println(localDateTime1);
        //
        Date date = LocalDateTimeUtil.localDateTime2Date(localDateTime1);
        System.out.println(date);
        LocalDateTime localDateTime2 = LocalDateTimeUtil.date2LocalDateTime(date);
        System.out.println(localDateTime2);

    }
}
