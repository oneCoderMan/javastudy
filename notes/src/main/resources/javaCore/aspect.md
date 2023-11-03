# 0x00 切面概念
AOP（Aspect Oriented Programming），面向切面思想，是Spring的三大核心思想之一<br>
简单地去理解，其实AOP要做三类事：
* 在哪里切入，也就是权限校验等非业务操作在哪些业务代码中执行。
* 在什么时候切入，是业务代码执行前还是执行后。
* 切入后做什么事，比如做权限校验、日志记录等。

具体AOP体系如下图所示：

<div align="center">
	<img src="https://github.com/oneCoderMan/javastudy/blob/2cdccd42d38477dff0e34ed2008d99d4b1453c64/notes/src/main/resources/pics/aspect.png" alt="Editor" width="500">
</div>

一些概念详解：

* **Pointcut**：切点，决定处理如权限校验、日志记录等在何处切入业务代码中（即织入切面）。切点分为execution方式和annotation方式。前者可以用路径表达式指定哪些类织入切面，后者可以指定被哪些注解修饰的代码织入切面。
* **Advice**：处理，包括处理时机和处理内容。处理内容就是要做什么事，比如校验权限和记录日志。处理时机就是在什么时机执行处理内容，分为前置处理（即业务代码执行前）、后置处理（业务代码执行后）等。 
* **Aspect**：切面，即Pointcut和Advice。
* **Joint point**：连接点，是程序执行的一个点。例如，一个方法的执行或者一个异常的处理。在 Spring AOP 中，一个连接点总是代表一个方法执行。
* **Weaving** ：织入，就是通过动态代理，在目标对象方法中执行处理内容的过程。

# 0x01 切面实例


# REF
[切面AOP优雅的实现权限校验](https://mp.weixin.qq.com/s/DjWq1Kw9kPMlt3l_3HDK0Q)