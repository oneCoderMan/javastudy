package com.java.study.controller.aspect;

import com.java.study.basic.aspect.example2.PermissionAnnotation;
import com.java.study.model.CommonResponse;
import com.java.study.model.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author： yijun
 * @DATE: 2023/11/4 23:18
 * @Description
 */
@RestController
@RequestMapping(value = "/aop")
public class AspectTestController {
    @GetMapping(value = "/getTest")
    public CommonResponse testAspect() {
        return CommonResponse.createSuccessResponse("this is a aop test");
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    // 添加这个注解
    @PermissionAnnotation()
    public CommonResponse getGroupList(@RequestBody Person person) {
        return CommonResponse.createSuccessResponse("this is auth aop test");
    }

}
