# 1. 代理模式介绍
代理模式是一种结构型设计模式， 让你能够提供对象的替代品或其占位符。 代理控制着对于原对象的访问， 并允许在将请求提交给对象前后进行一些处理。

作用：使用代理对象来代替对真实对象(real object)的访问，
这样就可以在不修改原目标对象的前提下，
提供额外的功能操作，扩展目标对象的功能。

# 2. 静态代理
对目标对象的每个方法的增强都是手动完成的，UML类图如下：

<div align="center">
	<img src="https://github.com/oneCoderMan/javastudy/blob/6de5b04a392edb81df568c4bfc322a45cec4bd6c/notes/src/main/resources/desiginpattern/pics/proxy1.png" alt="Editor" width="600">
</div>


缺点：
1. 非常不灵活（比如接口一旦新增加方法，目标对象和代理对象都要进行修改）
2. 麻烦(需要对每个目标类都单独写一个代理类）

静态代理实现步骤:
1. 定义一个接口及其实现类；
2. 创建一个代理类同样实现这个接口
3. 将目标对象注入进代理类，然后在代理类的对应方法调用目标类中的对应方法。
4. 可以通过代理类屏蔽对目标对象的访问，并且可以在目标方法执行前后做一些自己想做的事情。

[实现代码](../../../../../basicTech/src/main/java/com/java/study/designpattern/proxy/staticproxy/ClientTest.java)

# 3. 动态代理
无需声明式的创建Java代理类，而是在运行过程中生成"虚拟"的代理类，被ClassLoader加载。
从而避免了静态代理那样需要声明大量的代理类。

## 3.2 JDK动态代理
在 Java 动态代理机制中`InvocationHandler`接口和`Proxy`类是核心。

缺点：只能代理实现了接口的类

### 使用步骤
1. 定义一个接口及其实现类
2. 自定义`InvocationHandler`并重写`invoke`方法，
在`invoke`方法中我们会调用原生方法（被代理类的方法）并自定义一些处理逻辑
3. 通过`Proxy.newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h)` 方法创建代理对象

### 代码DEMO
[实现代码CLIENT DEMO](../../../../../basicTech/src/main/java/com/java/study/designpattern/proxy/dynamicproxy/DyClient.java)<br>
[定义一个接口](../../../../../basicTech/src/main/java/com/java/study/designpattern/proxy/dynamicproxy/SmsService.java) <br>
[写一个JDK 动态代理类](../../../../../basicTech/src/main/java/com/java/study/designpattern/proxy/dynamicproxy/DebugInvocationHandler.java)<br>
[写一个代理对象的工厂类](../../../../../basicTech/src/main/java/com/java/study/designpattern/proxy/dynamicproxy/JdkProxyFactory.java)

### 涉及类简介
`Proxy`类中使用频率最高的方法是：`newProxyInstance()` ，这个方法主要用来生成一个代理对象。

这个方法一共有 3 个参数：
1. loader :类加载器，用于加载代理对象。
2. interfaces : 被代理类实现的一些接口；
3. h : 实现了 InvocationHandler 接口的对象；

实现`InvocationHandler`来自定义处理逻辑。 当我们的动态代理对象调用一个方法时，
这个方法的调用就会被转发到实现`InvocationHandler`接口类的`invoke`方法来调用

invoke() 方法有下面三个参数：
1. proxy :动态生成的代理类
2. method : 与代理类对象调用的方法相对应
3. args : 当前 method 方法的参数

## 3.2 CGLIB代理

[CGLIB](https://github.com/cglib/cglib) (Code Generation Library)是一个基于[ASM](https://www.baeldung.com/java-asm)的字节码生成库，
它允许我们在运行时对字节码进行修改和动态生成。CGLIB 通过继承方式实现代理。

在 CGLIB 动态代理机制中`MethodInterceptor`接口和`Enhancer`类是核心。

原理：CGLIB 动态代理是通过生成一个被代理类的子类来拦截被代理类的方法调用

> 例如 Spring 中的 AOP 模块中：如果目标对象实现了接口，则默认采用 JDK 动态代理，
> 否则采用 CGLIB 动态代理。

## 使用步骤
需要添加依赖
```xml
<dependency>
  <groupId>cglib</groupId>
  <artifactId>cglib</artifactId>
  <version>3.3.0</version>
</dependency>
```
1. 定义一个类
2. 自定义`MethodInterceptor`并重写`intercept`方法，`intercept`用于拦截增强被代理类的方法，和`JDK`动态代理中的`invoke`方法类似
3. 通过`Enhancer`类的`create()`创建代理类


## 代码DEMO
[写一个具体工作类](../../../../../basicTech/src/main/java/com/java/study/designpattern/proxy/cglibproxy/AliSmsService.java) <br>
[自定义方法拦截器](../../../../../basicTech/src/main/java/com/java/study/designpattern/proxy/cglibproxy/DebugMethodInterceptor.java) <br>
[获取代理类](../../../../../basicTech/src/main/java/com/java/study/designpattern/proxy/cglibproxy/CglibProxyFactory.java) <br>
[客户端调用](../../../../../basicTech/src/main/java/com/java/study/designpattern/proxy/cglibproxy/CgClient.java)

## 涉及类简介
通过`Enhancer`类来动态获取被代理类，当代理类调用方法的时候，实际调用的是`MethodInterceptor`中的`intercept`方法。


# REF
[Java Guide代理模式](https://javaguide.cn/java/basis/proxy.html)