package org.algorithmdog.akkalearning

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

import scala.concurrent.Await
import akka.util.Timeout

import scala.concurrent.duration._

/**
  * Created by lietal on 2016/12/25.
  */

class StudentActor (remoteTeacher:ActorRef) extends Actor with ActorLogging{

  val remoteServerRef = remoteTeacher
  // 获取到远程Actor的一个引用，通过该引用可以向远程Actor发送消息

  var DayInSchool = 0;

  def receive = {
      case res:String => {
        println ("老师的答案是:"+res)
      }
      case time:Long => {

        val originalSender = sender;
        sender ! "关闭闹钟"

        DayInSchool += 1;
        log.info("DayInSchool is %d".format(DayInSchool))

        remoteServerRef ! "历史上规模最大的众筹行动是什么？";
      }
    }
}


