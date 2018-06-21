package utilities

import org.scalatest.FunSuite
import utilites.Retry

/**
  * Created by nuboat on 6/4/17.
  */
class RetryTest extends FunSuite {

  test("Retry 3 times") {

    val thrown = intercept[IllegalStateException] {
      Retry(3) {
        throw new Exception("For Failed")
      }
    }

    assert(thrown.getMessage === "Reached Limit Retry")
  }

}
