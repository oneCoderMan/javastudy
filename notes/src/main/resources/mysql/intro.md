# 0x00 MySQL介绍
MySQL 是一个开源关系型数据库管理系统，其主要通过表结构来存储数据，每一个列称为一个字段，每一行称为一个记录，每一个列的集合称为数据表，每一个表的集合称为数据库

发展历程如下：

![MySQL的发展历程](https://github.com/oneCoderMan/javastudy/blob/e4cfa533c6fc9666e8a2e50d452a960c1920a11d/notes/src/main/resources/mysql/pics/mysqlVersion.png)


# 0x01 安装
参考文档：https://hub.docker.com/_/mysql
版本：8.0

第一步：拉取镜像
>docker pull mysql:8.0.35

第二步：本机创建一个存储数据的路径
> mkdir /opt/mysql/datadir

第三步：启动容器
>docker run --name yijun-mysql \
-v /opt/mysql/datadir:/var/lib/mysql \
-p 3306:3306 \
-e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql:8.0.35 \
--character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci

稍等以下，容器启动之后，MySQL-server就会启动

第四步：查看日志
> docker logs yijun-mysql

第五步：登陆
> docker exec -it yijun-mysql /bin/bash <br>
> mysql -u root -p
> 
> 

# 0x02 MySQL基础
* [架构之SQL语句执行过程](./arch.md)
* [MySQL事务](./tx.md)
* [MySQL索引](./index.md)
* [MySQL锁](./lock.md)

# 0x03 MySQL实践


# REF
[2023中国数据库行业分析报告](https://www.cnblogs.com/modb/p/17754420.html) <br>
[MySQL45讲](https://funnylog.gitee.io/mysql45/)