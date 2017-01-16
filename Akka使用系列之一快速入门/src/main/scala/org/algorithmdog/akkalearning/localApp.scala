package org.algorithmdog.akkalearning

/**
  * Created by lietal on 2017/1/16.
  */
object localApp {

  def main(args:Array[String]) = {
    val actorSystem = ActorSystem("SummerSchool")
    val teacher = actorSystem.actorOf(Props[TeacherActor], "teacher")
    val student = actorSystem.actorOf(Props(new StudentActor(teacher)), "student")

    student ! 7.toLong; //早上 7 点哦
    Thread.sleep(1000);

    actorSystem.shutdown()

}
