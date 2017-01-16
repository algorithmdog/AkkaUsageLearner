package org.algorithmdog.akkalearning

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import com.typesafe.config.ConfigFactory
import scala.concurrent.Await
import akka.util.Timeout
import scala.concurrent.duration._

/**
  * Created by lietal on 2016/12/25.
  */

class StudentActor (remoteTeacher:ActorRef)extends Actor{

  val remoteServerRef = remoteTeacher
  // 获取到远程Actor的一个引用，通过该引用可以向远程Actor发送消息

  def receive = {
      case res:String => {
        println (res)
      }
      case time:Long => {
        println("起床ing")
        remoteServerRef ! "历史上规模最大的众筹行动是什么？";
      }
    }
}

object RemoteStudentSimulatorApp extends App{
  val config = ConfigFactory
    .parseResources("lietal.conf")
    .getConfig("RemoteClientSideActor")
  //读入客户端配置

  val actorSystem = ActorSystem("StudentClient",  config);
  //使用配置，建立 ActorSystem

  implicit val resolveTimeout = Timeout(5 seconds)
  val teacherActor = Await.result(actorSystem.actorSelection("akka.tcp://TeacherService@127.0.0.1:4999/user/teacherActor").resolveOne(), resolveTimeout.duration)
  // 远程Actor的路径，通过该路径能够获取到远程Actor的一个引用
  val studentActor = actorSystem.actorOf(Props(new StudentActor(teacherActor)))
  //获得 StudentActor 的一个引用。
  //在程序中 Actor 不能直接被访问。
  //所有操作都必须通过 ActorRef 引用。

  while(true){
      studentActor ! 7.toLong    // 7 点起床。。
       Thread.sleep(5000) // 假装一天过去了
  }
}
