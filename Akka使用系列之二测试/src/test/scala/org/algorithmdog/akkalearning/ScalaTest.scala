package org.algorithmdog.akkalearning

import org.junit.runner.RunWith
import org.scalatest.{BeforeAndAfter, FunSuite}
import org.scalatest.junit.JUnitRunner

/**
  * Created by lietal on 2017/1/16.
  */
@RunWith(classOf[JUnitRunner])
class ScalaTest extends FunSuite with BeforeAndAfter{

  test("test") {
    assert(1==1)
  }

}
