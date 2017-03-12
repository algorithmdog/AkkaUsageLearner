# AkkaUsageLearner
The project contains example code for learning to use akka. We use maven to organize this project, and use 2.10 Scala and 1.7.0_79 jdk.


### Akka 使用系列之一: 快速入门

Akka 是广泛应用的编写的 Actor 模型库，使得开发人员可以轻松开发具有容错性、可扩展性和跨平台的并发程序，在工业界得到了广泛应用。Spark 就用 Akka 实现内部通信。这篇博客从一个例子出发，入门简单的 Akka 应用。

[博客地址](http://www.algorithmdog.com/akka-usage-getstarted)

### Akka 使用系列之二: 测试

 Akka-testkit 是 Akka 官方推出的 Akka 测试包，目的是减轻测试难度。Akka-testkit 的主要工具包括, 1) testProbe 用于测试 Actor 回应和发送消息，testActor 用于简便情况下测试 Actor 回应消息，和 2) testActorRef 用于测试 Actor 内部状态的改变。
 
[博客地址]( http://www.algorithmdog.com/akka-test)

### Akka 使用系列之三: 层次结构

 我们使用 Akka 开发并行程序时，可以使用层级结构组织 Actors。层次结构不仅比较符合人类直觉，还为容错提供了机制保障。
 
[博客地址]( http://www.algorithmdog.com/akka-hierarchy-fault)
