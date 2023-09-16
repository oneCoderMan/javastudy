package com.java.study.javastudy.utilTest;

import com.github.rholder.retry.Attempt;
import com.github.rholder.retry.RetryListener;
import com.java.study.javastudy.Mock.MockPeople;
import com.java.study.javastudy.model.Person;
import com.java.study.javastudy.utils.JsonUtil;
import com.java.study.javastudy.utils.RetryUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @Author： yijun
 * @DATE: 2023/9/16 21:00
 * @Description
 */

public class RetryUtilTest {
    private static final Logger log = LoggerFactory.getLogger(RetryUtilTest.class);
    @Test
    public void testRetry() {
        try {
            List<Person> people = RetryUtil.executeWithRetry(() -> getPeople(),
                    2, 100, true);
            log.info("{}", JsonUtil.toJson(people));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testRetryListener() {
        try {
            List<Person> people = RetryUtil.executeWithRetry(() -> getPeople(),
                    2, 1000, false,
                    new RetryListener() {
                        @Override
                        public <V> void onRetry(Attempt<V> attempt) {
                            Long attempNumber = attempt.getAttemptNumber();
                            Long delay = attempt.getDelaySinceFirstAttempt();
                            Boolean hasException = attempt.hasException();
                            Boolean hasResult =  attempt.hasResult();
                            System.out.println("重试结果："+attempNumber+","+delay+","+hasException+","+hasResult);
                        }
                    });
            log.info("{}", JsonUtil.toJson(people));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Person> getPeople() {
        if (Math.random() < 0.6) {
            return null;
        } else {
            return MockPeople.mockPeople(1, 3);
        }

    }

}
