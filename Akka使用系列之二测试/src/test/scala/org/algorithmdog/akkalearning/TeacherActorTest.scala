package org.algorithmdog.akkalearning

import akka.actor.ActorSystem
import akka.testkit.{EventFilter, ImplicitSender, TestActorRef, TestKit, TestProbe}
import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner


/**
  * Created by lietal on 2017/1/3.
  */


/*
@RunWith(classOf[JUnitRunner])
class TeacherServiceTest1 extends FunSuite with BeforeAndAfter{
  test("countAnswer"){
    val teacherRef = ActorSystem("SummerSchool").actorOf(Props[TeacherActor])
    teacherRef ! "aa"
    assert(teacherRef.underlyingActor.coutAnswer == 2) //这行会出错，无法访问 actor 内部
  }
}
*/

@RunWith(classOf[JUnitRunner])
class TeacherActorTest extends TestKit(ActorSystem("SummerSchool"))
  with ImplicitSender
  with WordSpecLike
  with BeforeAndAfterAll{


   // 测试内部状态是否改变, testActorRef
  "The countAnswer " must {
    "increase the number" in {
      val teacherRef = TestActorRef[TeacherActor]
      teacherRef ! "aa"
      assert(teacherRef.underlyingActor.coutAnswer == 1)
      teacherRef ! "haha"
      assert(teacherRef.underlyingActor.coutAnswer == 2)
    }
  }

  // 测试是否正确 logging, eventFilter
  "The countAnswer" must {
    "logging when receive a question" in  {
      val testActorRef = TestActorRef[TeacherActor]
      EventFilter.info(pattern = ".*receives aa.*",occurrences = 1).intercept({
        testActorRef ! "aa";
      })
    }
  }

  //测试是否发出正确的 action, TestActor
  "The countAnswer " must {
    "response a correct answer" in {
      val teacherRef = TestActorRef[TeacherActor]
      teacherRef ! "aa"
      //expectMsg("这个问题，老师也得查查书")
      teacherRef ! "历史上规模最大的众筹行动是什么？"
      //expectMsg("历史上规模最大的众筹行动是 +1s")
    }
  }

  //测试是否发出正确的 action 序列
  "The countAnswer " must {
    "response a correct answer order" in {
      val teacherRef = TestActorRef[TeacherActor]
      val testProb   = new TestProbe(system);
      testProb.send(teacherRef, "aa");
      testProb.send(teacherRef,"历史上规模最大的众筹行动是什么？")

      testProb.expectMsg("这个问题，老师也得查查书")
      testProb.expectMsg("历史上规模最大的众筹行动是 +1s")
    }
  }


}
