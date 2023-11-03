# 切面
AOP（Aspect Oriented Programming），面向切面思想，是Spring的三大核心思想之一<br>
简单地去理解，其实AOP要做三类事：
* 在哪里切入，也就是权限校验等非业务操作在哪些业务代码中执行。
* 在什么时候切入，是业务代码执行前还是执行后。
* 切入后做什么事，比如做权限校验、日志记录等。

具体AOP体系如下图所示：

<div align="center">
	<img src="https://github.com/oneCoderMan/javastudy/blob/2cdccd42d38477dff0e34ed2008d99d4b1453c64/notes/src/main/resources/pics/aspect.png" alt="Editor" width="500">
</div>


# REF
[切面AOP优雅的实现权限校验](https://mp.weixin.qq.com/s/DjWq1Kw9kPMlt3l_3HDK0Q)