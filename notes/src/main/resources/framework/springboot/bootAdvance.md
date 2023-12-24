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

应用类型推断的代码在
```java
this.webApplicationType = WebApplicationType.deduceFromClasspath();
```
三种类型：NONE，SERVLET，REACTIVE

调用 SpringApplication 对象的 run() 方法时会创建`ApplicationContext`，
最后调用`ApplicationContext` 的 `refresh()` 方法完成初始化。

在创建与初始化完成之间的一些拓展功能就由 `ApplicationContext 初始化器`完成。


## SpringApplication的运行run
### 事件发布
[运行run事件发布演示代码](../../../../../../basicTech/src/main/java/com/java/study/frameworkstudy/springboot/launch/TestBootRun.java)

`SpringApplicationRunListeners` 中使用 `SpringApplicationRunListener` 来描述单个事件发布器，
`SpringApplicationRunListener` 是一个接口，它有且仅有一个实现类 `EventPublishingRunListener`。

在 SpringBoot 中，事件发布器都是在配置文件中读取，从 `META-INF/spring.factories` 中读取，

### 创建Spring容器
[演示代码](basicTech/src/main/java/com/java/study/frameworkstudy/springboot/launch/CreateSpringContainer.java)



### 其它



# REF
[B站视频](https://www.bilibili.com/video/BV1P44y1N7QG?p=120&vd_source=550dc9095f2a0980780a8fe0a239112e) <br>
[Boot原理文字](https://mofan212.github.io/posts/Spring-Forty-Nine-Lectures-Spring-Boot/)