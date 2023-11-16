# 1. 管理相关
## 查看连接数
```mysql
show processlist
```

## 查看binlog相关

查看是否支持binlog及其存储路径
```mysql
show variables like '%log_bin%'
```
  
# 2. 事务相关
## 查看隔离级别
```mysql
show variables like 'transaction_isolation';
```

## 查看长事务连接
```mysql
select * from information_schema.innodb_trx where TIME_TO_SEC(timediff(now(),trx_started))>60
```

# 3. 建库表

## 建库
```mysql
CREATE DATABASE user_info;
```

## 建表
```mysql
CREATE TABLE IF NOT EXISTS `user_tbl`(
       `id` INT UNSIGNED AUTO_INCREMENT,
       `name` VARCHAR(100) NOT NULL,
       `phone` VARCHAR(40) NOT NULL,
       `submission_date` DATE,
       PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

## 插入数据
```mysql
INSERT INTO `user_tbl` (name, phone, submission_date) VALUES ("yijun", "14534567896", NOW());
```

# 4. 创建索引
## 字符串索引
```mysql
alter table `SUser` add index index1(email);
# 选择前6个字符去索引
alter table `SUser` add index index2(email(6));
```





