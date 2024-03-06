# 1. 工厂模式简介
简单工厂模式是一种**创建型设计模式**

# 2. 简单工厂模式
在了解工厂方法模式之前，需要先了解“简单工厂”模式进，但并不属于23种设计模式之一，更多的是一种编程习惯。

简单工厂模式的核心思想是将产品的创建过程封装在一个工厂类中，把创建对象的流程集中在这个工厂类里面。

类图如下：

<div align="center">
	<img src="https://github.com/oneCoderMan/javastudy/blob/e3f2a65a318cbd6c960713988f8cb70f79256d99/notes/src/main/resources/desiginpattern/pics/simpleFactory.png" alt="Editor" width="800">
</div>

简单工厂类简化了客户端操作，客户端可以调用工厂方法来获取具体产品，而无需直接与具体产品类交互，降低了耦合，但是有一个很大的问题就是不够灵活，
如果需要添加新的产品，就需要修改工厂类的代码。


## 3. 工厂方法模式
工厂方法(Factory Method) 模式引入了抽象工厂和具体工厂的概念，每个具体工厂只负责创建一个具体产品，
添加新的产品只需要添加新的工厂类而无需修改原来的代码，这样就使得产品的生产更加灵活，支持扩展，符合开闭原则。

类图如下：
<div align="center">
	<img src="https://github.com/oneCoderMan/javastudy/blob/7bf457f36db706bbaf143afe227607a0d6e96051/notes/src/main/resources/desiginpattern/pics/factoryMethod.png" alt="Editor" width="800">
</div>

## 4. 抽象工厂模式
在工厂方法模式中，每个具体工厂只负责创建单一的产品。
但是如果有多类产品呢，抽象工厂模式可以确保一系列相关的产品被一起创建，这些产品能够相互配合使用

类图如下：
<div align="center">
	<img src="https://github.com/oneCoderMan/javastudy/blob/7eb9bbed6dfa940da1c34f9b2b36ef5ecf4ecb26/notes/src/main/resources/desiginpattern/pics/abstractF.png" alt="Editor" width="800">
</div>

你正在开发一款家具商店模拟器。 你的代码中包括一些类， 用于表示：
1. 一系列相关产品， 例如 椅子`Chair` 、 沙发`Sofa`和咖啡桌`Coffee Table`
2. 系列产品的不同变体。 例如， 你可以使用 `现代Modern` 、`维多利亚Victorian` 、`装饰风艺术Art Deco`等风格生成 椅子 、沙发和 咖啡桌 。

你需要设法单独生成每件家具对象， 这样才能确保其风格一致。



# REF
[工厂模式](https://github.com/youngyangyang04/kama-DesignPattern/blob/main/DesignPattern/2-%E5%B7%A5%E5%8E%82%E6%96%B9%E6%B3%95%E6%A8%A1%E5%BC%8F.md)

[深入理解设计模式-工厂方法](https://refactoring.guru/design-patterns/factory-method)

[深入理解设计模式-抽象工厂方法](https://refactoringguru.cn/design-patterns/abstract-factory)
