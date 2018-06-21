package controllers.registration

import javax.inject.{Inject, Singleton}
import play.api.db.Database
import play.api.mvc.{AbstractController, Action, ControllerComponents, Request}
import services.Json


/**
  * @author Peerapat A on Jun 19, 2018
  */
@Singleton
class Controller @Inject()(cc: ControllerComponents
                          , json: Json
                          , db: Database)
  extends AbstractController(cc) {

  def post: Action[String] = Action(parse.tolerantText) { implicit request =>
    request.toForm[Form].map (form => {
      Ok
    }).getOrElse {
      BadRequest
    }

  }

  implicit class HTTPRequest(request: Request[String]) {

    def toForm[T: Manifest]: Option[T] = json.toClass[T](request.body)
  }

}
