# Spring里面的BeanFactory创建
BeanFactory里面创建Bean的固定了一个流程，构造->依赖注入->初始化，但是一个依赖注入可以有
不同的实现，因此流程可以固定，实现可以进行扩展。[Bean构造的模版方法代码mini](../../../../../basicTech/src/main/java/com/java/study/frameworkstudy/spring/TemplateMethodTest.java)
