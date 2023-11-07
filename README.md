# 仓库介绍
记录本人学习技术的一些笔记，包括不局限于Java

# 多线程编程
* 提交任务，等待所有任务都执行完在获取到结果 [code](basicTech/src/main/java/com/java/study/multithread/ThreadPoolTest.java)
* 使用CompletableFuture进行线程编排，异步流程控制 [code](basicTech/src/main/java/com/java/study/multithread/CompletableFutureTest.java)
   <br> [简单教学](https://www.liaoxuefeng.com/wiki/1252599548343744/1306581182447650)

# 各种工具类
* [Json解析的工具类](basicTech/src/main/java/com/java/study/utils/JsonUtil.java)
* [Gson库解析Json](basicTech/src/main/java/com/java/study/utils/GsonUtil.java)
* [Retry重试工具类](basicTech/src/main/java/com/java/study/utils/RetryUtil.java)
* [LocalDateTimeUtil工具类](basicTech/src/main/java/com/java/study/utils/LocalDateTimeUtil.java)
* [redis分布式锁](basicTech/src/main/java/com/java/study/middleware/redis/RedisLockService.java)

# ID生成器
* 参考文章<br>
 [5种全局ID生成方式](https://cloud.tencent.com/developer/article/1884037) <br>
 [Snowflake算法](https://pdai.tech/md/algorithm/alg-domain-id-snowflake.html)
* mysql自增id，可以自定义步长
* 雪花id <br>
  [雪花id实现](basicTech/src/main/java/com/java/study/idGenerator/snowFlake/SnowflakeIdGenerator.java)
* UUID <br>
  [UUID实现](basicTech/src/main/java/com/java/study/idGenerator/snowFlake/UuidGenerator.java)

# 中间件学习

## Redis
 * 使用StringRedisTemplate操作 [code](basicTech/src/main/java/com/java/study/middleware/redis/RedisClient.java)
 * 使用其它方法操作

## MySql

* [安装和原理](notes/src/main/resources/mysql/intro.md)

# 设计模式
## 一些技巧
  * 消除多余的if-else [策略模式code](basicTech/src/main/java/com/java/study/designpattern/somtech/OptStrategyContext.java)

## 策略模式

# Java+Spring基础
[函数式编程](basicTech/src/main/java/com/java/study/basic/FunctionProgrammer.java) <br>
[注解使用](notes/src/main/resources/javaCore/annotation.md) <br>
[切面](notes/src/main/resources/javaCore/aspect.md)

# 文章推荐
[20篇改变世界的白皮书](notes/src/main/resources/paper/20paper.md)

# REF
1. readMe工具：https://github.com/ekalinin/github-markdown-toc

