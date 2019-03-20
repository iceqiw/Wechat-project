package controllers

import javax.inject.Inject
import models._
import play.api.libs.json.{JsError, Json}
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

/**
  * Manage a database of computers
  */
class UserController @Inject()(userService: UserRepository,
                               cc: ControllerComponents)(implicit ec: ExecutionContext)
  extends AbstractController(cc) {

  private val logger = play.api.Logger(this.getClass)

  def doRegister() = Action.async { implicit request =>
    logger.debug("doRegister".concat(request.body.asJson.get.toString))
    request.body.asJson.map(json =>
      json.validate[User].fold(
        valid = { u =>
          logger.debug("doRegister user ".concat(request.body.asJson.get.toString))
          userService.insert(u).map { userId =>
            Ok(Json.toJson(userId))
          }
        },
        invalid = { errors =>
          logger.error("doRegister error".concat(JsError.toJson(errors).toString()))
          Future.successful(BadRequest(JsError.toJson(errors)))
        }
      )
    ).getOrElse(Future.successful(BadRequest("wrong user")))
  }

  def doLogin() = Action.async { implicit request =>
    logger.debug("doLogin".concat(request.body.asJson.get.toString))
    request.body.asJson.map(json =>
      json.validate[User].fold(
        valid = { p =>
          logger.debug("doLogin user ".concat(request.body.asJson.get.toString))
          userService.findByTelephone(p.telephone).map { user =>
            user.map(us =>
              if (us.password.equals(p.password)) {
                Ok(Json.toJson(us.nickName.hashCode()))
              } else {
                BadRequest("wrong user")
              }
            ).getOrElse(
              BadRequest("wrong user")
            )
          }
        },
        invalid = { errors =>
          logger.error("doLogin error".concat(JsError.toJson(errors).toString()))
          Future.successful(BadRequest(JsError.toJson(errors)))
        }
      )
    ).getOrElse(Future.successful(BadRequest("wrong user")))
  }
}
