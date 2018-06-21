package utilities

import java.io.{File, PrintWriter}

import org.scalatest.FunSuite
import utilites.Closer

/**
  * Created by Peerapat A on Mar 18, 2017
  */
class CloserSpec extends FunSuite with Closer {

  test("close without exception") {
    closer(new PrintWriter("tmp.txt")) { pw =>
      pw.flush()
    }

    new File("tmp.txt").delete()
  }

}
