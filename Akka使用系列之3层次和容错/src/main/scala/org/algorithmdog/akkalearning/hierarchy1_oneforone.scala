package org.algorithmdog.akkalearning

import akka.actor.Actor.Receive
import akka.actor.SupervisorStrategy.{Restart, Resume, Stop}
import akka.actor.{Actor, ActorLogging, ActorSystem, AllForOneStrategy, OneForOneStrategy, Props}

/**
  * Created by lietal on 2017/2/27.
  */


class Master1 extends Actor with ActorLogging{
    val english2Chinese = context.actorOf(Props[English2Chinese1],"English2Chinese")
    val english2Cat     = context.actorOf(Props[English2Cat1], "English2Cat")
    override val supervisorStrategy = OneForOneStrategy(){
      case _:Exception                    => Stop
    }
    override def receive = {
      case eng:String => {
        english2Cat ! eng;
        english2Chinese ! eng;
      }
    }
}
class English2Chinese1 extends Actor with ActorLogging{
  override def receive = {
    case eng:String => {
      println("翻译不出来")
    }
  }
}
class English2Cat1 extends Actor with ActorLogging{
  override def receive = {
    case eng:String => {
      throw new Exception("Exception in English2Cat1")
    }
  }
}

object hierarchy1 {
  def main(args:Array[String]):Unit = {
    val system = ActorSystem("system")
    val master = system.actorOf(Props[Master1],"Master")
    master ! "Hello, world"
    Thread.sleep(1000)
    master ! "Hello, world"
  }
}
