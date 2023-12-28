# 0x01. SpringBoot的启动过程
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
[创建Spring容器演示代码](../../../../../../basicTech/src/main/java/com/java/study/frameworkstudy/springboot/launch/CreateSpringContainer.java)

在 SpringBoot 启动成功后，可以执行一些 `Runner`，进行一些预处理或测试。`Runner`有两种，
分别是`CommandLineRunner` 和 `ApplicationRunner`

[第五步：读取配置](../../../../../../basicTech/src/main/java/com/java/study/frameworkstudy/springboot/launch/EnvStep5Test.java)

### 运行步骤流程
1. 得到 `SpringApplicationRunListeners` 事件发布器, 
发布 `Application Starting` 事件 1️⃣

2. 封装启动 args

3. 准备 `Environment` 添加命令行参数

4. `ConfigurationPropertySources` 处理
发布 `Application Environment` 已准备事件 2️⃣

5. 通过 `EnvironmentPostProcessorApplicationListener` 进行 env 后处理
`application.properties` 由 `StandardConfigDataLocationResolver` 解析

6. 绑定 `spring.main` 到 `SpringApplication` 对象

7. 打印 Banner

8. 创建容器

9. 准备容器
发布`Application Context` 已初始化事件 3️⃣

10. 加载 Bean 定义
发布 `Application Prepared` 事件 4️⃣

11. refresh 容器
发布 `Application Started` 事件 5️⃣

12. 执行 Runner
发布 `Application Ready` 事件 6️⃣
这其中有异常，发布 `Application Failed` 事件7️⃣


    

# 0x02. 自动装配
## 自动配置类原理
[模拟自动装配代码](../../../../../../basicTech/src/main/java/com/java/study/frameworkstudy/springboot/autoconfig/AutoConfigTest.java)

使用注解@`Import({AutoConfiguration1.class, AutoConfiguration2.class})`

如果有多个第三方配置类，难不成到一个个地导入？
可以使用导入选择器`ImportSelector`，重写 `selectImports()` 方法，
返回需要自动装配的`Bean`的全限定类名数组

[ImportSelector演示代码](../../../../../../basicTech/src/main/java/com/java/study/frameworkstudy/springboot/autoconfig/AutoConfigTest2.java)

如果 `selectImports()` 方法返回的全限定类名可以从文件中读取，就更方便了。
在当前项目的类路径下创建 `META-INF/spring.factories` 文件，
约定一个 key，对应的 value 即为需要指定装配的 Bean

[从文件读取自动装配的类](../../../../../../basicTech/src/main/java/com/java/study/frameworkstudy/springboot/autoconfig/AutoConfigTest3.java)

第三方与本项目出现同名Bean的时候通过
`DeferredImportSelector`和`@ConditionalOnMissingBean`解决Bean冲突覆盖问题

## AOP的自动配置
[AOP自动配置演示代码](../../../../../../basicTech/src/main/java/com/java/study/frameworkstudy/springboot/autoconfig/TestAopConfig.java)

## 数据库相关的自动配置
[数据库相关的自动配置](../../../../../../basicTech/src/main/java/com/java/study/frameworkstudy/springboot/autoconfig/TestDatasourceConfig.java)

## 自定义配置类



# 0x03. 

# REF
[B站视频](https://www.bilibili.com/video/BV1P44y1N7QG?p=120&vd_source=550dc9095f2a0980780a8fe0a239112e) <br>
[Boot原理文字](https://mofan212.github.io/posts/Spring-Forty-Nine-Lectures-Spring-Boot/)