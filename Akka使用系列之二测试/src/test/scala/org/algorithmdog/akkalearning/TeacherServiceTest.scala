package org.algorithmdog.akkalearning

import akka.actor.{ActorSystem, Props}
import akka.testkit.{TestActorRef, TestKit}
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

class TeacherServiceTest extends TestKit(ActorSystem("SummerSchool"))
  with WordSpecLike
  with BeforeAndAfter{

  "The countAnswer " must {
    "increase after response a answer" in {

      val teacherRef = TestActorRef[TeacherActor]
      teacherRef ! "aa"
      //assert(teacherRef.underlyingActor.coutAnswer == 1)
      expectMsg("hello aa")
    }
  }

}
