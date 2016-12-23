package person.lietal.akkalearning.chapter1

/**
  * Created by lietal on 2016/12/22.
  */


import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props

object SchoolActorSystem{
  val actorSystem = ActorSystem("SchoolActorSystem");
  def getInstance():ActorSystem = {
    actorSystem
  }
}


class Student{
  def keepAsking()={
    while(true){
      println("a");
    }
  }
}

class TeacherActor extends Actor {
  def receive = {
    case "1+1等于多少?"           => println("2")
    case "腾讯游戏的格言是什么？" => println("用鑫创造快乐")
    case "腾讯第一网红是谁？"     => println("哎呀我 watch")
    case _              => println("这个问题，老师也得查查书")
  }
}

object Main extends App {
  val system = ActorSystem("HelloSystem")
  // 缺省的Actor构造函数
  val helloActor = system.actorOf(Props[TeacherActor], name = "helloactor")
  val helloActor1 = system.actorOf(Props[TeacherActor], name="helloactor1")
  helloActor ! "hello"
  helloActor ! "喂"

  helloActor1 ! "我操，死了"


}