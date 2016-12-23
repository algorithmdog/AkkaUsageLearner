package person.lietal.akkalearning.chapter1

/**
  * Created by lietal on 2016/12/22.
  */
import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props

class HelloActor extends Actor {
  def receive = {
    case "hello" => println("您好！")
    case _       => println("您是?")
  }
}

object Main extends App {
  val system = ActorSystem("HelloSystem")
  // 缺省的Actor构造函数
  val helloActor = system.actorOf(Props[HelloActor], name = "helloactor")
  val helloActor1 = system.actorOf(Props[HelloActor], name="helloactor1")
  helloActor ! "hello"
  helloActor ! "喂"

  helloActor1 ! "我操，死了"
}