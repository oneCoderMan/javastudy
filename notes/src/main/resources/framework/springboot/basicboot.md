# 1. SpringBoot基础

## 1.1 starter介绍
将常用的依赖分组进行了整合，如`spring-boot-starter-web`主要作用是打包了Web开发场景所需的
底层所有依赖（基于依赖传递，当前项目也存在对应依赖的Jar包）

因此，在`pom.xml`中引入`spring-boot-starter-web`依赖启动时，就可以实现Web场景开发（如SpringMVC，Tomcat），
不需要额外导入Tomcat服务器及其它Web依赖文件

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

SpringBoot官方提供了许多依赖启动，可以[点击查看](https://docs.spring.io/spring-boot/docs/2.7.18-SNAPSHOT/reference/html/using.html#using.build-systems.starters)

也可以自己写Starter

## 1.2 自动配置
自动配置： 根据添加的Jar包依赖，`SpringBoot`会自动将一些配置类的`Bean`注册进入IOC容器，
在需要的地方使用`@autowired`或者`@resource`注解来使用它

需要了解的两个问题：
1. SpringBoot如何进行自动装配的？
2. 需要把哪些组件进行自动装配？

### 核心启动类
被`@SpringBootApplication`注解修饰的类是SpringBoot的核心启动类，是应用的入口

这个注解核心的能力是有下面三个注解提供的
```java
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = { @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
		@Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
```

#### SpringBootConfiguration
其中`@SpringBootConfiguration`包装了`@Configuration`，表明该类是一个配置类

#### EnableAutoConfiguration
`@EnableAutoConfiguration`启动自动配置功能

可以看到该注解也是一个组合注解
```java
@AutoConfigurationPackage
@Import(AutoConfigurationImportSelector.class)
```

`@AutoConfigurationPackage`的功能是干一个事，注册一个`org.springframework.boot.autoconfigure.AutoConfigurationPackages`，
保存自动配置类以后续使用

`@Import(AutoConfigurationImportSelector.class)`

将`AutoConfigurationImportSelector`这个类导入到Spring容器中

`AutoConfigurationImportSelector`可以帮助SpringBoot应用将所有**符合条件**的`@Configuration`
配置都加载到当前SpringBoot创建并使用的IOC容器中

`AutoConfigurationImportSelector`重点实现了`DeferredImportSelector`和各种`Aware`接口

自动配置实现的逻辑入口方法在`DeferredImportSelectorGrouping`类中的的' 



注解`@EnableAutoConfiguration`借助`@Import`来收集所有符合自动配置条件的Bean定义，并加载到IoC容器

> `Spring`中有许多以`Enable`开头的注解，其作用就是借助`@Import`来收集并注册特定场景相关
> 的`Bean`，并加载到IoC容器。<br>
> `@Import`作用就是给容器导入一个组件

# REF
[B站SpringBoot源码剖析](https://www.bilibili.com/video/BV1Jo4y1m7hY?p=3&spm_id_from=pageDriver&vd_source=550dc9095f2a0980780a8fe0a239112e)