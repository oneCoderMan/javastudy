package com.java.study.model;

import com.java.study.basic.enumtest.TypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author： yijun
 * @DATE: 2023/9/9 11:26
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private Integer id;
    private String name;
    private String gender;
    private TypeEnum type;

    public Person(Integer id, String name, String gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }
}
