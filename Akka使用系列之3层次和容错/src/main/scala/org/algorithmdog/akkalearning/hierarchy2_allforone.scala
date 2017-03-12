package org.algorithmdog.akkalearning

import akka.actor.Actor.Receive
import akka.actor.SupervisorStrategy.{Restart, Resume, Stop}
import akka.actor.{Actor, ActorLogging, ActorSystem, AllForOneStrategy, OneForOneStrategy, Props}

/**
  * Created by lietal on 2017/2/27.
  */

class Master2 extends Actor with ActorLogging{
    val english2Chinese = context.actorOf(Props[English2Chinese2],"English2Chinese")
    val english2Cat     = context.actorOf(Props[English2Cat2], "English2Cat")

    override val supervisorStrategy= AllForOneStrategy() {
      case _: Exception                   => Stop
    }

    override def receive = {
      case eng:String => {
        english2Cat ! eng;
        english2Chinese ! eng;
      }
    }


}
class English2Chinese2 extends Actor with ActorLogging{
  override def receive = {
    case eng:String => {
      println("翻译不出来")
    }
  }
}
class English2Cat2 extends Actor with ActorLogging{
  override def receive = {
    case eng:String => {
      throw new Exception("Exception in English2Cat1")
    }
  }
}

object hierarchy2 {
  def main(args:Array[String]):Unit = {
    val system = ActorSystem("system")
    val master = system.actorOf(Props[Master2],"Master")
    println(master.toString())

    master ! "Hello, world"
    Thread.sleep(1000)
    master ! "Hello, world"
  }
}
