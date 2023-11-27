# 1. 模版方法模式介绍
模板方法模式是一种行为设计模式， 它在超类中定义了一个算法的框架， 
允许子类在不修改结构的情况下重写算法的特定步骤。


# 2. Spring里面的BeanFactory创建
BeanFactory里面创建Bean的固定了一个流程，构造->依赖注入->初始化，但是一个依赖注入可以有
不同的实现，因此流程可以固定，实现可以进行扩展。[Bean构造的模版方法代码mini](../../../../../basicTech/src/main/java/com/java/study/frameworkstudy/spring/TemplateMethodTest.java)

# 3. 数据解析例子
**需求**：你正在开发一款分析公司文档的数据挖掘程序。 用户需要向程序输入各种格式 （PDF、 DOC 或 CSV） 的文档， 程序则会试图从这些文件中抽取有意义的数据， 
并以统一的格式将其返回给用户。




# REF
[深入设计模式-模版方法](https://refactoringguru.cn/design-patterns/template-method)

