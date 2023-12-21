# 01 仓库介绍
记录本人学习技术的一些笔记，包括不局限于Java

# 02. 多线程编程
* 提交任务，等待所有任务都执行完在获取到结果 [code](basicTech/src/main/java/com/java/study/multithread/ThreadPoolTest.java)
* 使用CompletableFuture进行线程编排，异步流程控制 [code](basicTech/src/main/java/com/java/study/multithread/CompletableFutureTest.java)
   <br> [简单教学](https://www.liaoxuefeng.com/wiki/1252599548343744/1306581182447650)

# 03. 各种工具类
* [Json解析的工具类](basicTech/src/main/java/com/java/study/utils/JsonUtil.java)
* [Gson库解析Json](basicTech/src/main/java/com/java/study/utils/GsonUtil.java)
* [Retry重试工具类](basicTech/src/main/java/com/java/study/utils/RetryUtil.java)
* [LocalDateTimeUtil工具类](basicTech/src/main/java/com/java/study/utils/LocalDateTimeUtil.java)
* [redis分布式锁](basicTech/src/main/java/com/java/study/middleware/redis/RedisLockService.java)

# 04. ID生成器
* 参考文章<br>
 [5种全局ID生成方式](https://cloud.tencent.com/developer/article/1884037) <br>
 [Snowflake算法](https://pdai.tech/md/algorithm/alg-domain-id-snowflake.html)
* mysql自增id，可以自定义步长
* 雪花id <br>
  [雪花id实现](basicTech/src/main/java/com/java/study/idGenerator/snowFlake/SnowflakeIdGenerator.java)
* UUID <br>
  [UUID实现](basicTech/src/main/java/com/java/study/idGenerator/snowFlake/UuidGenerator.java)

# 05. 中间件学习

## Redis
 * 使用StringRedisTemplate操作 [code](basicTech/src/main/java/com/java/study/middleware/redis/RedisClient.java)
 * 使用其它方法操作

## MySQL

* [安装和原理](notes/src/main/resources/mysql/intro.md)
* [常用命令](notes/src/main/resources/mysql/cmd.md)

## Kafka

* [安装和使用](notes/src/main/resources/kafka/install.md)

# 06. 设计模式
## 一些技巧
  * 消除多余的if-else [策略模式code](basicTech/src/main/java/com/java/study/designpattern/somtech/OptStrategyContext.java)

## 模式
[策略模式](notes/src/main/resources/desiginpattern/strategy.md) <br>
[代理模式](notes/src/main/resources/desiginpattern/proxy.md) <br>
[模版方法模式](notes/src/main/resources/desiginpattern/template.md)

# 07. Java+Spring基础
[函数式编程](basicTech/src/main/java/com/java/study/basic/FunctionProgrammer.java) <br>
[注解使用](notes/src/main/resources/javaCore/annotation.md) <br>
[切面](notes/src/main/resources/javaCore/aspect.md) <br>
[枚举及应用](notes/src/main/resources/javaCore/enum.md) <br>

# 08. 框架原理
[Spring的一些注解](notes/src/main/resources/framework/spring/springannotation.md) <br>
[Spring进阶](notes/src/main/resources/framework/spring/springadvance.md) <br>
[SpringBoot基础](notes/src/main/resources/framework/springboot/basicboot.md)

# 08. 编程实践
[AOP+Redis延时双删](notes/src/main/resources/exp/aopredisdelay.md)

# 09. 工具
[idea常用快捷键](notes/src/main/resources/tool/ideashotkey.md)

# 10. 文章推荐
[20篇改变世界的白皮书](notes/src/main/resources/paper/20paper.md)

# 11. 算法
[基础算法介绍](notes/src/main/resources/algorithm/intro.md) <br>
[微软算法题库](notes/src/main/resources/algorithm/microsoft.md)


# REF
1. readMe工具：https://github.com/ekalinin/github-markdown-toc

