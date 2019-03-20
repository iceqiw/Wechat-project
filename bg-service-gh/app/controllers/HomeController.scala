package controllers

import javax.inject.Inject
import models._
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

/**
  * Manage a database of computers
  */
class HomeController @Inject()(courseService: CourseRepository,
                               cc: MessagesControllerComponents)(implicit ec: ExecutionContext)
  extends MessagesAbstractController(cc) {

  private val logger = play.api.Logger(this.getClass)

  def index = Action {
    Ok("test")
  }

  def notification() = Action.async { implicit request =>
    Future.successful(Ok("test tongzhi xiexie "))
  }

  def list() = Action.async { implicit request =>
    logger.info("list")
    courseService.findLastOne().map { course =>
      Ok(Json.toJson(course))
    }
  }
}
