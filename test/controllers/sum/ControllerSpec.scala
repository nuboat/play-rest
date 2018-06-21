package controllers.sum

import abstractspec.UnitSpec
import com.typesafe.scalalogging.LazyLogging
import play.api.test.FakeRequest
import play.api.test.Helpers.{POST, route, status, _}

/**
  * @author Peerapat A on Jun 14, 2018
  */
class ControllerSpec extends UnitSpec with LazyLogging {

  private lazy val R1 = (POST, "/sum")

  s"${R1._1} ${R1._2}" should {

    "return 200 with right json post" in {

      val res = route(app, FakeRequest(R1._1, R1._2)
        .withHeaders(
          ("Content-Type", "application/json"))
        .withBody(
          """
            |[1,2,3,4,6,7]
          """.stripMargin)
      ).get

      status(res) mustBe OK
      assert(contentAsString(res).toInt === 23)
    }

  }

}
