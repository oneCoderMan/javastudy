# 管理相关
## 查看连接数
```mysql
show processlist
```

## 查看binlog相关

查看是否支持binlog及其存储路径
```mysql
show variables like '%log_bin%'
```
  

# 建库表

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




