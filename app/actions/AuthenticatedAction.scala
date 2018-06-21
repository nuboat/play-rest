package actions


import java.util.concurrent.TimeUnit

import com.google.common.base.Stopwatch
import com.typesafe.scalalogging.LazyLogging
import javax.inject.Inject
import play.api.http.HttpVerbs
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

/**
  * @author Peerapat A on April 13, 2018
  */
class AuthenticatedAction @Inject()(val parser: BodyParsers.Default)
                                   (implicit val executionContext: ExecutionContext)
  extends ActionBuilder[AccountRequest, AnyContent]
    with HttpVerbs
    with LazyLogging {

  override def invokeBlock[A](request: Request[A],
                              block: AccountRequest[A] => Future[Result]): Future[Result] = {
    processTime(request) {
      try {
        val account = None

        val f = block(AccountRequest(request, account))
        f onComplete {
          case Success(_) => logger.info(s"success")
          case Failure(_) =>
        }

        f
      } catch {
        case e: IllegalAccessException =>
          throw e
      }
    }
  }

  protected def processTime[R, A](request: Request[A])(block: => R): R = {
    val stopwatch = Stopwatch.createStarted()
    val userAgent = request.headers.get("User-Agent").getOrElse("N/A")
    try {
      block
    } finally {
      logger.info(s"${request.method} ${request.uri} in ${stopwatch.stop.elapsed(TimeUnit.MILLISECONDS)} ms. [$userAgent]")
    }
  }

}
