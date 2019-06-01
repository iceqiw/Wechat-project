package controllers

import javax.inject.Inject
import models._
import play.api.libs.json.{JsError, Json}
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

class StudentController @Inject()(studentService: StudentRepository,
                                  cc: MessagesControllerComponents)(implicit ec: ExecutionContext)
  extends MessagesAbstractController(cc) {

  private val logger = play.api.Logger(this.getClass)

  def recordStudentInfo() = Action.async { implicit request =>
    request.body.asJson.map(json =>
      json.validate[Student].fold(
        valid = { stu =>
          logger.debug("record student info ".concat(request.body.asJson.get.toString))
          studentService.insert(stu).map { id =>
            Ok(Json.toJson(id))
          }
        },
        invalid = { errors =>
          logger.error("info error".concat(JsError.toJson(errors).toString()))
          Future.successful(BadRequest(JsError.toJson(errors)))
        }
      )
    ).getOrElse(Future.successful(BadRequest("wrong record")))
  }
}
