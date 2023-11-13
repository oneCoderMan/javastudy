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
<div align="center">
	<img src="https://github.com/oneCoderMan/javastudy/blob/844e9538e0c17bdee8c1b0ef73b888ae331f82f4/notes/src/main/resources/mysql/pics/index.png" alt="Editor" width="500">
</div>

| 索引分类 | 叶子节点形态 |          别名           |
|:----:| :----: |:---------------------:| 
| 主键索引  | 叶子节点存的是整行数据 | 聚簇索引（clustered index） | 
| 非主键索引  | 叶子节点内容是主键的值 | 二级索引（secondary index） | 

基于主键索引和普通索引的查询有什么区别？
* 如果语句是select * from T where ID=500，即主键查询方式，则只需要搜索ID这棵B+树；
* 如果语句是select * from T where k=5，即普通索引查询方式，则需要先搜索k索引树，得到ID的值为500，再到ID索引树搜索一次。这个过程称为回表。

>基于非主键索引的查询需要多扫描一棵索引树。因此，我们在应用中应该尽量使用主键查询。


# 3. 索引优化（覆盖索引）
一个例子如下：
```mysql
mysql> create table T (
ID int primary key,
k int NOT NULL DEFAULT 0, 
s varchar(16) NOT NULL DEFAULT '',
index k(k))
engine=InnoDB;

insert into T values(100,1, 'aa'),(200,2,'bb'),(300,3,'cc'),(500,5,'ee'),(600,6,'ff'),(700,7,'gg');
```

两颗索引树如下：


问题：执行`select * from T where k between 3 and 5`，需要执行几次树的搜索操作，会扫描多少行？

这个SQL执行流程如下：
1. 在k索引树上找到k=3的记录，取得 ID = 300；
2. 再到ID索引树查到ID=300对应的R3；
3. 在k索引树取下一个值k=5，取得ID=500；
4. 再回到ID索引树查到ID=500对应的R4；
5. 在k索引树取下一个值k=6，不满足条件，循环结束。

这个查询过程读了k索引树的3条记录（步骤1、3和5），回表了两次（步骤2和4）。

优化思路：由于查询结果所需要的数据只在主键索引上有，所以不得不回表。那么，有没有可能经过索引优化，避免回表过程呢？

具体实践：如果执行的语句是`select ID from T where k between 3 and 5`
这时只需要查ID的值，而ID的值已经在k索引树上了，因此可以直接提供查询结果，不需要回表。也就是说，在这个查询里面，索引k已经“覆盖了”我们的查询需求，我们称为**覆盖索引**。

>由于覆盖索引可以减少树的搜索次数，显著提升查询性能，所以使用覆盖索引是一个常用的性能优化手段。








# 4. 