# 1. 安装
[官网地址quickStart](https://kafka.apache.org/quickstart)
选择最新的版本：3.6.0
下载地址
```shell
wget https://dlcdn.apache.org/kafka/3.6.0/kafka_2.13-3.6.0.tgz
```

* Step1: Generate a Cluster UUID
```shell
KAFKA_CLUSTER_ID="$(bin/kafka-storage.sh random-uuid)"
```
记录下来生成的uuid`qyiEVuIrS-G9nZMk3mI36g`


* Step2: Format Log Directories
```shell
bin/kafka-storage.sh format -t $KAFKA_CLUSTER_ID -c config/kraft/server.properties
```
会打印出如下日志：
```shell
Formatting /tmp/kraft-combined-logs with metadata.version 3.6-IV2.
```

* Step3: Start the Kafka Server
```shell
nohup bin/kafka-server-start.sh config/kraft/server.properties &
```


# 2. Topic操作
创建Topic
```shell
bin/kafka-topics.sh --create --topic yijun-test --bootstrap-server localhost:9092
```

查看Topic的信息
```shell
bin/kafka-topics.sh --describe --topic yijun-test --bootstrap-server localhost:9092
```

# REF