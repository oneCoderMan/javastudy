# 仓库介绍
记录本人学习技术的一些笔记，包括不局限于Java

# Java基础
* 函数式编程 [code](basicTech/src/main/java/com/java/study/basic/FunctionProgrammer.java)

# 多线程编程
* 提交任务，等待所有任务都执行完在获取到结果 [code](basicTech/src/main/java/com/java/study/multiThread/ThreadPoolTest.java)
* 使用CompletableFuture进行线程编排，异步流程控制 [code](basicTech/src/main/java/com/java/study/multiThread/CompletableFutureTest.java)
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
# REF
1. readMe工具：https://github.com/ekalinin/github-markdown-toc

