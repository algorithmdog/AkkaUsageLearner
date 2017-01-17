package org.algorithmdog.akkalearning

import akka.actor.{ActorSystem, Props}
import akka.testkit.{EventFilter, ImplicitSender, TestActorRef, TestKit, TestProbe}
import com.typesafe.config.ConfigFactory
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{BeforeAndAfterAll, WordSpecLike}

/**
  * Created by lietal on 2017/1/3.
  */

/**
  * 看了博客的同学主要看这个 StudentActorTest。另外一个测试类(TeacherActorTest)只是写着玩。
  */

/*
@RunWith(classOf[JUnitRunner])
class StudentTest1 extends FunSuite with BeforeAndAfter{
  test("a_test_case"){
    val teacherActor = system.actorOf(Props[TeacherActor])
    val teacherRef = ActorSystem("SummerSchool").actorOf(Props(new StudentActor(teacherActor))
    teacherRef ! 7.toLong
    assert(teacherRef.DayInSchool == 1);//这行会出错，无法访问 actor 内部
  }
}
*/

@RunWith(classOf[JUnitRunner])
class StudentActorTest
  extends TestKit(ActorSystem("SummerSchool",
    ConfigFactory.parseString("""akka.loggers = ["akka.testkit.TestEventListener"]""")))
  with ImplicitSender
  with WordSpecLike
  with BeforeAndAfterAll{

  val teacherActor = system.actorOf(Props[TeacherActor])

  //test its responses
  "The countAnswer " must {
    "response a correct answer order" in {
      val studentActor = system.actorOf(Props(new StudentActor(teacherActor )))
      val testProb   = new TestProbe(system);
      testProb.send(studentActor, 7.toLong);
      testProb.send(studentActor, 7.toLong)

      testProb.expectMsg("关闭闹钟")
      testProb.expectMsg("关闭闹钟")
    }
  }

  //test its response with simple
  "StudentActor" must {
    "response correctly" in {
      val studentActor = system.actorOf(Props(new StudentActor(teacherActor )))
      studentActor ! 7.toLong;
      expectMsg("关闭闹钟")
    }
  }

  //test internal state
  "StudentActor" must {
    "increase the DayInSchool" in {
      val testActorRef = TestActorRef(new StudentActor(teacherActor))
      testActorRef ! 7.toLong;
      assert(testActorRef.underlyingActor.DayInSchool == 1);
    }
  }
  //test logging
  "StudentActor" must {
    "logging" in {
      val testActorRef = system.actorOf(Props(new StudentActor(teacherActor)))
      EventFilter.info(pattern = ".*", occurrences = 1).intercept({
        testActorRef ! 7.toLong;
      })
    }
  }

  //test it sending a message
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
