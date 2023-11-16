
# 索引失效

## 数据准备
```mysql
CREATE TABLE `t` (
     `id` int(11) NOT NULL,
     `a` int(11) DEFAULT NULL,
     `b` int(11) DEFAULT NULL,
     PRIMARY KEY (`id`),
     KEY `a` (`a`),
     KEY `b` (`b`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
```
往表t中插入10万行记录，取值按整数递增，即：(1,1,1)，(2,2,2)，(3,3,3) 直到(100000,100000,100000)

用存储过程来插入数据的
```mysql
delimiter ;;
create procedure idata()
begin
  declare i int;
  set i=1;
  while(i<=100000)do
    insert into t values(i, i, i);
    set i=i+1;
  end while;
end;;
delimiter ;
call idata();
```

## 索引失效
一个查询语句：
`select * from t where a between 10000 and 20000;`

1. 第一种情况，数据表T没有数据的删除
2. 第二种情况，数据表T有事务在执行删除操作，在插入数据

结论：第二种情况不会使用索引a

原因分析：
选择索引是优化器的工作，优化器选择索引的目的，是找到一个最优的执行方案，并用最小的代价去执行语句。在数据库里面，扫描行数是影响执行代价的因素之一。
扫描的行数越少，意味着访问磁盘数据的次数越少，消耗的CPU资源越少。

扫描行数并不是唯一的判断标准，优化器还会结合是否使用临时表、是否排序等因素进行综合判断。

上面的情况是MySQL选错索引在判断扫描行数的时候出问题了


## REF
[MySQL为什么有时候会选错索引](https://funnylog.gitee.io/mysql45/10%E8%AE%B2MySQL%E4%B8%BA%E4%BB%80%E4%B9%88%E6%9C%89%E6%97%B6%E5%80%99%E4%BC%9A%E9%80%89%E9%94%99%E7%B4%A2%E5%BC%95.html)
