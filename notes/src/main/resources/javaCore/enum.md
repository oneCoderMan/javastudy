# 枚举定义
一般Java定义的枚举类如下：[枚举定义](../../../../../basicTech/src/main/java/com/java/study/basic/enumtest/TypeEnum.java)

# 序列化枚举
在对应的getXX方法上加上`@JsonValue`，控制序列化哪一个字段