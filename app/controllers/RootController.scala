package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import play.libs.Json

/**
  * @author Peerapat A on Jun 19, 2018
  */
@Singleton
class RootController @Inject()(cc: ControllerComponents)
  extends AbstractController(cc) {

  def rootOptions: Action[AnyContent] = options("/")

  def options(url: String) = Action {
    NoContent
  }

  def version() = Action {
    Ok(Json.mapper.writeValueAsString(ApiVersion("1.0.0")))
  }

  case class ApiVersion(version: String)

}
