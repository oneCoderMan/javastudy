package com.java.study.Mock;


import com.java.study.model.Person;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author： yijun
 * @DATE: 2023/9/9 11:35
 * @Description
 */
public class MockPeople {
    public static List<Person> mockPeople(int id, int count) {
        List<Person> res = new ArrayList<>();
        int curId = id;
        for (int i = 0; i < count; i++) {
            Person person = new Person(curId, "test", "male");
            curId++;
            res.add(person);
        }
        return res;
    }

}
