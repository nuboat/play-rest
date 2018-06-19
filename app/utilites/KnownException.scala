package utilites

/**
  * @author Peerapat A on Jun 19, 2018
  */
case class KnownException(message: String
                          , cause: Throwable = new IllegalStateException("N/A"))
  extends Exception(message, cause)
