package actions

import play.api.mvc.{Request, WrappedRequest}


/**
  * @author Peerapat A on April 13, 2018
  */
case class AccountRequest[A](request: Request[A]
                             , optAccount: Option[Account])
  extends WrappedRequest(request) {

  val account: Account = optAccount.orNull

}
