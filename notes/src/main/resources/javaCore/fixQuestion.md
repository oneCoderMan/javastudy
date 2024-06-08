# Java问题排查及调优

## 一、一些参数介绍
Xms: 初始堆大小

Xmx: 最大堆大小
```shell
-Xms200M -Xmx200M
```
> 生产环境下这两个一般需要设置的一样，防止内存抖动，避免程序运行过程中弹性内存

## 二、一些命令
`jps` : 查看Java进程

`jinfo [pid]`: 查看Java进程详细信息

`jstack [pid] | more` : 查看Java进程堆栈信息

## 三、排查神奇arthas
https://arthas.aliyun.com/en/

`dashboard` : 查看当前Java程序的状况

