# 1. 模版方法模式介绍
模板方法模式是一种行为设计模式， 它在超类中定义了一个算法的框架， 
允许子类在不修改结构的情况下重写算法的特定步骤。

模版模式的UML图如下：
<div align="center">
	<img src="https://github.com/oneCoderMan/javastudy/blob/d52d4156db4fd06560dba2e8845e53e1dfd058ae/notes/src/main/resources/desiginpattern/pics/templateOverview.png" alt="Editor" width="500">
</div>



# 2. Spring里面的BeanFactory创建
BeanFactory里面创建Bean的固定了一个流程，构造->依赖注入->初始化，但是一个依赖注入可以有
不同的实现，因此流程可以固定，实现可以进行扩展。[Bean构造的模版方法代码mini](../../../../../basicTech/src/main/java/com/java/study/frameworkstudy/spring/TemplateMethodTest.java)

# 3. 数据解析例子
**需求**：你正在开发一款分析公司文档的数据挖掘程序。 用户需要向程序输入各种格式 （PDF、 DOC 或 CSV） 的文档， 程序则会试图从这些文件中抽取有意义的数据， 
并以统一的格式将其返回给用户。

## 普通写法
普通写法的实例如下：
<div align="center">
	<img src="https://github.com/oneCoderMan/javastudy/blob/d52d4156db4fd06560dba2e8845e53e1dfd058ae/notes/src/main/resources/desiginpattern/pics/tem1.png" alt="Editor" width="500">
</div>

个类中包含许多相似代码。 尽管这些类处理不同数据格式的代码完全不同， 但数据处理和分析的代码却几乎完全一样。

可以思考如何去除重复代码


## 模版模式方法
模板方法模式建议将**算法**分解为一系列步骤， 然后将这些步骤改写为方法， 
最后在 “模板方法” 中依次调用这些方法。 步骤可以是**抽象的**， 也可以有一些默认的实现。 
为了能够使用算法， 客户端需要自行提供子类并实现所有的抽象步骤。 
如有必要还需重写一些步骤 （但这一步中不包括模板方法自身）。

<div align="center">
	<img src="https://github.com/oneCoderMan/javastudy/blob/d52d4156db4fd06560dba2e8845e53e1dfd058ae/notes/src/main/resources/desiginpattern/pics/template2.png" alt="Editor" width="500">
</div>

我们可为上面的三个解析算法创建一个基类， 该类将定义调用了一系列不同文档处理步骤的模板方法。

现在， 让我们看看如何去除重复代码。 
对于不同的数据格式， 打开和关闭文件以及抽取和解析数据的代码都不同， 因此这些代码在子类实现。 
但分析原始数据和生成报告等其他步骤的实现方式非常相似， 因此可将其提取到基类中， 以让子类共享这些代码。


# 4. 使用场景
* 只希望客户端扩展某个特定算法步骤， 而不是整个算法或其结构时， 可使用模板方法模式。
* 当多个类的算法除一些细微不同之外几乎完全一样时， 你可使用该模式


# REF
[深入设计模式-模版方法](https://refactoringguru.cn/design-patterns/template-method)

