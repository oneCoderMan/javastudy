# 1.状态模式简介
状态模式（State Pattern）是一种行为型设计模式，它适用于一个对象在在不同的状态下有不同的行为

即状态模式适用于**有限状态机**的场景：
其主要思想是程序在任意时刻仅可处于几种有限的状态中。 
在任何一个特定状态中， 程序的行为都不相同， 且可瞬间从一个状态切换到另一个状态。 
不过， 根据当前状态， 程序可能会切换到另外一种状态， 也可能会保持当前状态不变。 
这些数量有限且预先定义的状态切换规则被称为**转移**。

# 2.状态模式类图

UML类图如下：

<div align="center">
	<img src="https://github.com/oneCoderMan/javastudy/blob/ba86ff0e3579c3def0b8290bd6e344c79fe0498d/notes/src/main/resources/desiginpattern/pics/state.png" alt="Editor" width="700">
</div>

# 3.与策略模式的区别
与策略模式相似， 但有一个关键性的不同——在状态模式中， 特定状态知道其他所有状态的存在， 且能触发从一个状态到另一个状态的转换； 
策略则几乎完全不知道其他策略的存在。


# REF
[深入理解设计模式-状态模式](https://refactoringguru.cn/design-patterns/state)