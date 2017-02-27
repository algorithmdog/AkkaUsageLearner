package org.algorithmdog.akkalearning

/**
  * Created by lietal on 2016/12/22.
  */


import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

class TeacherActor extends Actor with ActorLogging {
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
      log.info("it receives aa")
      val originalSender = sender;//
      sender ! "这个问题，老师也得查查书"
      coutAnswer += 1;
    }
  }
}

