package adhoc

import org.scalatest.FunSuite

class AdhocTest extends FunSuite {

  test("1") {
    val s = Seq(Customer(1), Customer(2))
    val m: Seq[Person] = s

    val q = scala.collection.immutable.Queue(1,2)
    q.enqueue(3)
  }

  trait Person {}

  case class Customer(id: Long) extends Person

}
