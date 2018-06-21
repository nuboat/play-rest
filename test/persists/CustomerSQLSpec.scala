package persists

import abstractspec.UnitSpec
import entites.Customer

class CustomerSQLSpec extends UnitSpec {

  "Like name" should {
    "return List[Customer]" in {
      val customers: List[Customer] =
        """
          |SELECT * FROM CUSTOMERS
          |WHERE  name like ?
        """.stripMargin
          .setString("Miche%")
          .query[Customer]


      assert(customers.size === 3)
    }
  }


  override val initialSQL = List(
    """
      |CREATE TABLE customers (
      |  id BIGINT NOT NULL,
      |  name VARCHAR(64),
      |  gender CHAR(1),
      |  CONSTRAINT customers_pk PRIMARY KEY (id)
      |);
    """.stripMargin)

  override def preExecute(): Unit = db.withConnection { implicit con =>
    con.prepareStatement("INSERT INTO customers VALUES (1, 'Ada Loveless', 'F')").executeUpdate()
    con.prepareStatement("INSERT INTO customers VALUES (2, 'Micheal', 'M')").executeUpdate()
  }

}
