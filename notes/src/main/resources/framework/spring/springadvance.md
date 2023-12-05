# 1. ApplicationContext
这个类的继承关系如下图所示: [代码参考](../../../../../../basicTech/src/main/java/com/java/study/frameworkstudy/springboot/SpringBootDemo.java) <br>
<div align="center">
	<img src="https://github.com/oneCoderMan/javastudy/blob/fbd2996df3f65c79674382337843f62348431d5f/notes/src/main/resources/framework/spring/pics/diagram1.png" alt="Editor" width="800">
</div>

`ApplicationContext`的父接口是`BeanFactory`，`BeanFactory`是Spring的核心容器，其实现
的接口是拓展了新的功能

`MessageSource`提供国际化能力

`ResourcePatternResolver` 提供资源正则解析的能力

`EnvironmentCapable` 提供环境配置的能力


主要有如下一个重要实现类`AnnotationConfigApplicationContext`，这是一个比较经典的容器，
基于Java的配置类创建Bean [使用和介绍](../../../../../../basicTech/src/main/java/com/java/study/frameworkstudy/spring/AnnotationConfigAppCtxDemo.java)

主要的`ApplicationContext`的实现都组合（有成员变量）了`BeanFactory`的功能

# 2. BeanFactory
这是一个接口，表面上看接口只有`getBean`这个函数，实际上控制反转，依赖注入，Bean生命周期的各种
功能都由它的实现类提供，

它的一个重要实现类`DefaultListableBeanFactory`，[一些使用和介绍](../../../../../../basicTech/src/main/java/com/java/study/frameworkstudy/spring/SpringBeanFactoryDemo.java)

# 3. Bean的生命周期
先构造方法 -> 依赖注入方法 ->  初始化（`@PostConstruct`）-> 销毁（`@PreDestroy`）

[演示代码](../../../../../../basicTech/src/main/java/com/java/study/frameworkstudy/springboot/beancycle/LifeCycleBean.java)

# 4. Bean的后置处理器
针对Bean的生命周期各个阶段，Spring提供了不同的后处理器

在BeanFactory中，有用到了模版方法，具体由一个案例了解，[BeanFactory模版模式mini](../../../../../../basicTech/src/main/java/com/java/study/frameworkstudy/spring/TemplateMethodTest.java)

Bean的常见后处理器测试及其使用，[代码](../../../../../../basicTech/src/main/java/com/java/study/frameworkstudy/spring/beanposttest/BeanPostTest.java)

## AutowiredAnnotationBeanPostProcessor
在Bean的组织过程中，Bean的后处理器`AutowiredAnnotationBeanPostProcessor`完成对`@Autowired`和`@Value` 注解的解析

[Test Code](../../../../../../basicTech/src/main/java/com/java/study/frameworkstudy/spring/beanposttest/AutowiredBeanPostTest.java)

# 5. BeanFactory的后置处理器
为BeanFactory提供扩展，[学习代码](../../../../../../basicTech/src/main/java/com/java/study/frameworkstudy/spring/beanfacoryposttest/AppTest.java)

比如`ConfigurationClassPostProcessor`可以完成`@ComponentScan`，`@Bean`， `@Import`，`@ImportResource`注解的解析工作

可以通过代码模拟`ConfigurationClassPostProcessor`的工作过程，[模拟ComponentScan注解](../../../../../../basicTech/src/main/java/com/java/study/frameworkstudy/spring/beanfacoryposttest/MockConfigPost.java)

[模拟处理@Bean注解](../../../../../../basicTech/src/main/java/com/java/study/frameworkstudy/spring/beanfacoryposttest/MockBeanDeal.java)

[模拟处理@Mapper注解](../../../../../../basicTech/src/main/java/com/java/study/frameworkstudy/spring/beanfacoryposttest/MockMapperDeal.java)

问题：`@Mapper`注解是在接口上使用的，但根据前文内容可知，`@Mapper`被解析后在 Spring 容器中也存在与被标记的接口相关的`Bean`。
那么那这些接口是怎么变成对象被 Spring 管理的呢？

答案：这依赖于`MapperFactoryBean`将接口转换为对象

# 6. Aware接口和InitializingBean接口
`Aware`提供内置的注入手段，该接口用于注入一些与容器相关的信息，比如：

`BeanNameAware` 注入 Bean 的名字 [代码测试](../../../../../../basicTech/src/main/java/com/java/study/frameworkstudy/spring/aware/AppAwareTest.java)

`BeanFactoryAware` 注入 BeanFactory 容器

`ApplicationContextAware` 注入 ApplicationContext 容器

`EmbeddedValueResolverAware` 解析 ${}

`InitializingBean`接口提供了一种内置的初始化手段 [代码测试](../../../../../../basicTech/src/main/java/com/java/study/frameworkstudy/spring/aware/AppAwareTest.java)

内置的注入和初始化不受扩展功能的影响，总会被执行，Spring框架的内部的类常用他们

问题：`BeanFactoryAware` 、`ApplicationContextAware` 和 
`EmbeddedValueResolverAware` 三个接口的功能可以使用 `@Autowired` 注解实现，
`InitializingBean` 接口的功能也可以使用 `@PostConstruct` 注解实现，为什么还要使用接口呢？

解析：`@Autowired` 和 `@PostConstruct` 注解的解析需要使用 Bean 后置处理器，属于拓展功能，
而这些接口属于内置功能，不加任何拓展 Spring 就能识别。在某些情况下，拓展功能会失效，而内容功能不会失效。

## 初始化Bean手段
初始化Bean的时候可以执行一些方法，使Bean的初始化功能完成，
初始化 Bean 的实现有三种：

* 依赖于后置处理器`@PostConstruct`提供的拓展功能
* 相关接口`InitializingBean`的功能
* 使用 `@Bean`注解中的属性`initMethod`进行指定
当同时存在以上三种方式时，它们的执行顺序也将按照上述顺序进行执行

[初始化演示](../../../../../../basicTech/src/main/java/com/java/study/frameworkstudy/spring/aware/BeanInitApp.java)


## 销毁Bean手段
销毁Bean的时候可以执行一些方法，使Bean的退出功能完成，
销毁 Bean 的实现有三种：

* 依赖于后置处理器`@PreDestroy`提供的拓展功能
* 相关接口`DisposableBean`的功能
* 使用 `@Bean`注解中的属性`destroyMethod`进行指定
  当同时存在以上三种方式时，它们的执行顺序也将按照上述顺序进行执行
* 
[销毁Bean演示](../../../../../../basicTech/src/main/java/com/java/study/frameworkstudy/spring/aware/BeanInitApp.java)


# REF
[B站-Spring视频](https://www.bilibili.com/video/BV1P44y1N7QG?p=4&vd_source=550dc9095f2a0980780a8fe0a239112e) <br>
[容器与Bean](https://mofan212.github.io/posts/Spring-Forty-Nine-Lectures-Container-And-Bean/)

