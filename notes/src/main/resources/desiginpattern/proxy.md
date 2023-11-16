# 1. 代理模式介绍
作用：使用代理对象来代替对真实对象(real object)的访问，
这样就可以在不修改原目标对象的前提下，
提供额外的功能操作，扩展目标对象的功能。

# 2. 静态代理
对目标对象的每个方法的增强都是手动完成的
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


# 3. 动态代理

## JDK代理

## CGLIB代理

# REF
[Java Guide代理模式](https://javaguide.cn/java/basis/proxy.html)