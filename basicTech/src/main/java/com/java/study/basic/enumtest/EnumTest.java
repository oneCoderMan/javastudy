package com.java.study.basic.enumtest;

import com.java.study.model.Person;
import com.java.study.utils.JsonUtil;

/**
 * @Author： yijun
 * @DATE: 2023/11/13 21:48
 * @Description
 */
public class EnumTest {
    public static void main(String[] args) {
        Integer code = 1;
        // TypeEnum byValue = TypeEnum.getByValue(code);
        // System.out.println(byValue);
        String rawStr = "{\"id\":1,\"name\":\"yijun\",\"type\":\"音频\"}";
        Person person = JsonUtil.fromJson(rawStr, Person.class);
        System.out.println(person);

    }
}
