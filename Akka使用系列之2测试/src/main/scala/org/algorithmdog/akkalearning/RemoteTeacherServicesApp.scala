package org.algorithmdog.akkalearning

import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory

/**
  * Created by gotoli on 2017/1/17.
  */
object RemoteTeacherServicesApp extends App {

  val config = ConfigFactory
    .parseResources("example.conf")
    .getConfig("RemoteServerSideActor")
  //读入配置

  val system = ActorSystem("TeacherService",  config)
  //使用配置，建立 Actor 模型系统 ActorSystem。
  //ActorSystem 是访问 Actor 模型系统的接口，类似于 Spark 的 SparkContext。

  system.actorOf(Props[TeacherActor], "teacherActor")
  //创建TearcherActor，返回一个引用
  //这里，我们并不需要使用这个引用
}
