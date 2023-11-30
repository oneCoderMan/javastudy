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

可以通过代码模拟`ConfigurationClassPostProcessor`的工作过程，[demoMockCode](../../../../../../basicTech/src/main/java/com/java/study/frameworkstudy/spring/beanfacoryposttest/MockConfigPost.java)



# REF
[B站-Spring视频](https://www.bilibili.com/video/BV1P44y1N7QG?p=4&vd_source=550dc9095f2a0980780a8fe0a239112e)
[容器与Bean](https://mofan212.github.io/posts/Spring-Forty-Nine-Lectures-Container-And-Bean/)

