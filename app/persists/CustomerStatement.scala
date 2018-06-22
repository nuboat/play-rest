package persists

import java.sql.Connection

import entites.Customer

case class CustomerStatement(sql: String)
                            (implicit con: Connection) {

  var count = 1

  private val p = con.prepareStatement(sql)

  def setString(s: String): CustomerStatement = {
    p.setString(count, s)
    count = count + 1
    this
  }

  def query[A]: List[Customer] = {
    val rs = p.executeQuery

    new Iterator[Customer] {
      override def hasNext: Boolean = rs.next

      override def next: Customer = Customer(
        rs.getLong("id")
        , rs.getString("name")
        , rs.getString("gender"))
    }.toList
  }

}

object CustomerStatement {

  implicit class StringAsCustomerStatement(sql: String)
                                   (implicit con: Connection) {
    def setString(s: String): CustomerStatement = {
      CustomerStatement(sql)
        .setString(s)
    }
  }

}