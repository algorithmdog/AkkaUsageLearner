package org.algorithmdog.akkalearning

/**
  * Created by lietal on 2016/12/22.
  */


import akka.actor.{Actor, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

class TeacherActor extends Actor {
  var coutAnswer = 0;
  def receive = {
    case "1+1等于多少?"           => {
      val originalSender = sender;//
      sender ! "1+1等于2"
      coutAnswer += 1;
    }
    case "历史上规模最大的众筹行动是什么？" => {
      val originalSender = sender;//
      sender ! "历史上规模最大的众筹行动是 +1s"
      coutAnswer += 1;
    }
    case "腾讯第一网红是谁？"     => {
      val originalSender = sender;//
      sender ! "腾讯第一网红是\"我去\""
      coutAnswer += 1;
    }
    case _              => {
      val originalSender = sender;//
      sender ! "这个问题，老师也得查查书"
      coutAnswer += 1;
    }
  }
}

object RemoteTeacherServicesApp extends App {

  val config = ConfigFactory
    .parseResources("lietal.conf")
    .getConfig("RemoteServerSideActor")
  //读入配置

  val system = ActorSystem("TeacherService",  config)
  //使用配置，建立 Actor 模型系统 ActorSystem。
  //ActorSystem 是访问 Actor 模型系统的接口，类似于 Spark 的 SparkContext。

  system.actorOf(Props[TeacherActor], "teacherActor")
  //创建TearcherActor，返回一个引用
  //这里，我们并不需要使用这个引用
}