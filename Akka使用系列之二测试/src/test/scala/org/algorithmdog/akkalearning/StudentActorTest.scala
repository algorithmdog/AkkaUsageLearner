package org.algorithmdog.akkalearning

import akka.actor.{ActorSystem, Props}
import akka.testkit.{TestKit, TestProbe}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{BeforeAndAfterAll, WordSpecLike}

/**
  * Created by lietal on 2017/1/3.
  */

@RunWith(classOf[JUnitRunner])
class StudentActorTest extends TestKit(ActorSystem("SummerSchool"))
  with WordSpecLike
  with BeforeAndAfterAll{

  "StudentActor " must{
      val questionReceiver = TestProbe()
      val studentActorRef = system.actorOf(Props(new StudentActor(questionReceiver.ref)))
    "send a question after waking up" in {

        studentActorRef ! 7.toLong
        studentActorRef ! 7.toLong
        questionReceiver.expectMsg("历史上规模最大的众筹行动是什么？")
        questionReceiver.expectMsg("历史上规模最大的众筹行动是什么？")

      }
    }

}
