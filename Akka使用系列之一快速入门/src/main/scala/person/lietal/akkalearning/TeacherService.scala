/**
  * Created by lietal on 2016/12/22.
  */


import akka.actor.{Actor, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

class TeacherActor extends Actor {
  def receive = {
    case "1+1等于多少?"           => {
      val originalSender = sender;//
      sender ! "1+1等于2"
    }
    case "历史上规模最大的众筹行动是什么？" => {
      val originalSender = sender;//
      sender ! "历史上规模最大的众筹行动是 +1s"
    }
    case "腾讯第一网红是谁？"     => {
      val originalSender = sender;//
      sender ! "腾讯第一网红是\"我去\""
    }
    case _              => {
      val originalSender = sender;//
      sender ! "这个问题，老师也得查查书"
    }
  }
}

object TeacherServices extends App { //为什么感觉 TeacherService 这个名字有点邪恶。

  val system = ActorSystem("TeacherService",  ConfigFactory.parseResources("lietal.conf").getConfig("RemoteServerSideActor "))

  system.actorOf(Props[TeacherActor], "teacherActor") // 创建一个名称为remoteActor的Actor，返回一个ActorRef，这里我们不需要使用这个返回值

}