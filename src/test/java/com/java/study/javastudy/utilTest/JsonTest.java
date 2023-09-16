package com.java.study.javastudy.utilTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.reflect.TypeToken;
import com.java.study.javastudy.model.Person;
import com.java.study.javastudy.utils.GsonUtil;
import com.java.study.javastudy.utils.JsonUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.java.study.javastudy.Mock.MockPeople.mockPeople;

/**
 * @Author： yijun
 * @DATE: 2023/9/16 17:41
 * @Description
 */
public class JsonTest {
    private static final Logger log = LoggerFactory.getLogger(JsonTest.class);

    @Test
    public void testJson() {
        List<Person> people = mockPeople(1, 2);
        String s = JsonUtil.toJson(people);
        log.info(s);
        List<Person> people1 = JsonUtil.fromJson(s, new TypeReference<List<Person>>() {});
        System.out.println(people1);
        // String s2 = JsonUtil.writePrettyValue(people);
        log.info(s);
        log.info("====");
        String s1 = GsonUtil.toJson(people);
        List<Person> people2 = GsonUtil.fromJson(s1, new TypeToken<List<Person>>() {
        });
        System.out.println(people2);
        log.info(s1);
    }
}
