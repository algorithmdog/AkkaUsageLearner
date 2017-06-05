package org.algorithmdog.akkalearning

import akka.actor._
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.language.postfixOps
import scala.concurrent.duration._

/**
  * Created by lietal on 2017/2/27.
  */

class MathTeacherActor extends Actor with ActorLogging {
  var coutAnswer = 0;
  def receive = {
    case "1+1等于多少?"           => {
      val originalSender = sender;//
      sender ! "1+1等于2"
      coutAnswer += 1;
    }
  }
}

class HistoryTeacherActor extends Actor with ActorLogging {
  def receive = {
    case "历史上规模最大的众筹行动是什么？" => {
      Thread.sleep(1)
      val originalSender = sender;//
      sender ! "历史上规模最大的众筹行动是 +1s"
    }
  }
}

class StudentActor(mathteacher:ActorRef,historyteacher:ActorRef) extends Actor with ActorLogging{

  def receive = {
    case res:String => {
        implicit val timeout = Timeout(5)
        val future1 = historyteacher ? "历史上规模最大的众筹行动是什么？"
        val future2 = mathteacher ? "1+1等于多少?"

        val res1    = Await.result(future1,10 second)
        val res2    = Await.result(future2,10 second)

        println(res1)
        println(res2)

    }
  }
}


object Main{
  def main(args:Array[String]):Unit = {
      val system = ActorSystem("AskTestSystem")
      val history_teacher = system.actorOf(Props[HistoryTeacherActor],"history_teacher")
      val math_teacher    = system.actorOf(Props[MathTeacherActor],"math_teacher")
      val studentActor    = system.actorOf(Props(new StudentActor(math_teacher,history_teacher)))

      studentActor ! "xx"

  }
}