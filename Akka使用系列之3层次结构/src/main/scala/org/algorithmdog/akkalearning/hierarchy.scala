package org.algorithmdog.akkalearning

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

import scala.concurrent.Await
import akka.util.Timeout

import scala.concurrent.duration._

/**
  * Created by lietal on 2016/12/25.
  */


class Master extends Actor with ActorLogging{
    val english2chinese = context.actorOf(Props[English2Chinese],"English2Chinese")
    val english2cat     = context.actorOf(Props[English2Cat],"English2Cat")

    def receive = {
        case eng1:String =>{
            english2chinese ! eng1
            english2cat     ! eng1             
        }
    }
    
}

class English2Chinese extends Actor with ActorLogging{
    def receive = {
        case eng:String => {
            println("我翻译不出来!")
        }
    }
}

class English2Cat extends Actor with ActorLogging{
    def receive = {
        case eng:String =>{
             println( "喵喵喵!")
        }
    }
}


object Main{
    def main(args:Array[String]):Unit = {
        val sys = ActorSystem("system")
        val master = sys.actorOf(Props[Master],"Master")
        master ! "Hello,world!"
    }
}

