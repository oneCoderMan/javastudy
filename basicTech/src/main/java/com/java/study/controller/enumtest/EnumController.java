package com.java.study.controller.enumtest;

import com.java.study.model.CommonResponse;
import com.java.study.model.Person;
import com.java.study.utils.JsonUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author： yijun
 * @DATE: 2023/11/13 21:52
 * @Description
 */
@RestController
@RequestMapping(value = "/enum")
public class EnumController {
    @RequestMapping(value = "/testEnum", method = RequestMethod.POST)
    public CommonResponse getEnumPersonList(@RequestBody Person person) {
        System.out.println(JsonUtil.toJson(person));
        return CommonResponse.createSuccessResponse("this is enum test");
    }
}
