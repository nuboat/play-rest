package actions

/**
  * @author Peerapat A on Jul 17, 2017
  */
case class Account(id: Long
                   , clientId: Long
                   , status: Account.Status
                   , email: String
                   , username: String
                   , firstname: String
                   , lastname: String
                   , roles: Set[String] = Set.empty)

object Account extends Enumeration {
  type Status = Value

  val Active: Value = Value(0)

  val InActive: Value = Value(1)

}
