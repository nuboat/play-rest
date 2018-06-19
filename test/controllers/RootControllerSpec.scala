package controllers

import abstractspec.UnitSpec
import play.api.test.FakeRequest
import play.api.test.Helpers.{route, status, _}

/**
  * @author Peerapat A on April 26, 2018
  */
class RootControllerSpec extends UnitSpec {

  private lazy val R1 = (OPTIONS, "/")
  private lazy val R2 = (OPTIONS, "/version")
  private lazy val R3 = (GET, "/version")

  s"${R1._1} ${R1._2}" should {

    "success return 200" in {
      val testObject = route(app, FakeRequest(R1._1, R1._2)).get

      status(testObject) mustBe NO_CONTENT
    }

  }

  s"${R2._1} ${R2._2}" should {

    "success return 200" in {
      val testObject = route(app, FakeRequest(R2._1, R2._2)).get

      status(testObject) mustBe NO_CONTENT
    }

  }

  s"${R3._1} ${R3._2}" should {

    "success return 200" in {
      val testObject = route(app, FakeRequest(R3._1, R3._2)).get

      status(testObject) mustBe OK

      println(contentAsString(testObject))
    }

  }

}
