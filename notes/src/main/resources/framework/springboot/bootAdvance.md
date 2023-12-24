# SpringBoot的启动过程
12大步骤，7大事件

一个Boot主程序一般如下所示：
```java
@SpringBootApplication
public class BasicTechApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(BasicTechApplication.class, args);
    }
}
```
## SpringApplication的构造
构造`SpringApplication` 对象时做了如下几件事：方法之后主要的事情如下：
1. 获取 `Bean Definition` 源
2. 推断应用类型
3. 添加`ApplicationContext`初始化器
4. 添加事件监听器
5. 主类推断

[构造SpringApplication的演示代码](../../../../../../basicTech/src/main/java/com/java/study/frameworkstudy/springboot/launch/TestSpringApplication.java)

## SpringApplication的运行

# REF
[B站视频](https://www.bilibili.com/video/BV1P44y1N7QG?p=120&vd_source=550dc9095f2a0980780a8fe0a239112e) <br>
[Boot原理文字](https://mofan212.github.io/posts/Spring-Forty-Nine-Lectures-Spring-Boot/)