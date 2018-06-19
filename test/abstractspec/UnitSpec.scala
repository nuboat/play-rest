package abstractspec

import com.typesafe.scalalogging.LazyLogging
import org.junit.runner.RunWith
import org.scalatest.BeforeAndAfterAll
import org.scalatest.junit.JUnitRunner
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.Application
import play.api.db.Database
import play.api.inject.guice.GuiceApplicationBuilder

/**
  * @author Peerapat A on April 26, 2018
  */
@RunWith(classOf[JUnitRunner])
abstract class UnitSpec extends PlaySpec
  with GuiceOneAppPerSuite
  with BeforeAndAfterAll
  with LazyLogging {

  protected def initialSQL: List[String] = List.empty

  protected def preExecute(): Unit = {}

  implicit override lazy val app: Application = new GuiceApplicationBuilder()
    .configure("play.db.pool" -> "bonecp")
    .configure("yoda.json.is_snake_case" -> true)
    .configure("yoda.naming-convention" -> 2)
    .configure("db.default.url" -> "jdbc:h2:mem:play;MODE=PostgreSQL")
    .configure("db.default.username" -> "beid")
    .configure("db.default.password" -> "")
    .build

  protected lazy val db: Database = app.injector.instanceOf[Database]

  override def beforeAll: Unit = db.withConnection { implicit conn =>
    println("Yo !!!!!!!!!")
    initialSQL.foreach(sql => conn
      .prepareStatement(sql.replace("WITH TIME ZONE", ""))
      .executeUpdate
    )

    preExecute()
  }

}
