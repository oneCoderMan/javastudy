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



主要的`ApplicationContext`的实现都组合（有成员变量）了`BeanFactory`的功能

# 2. BeanFactory
这是一个接口，表面上看接口只有`getBean`这个函数，实际上控制反转，依赖注入，Bean生命周期的各种
功能都由它的实现类提供，

它的一个重要实现类`DefaultListableBeanFactory`，

# REF
[B站-Spring视频](https://www.bilibili.com/video/BV1P44y1N7QG?p=4&vd_source=550dc9095f2a0980780a8fe0a239112e)

