# MySQL架构
MySQL的逻辑架构图如下，MySQL可以分为**Server层**和**存储引擎层**两部分

| 组件     | 功能                                    |
|--------|---------------------------------------|
| Server层 | 连接器、查询缓存、分析器、优化器、执行器等，涵盖MySQL的大多数核心服务功能 |
| 存储引擎   | 负责数据的存储和提取。其架构模式是插件式的，支持InnoDB、MyISAM、Memory等多个存储引擎                                     |

> 默认使用的就是InnoDB，MySQL8.0没有查询缓存模块

<div align="center">
	<img src="https://github.com/oneCoderMan/javastudy/blob/f70fb795b40a00913f8d89e5efb69a5c7af213ea/notes/src/main/resources/mysql/pics/arch.png" alt="Editor" width="500">
</div>

# 连接器
连接器负责跟客户端建立连接、获取权限、维持和管理连接

一个简单的创建连接的命令
```
mysql -h$ip -P$port -u$user -p
```

过程：用来跟服务端建立连接 -> 在完成经典的TCP握手 -> 连接器会到权限表里面查出你拥有的权限 -> 权限判断逻辑 -> 没有后续的动作，这个连接就处于空闲状态 
-> 客户端如果太长时间没动静，连接器就会自动将它断开（wait_timeout控制的，默认值是8小时）

> 过程通常是比较复杂的，尽量减少建立连接的动作

# 分析器
分析器负责SQL语句的词法分析和语法分析，语义分析

# 优化器
负责优化SQL语句的查询


一些优化case
* 有多个索引的时候，决定使用哪个索引
* 在一个语句有多表关联（join）的时候，决定各个表的连接顺序

# 执行器
MySQL通过分析器知道了你要做什么，通过优化器知道了该怎么做，于是就进入了执行器阶段，开始执行语句。

过程：验证表的查询权限 -> 打开表 -> 调用引擎提供的接口进行数据行的读取 -> 讲符合条件的数据组装 -> 返回数据给客户端

# 日志模块
## redo log
所在层次：redo log是InnoDB存储引擎特有的

作用：提供再写入操作，恢复提交事务修改的页操作，用来保证事务的**持久性**

记录数据：记录的是"物理级别"上的页修改操作，比如页号xx、偏移量yyy写入了’zzz’数据。主要为了保证数据的可靠性;

如果每一次的更新操作都需要写进磁盘，然后磁盘也要找到对应的那条记录，然后再更新，整个过程IO成本、查找成本都很高。为了解决这个问题，MySQL采用了**WAL**（Write-Ahead Logging）技术

具体来说，当有一条记录需要更新的时候，InnoDB引擎就会先把记录写到**redo log**里面，并更新内存，这个时候更新就算完成了。同时，InnoDB引擎会在适当的时候，将这个操作记录更新到磁盘里面，而这个更新往往是在系统比较空闲的时候做

InnoDB的redo log是固定大小的，比如可以配置为一组4个文件，每个文件的大小是1GB，总共支持4GB大小的操作记录

组成：redo log包括两部分：一个是内存中的日志缓冲(redo log buffer) ，另一个是磁盘上的日志文件(redo log file)

<div align="center">
	<img src="https://github.com/oneCoderMan/javastudy/blob/c80c79f5903109e56424a70d369b08b48cb7700a/notes/src/main/resources/mysql/pics/redo.png" alt="Editor" width="500">
</div>

write pos: 当前记录的位置，一边写一边后移

checkpoint: 当前要擦除的位置(将 Checkpoint 之前的页刷新回磁盘)，也是往后推移并且循环的。

运行过程：write pos 和 CheckPoint 之间的就是 redo log file 上还空着的部分，可以用来记录新的操作；如果 write pos 追上 CheckPoint，就表示 redo log file 满了，
这时候不能再执行新的更新，得停下来先覆盖(擦掉)一些 redo log，把 CheckPoint 推进一下。

CheckPoint的其它问题：Checkpoint每次需要刷新多少数据？每次从哪里取脏页？以及什么时间触发 Checkpoint？

> 脏页:如果缓冲池中的页已经被修改了，但是还没有刷新到磁盘上，那么我们就称缓冲池中的这页是 ”脏页“

| checkPoint类型     | 机制 | 备注              |
|------------------|----|-----------------|
| Sharp Checkpoint | 数据库关闭时将所有的脏页都刷新回磁盘 | 默认方式，参数 innodb_fast_shutdown=1    |
| Fuzzy Checkpoin              | 只刷新一部分脏页，而不是刷新所有的脏页回磁盘 | InnoDB 存储引擎内部使用 |

crash-safe: 有了 redo log，InnoDB 就可以保证即使数据库发生异常重启，之前提交的记录都不会丢失，这个能力称为 crash-safe。

## binlog
所在层次：Server层自己的日志

>只依靠binlog是没有crash-safe能力的







# 问题
## 题目
1. 一个用户成功建立连接后，用管理员账号对这个用户的权限做了修改，会影响已经存在连接的权限吗？
2. 什么是长连接？什么是短连接？
3. 如果长连接累积下来，可能导致内存占用太大，被系统强行杀掉（OOM），导致MySQL异常重启，如何解决
4. 如果表T中没有字段k，而你执行了这个语句`select * from T where k=1`, 那肯定是会报“不存在这个列”的错误： `Unknown column ‘k’ in ‘where clause’`。这个错误是在上面提到的哪个阶段报出来？


## 答案
1. 不会影响，这个连接里面的权限判断逻辑，都将依赖于一开始读到的权限表记录
2. 数据库里面，长连接是指连接成功后，如果客户端持续有请求，则一直使用同一个连接。短连接则是指每次执行完很少的几次查询就断开连接，下次查询再重新建立一个。
3. a）程序里面判断执行过一个占用内存的大查询后，断开连接，之后要查询再重；b)MySQL 5.7或更新版本，可以在每次执行一个比较大的操作后，通过执行 mysql_reset_connection来重新初始化连接资源。这个过程不需要重连和重新做权限验证，但是会将连接恢复到刚刚创建完时的状态。
4. 分析器；在做语法分析的时候会