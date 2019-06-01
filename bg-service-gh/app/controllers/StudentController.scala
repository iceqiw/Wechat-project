package controllers

import javax.inject.Inject
import models._
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

/**
  * Manage a database of computers
  */
class StudentController @Inject()(studentService: StudentRepository,
                                  cc: MessagesControllerComponents)(implicit ec: ExecutionContext)
  extends MessagesAbstractController(cc) {

  private val logger = play.api.Logger(this.getClass)

  def index = Action {
    Ok("test")
  }

  def notification() = Action.async { implicit request =>
    Future.successful(Ok("test tongzhi xiexie "))
  }
}
