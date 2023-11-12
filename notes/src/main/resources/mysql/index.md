# 1. 索引介绍
索引的出现其实就是为了提高数据查询的效率，索引是在存储引擎层实现的



# 2. InnoDB的索引模型
在InnoDB中，表都是根据主键顺序以索引的形式存放的(索引组织表)

InnoDB使用了B+树索引模型，所以数据都是存储在B+树中的。

我们有一个主键列为ID的表，表中有字段k，并且在k上有索引。建表语句如下：
```mysql
create table T(
id int primary key, 
k int not null, 
name varchar(16),
index (k)) engine=InnoDB;
```
有5个数据：分别为(100,1)、(200,2)、(300,3)、(500,5)和(600,6)
那么这两个索引组织的数如下：


# 3. 