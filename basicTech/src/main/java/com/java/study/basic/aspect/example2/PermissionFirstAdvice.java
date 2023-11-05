package com.java.study.basic.aspect.example2;

import com.java.study.model.CommonResponse;
import com.java.study.model.Person;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author： yijun
 * @DATE: 2023/11/5 09:48
 * @Description
 */
@Aspect
@Component
@Order(1)
public class PermissionFirstAdvice {
    // 定义一个切面，括号内写入第1步中自定义注解的路径
    @Pointcut("@annotation(com.java.study.basic.aspect.example2.PermissionAnnotation)")
    private void permissionCheck() {
    }

    @Around("permissionCheck()")
    public Object permissionCheckFirst(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("===================第一个切面===================：" + System.currentTimeMillis());

        //获取请求参数，详见接口类
        Object[] objects = joinPoint.getArgs();
        Integer id = ((Person) objects[0]).getId();
        String name = ((Person) objects[0]).getName();
        System.out.println("id1->>>>>>>>>>>>>>>>>>>>>>" + id);
        System.out.println("name1->>>>>>>>>>>>>>>>>>>>>>" + name);

        // id小于0则抛出非法id的异常
        if (id < 0) {
            return CommonResponse.createFailureResponse("error user id");
        }
        return joinPoint.proceed();
    }


}
