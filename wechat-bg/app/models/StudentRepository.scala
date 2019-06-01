package models

import java.util.Date

import anorm.SqlParser.{get, str}
import anorm._
import javax.inject.Inject
import play.api.db.DBApi
import play.api.libs.json.Json

import scala.concurrent.Future

case class Student(id: Option[Long] = None,
                   name: String,
                   modifyTime: Option[Date] = None,
                   createTime: Option[Date] = None)

object Student {
  implicit val userWrites = Json.writes[Student]
  implicit val userReads = Json.reads[Student]

  implicit def toParameters: ToParameterList[Student] =
    Macro.toParameters[Student]
}

@javax.inject.Singleton
class StudentRepository @Inject()(dbapi: DBApi)(implicit ec: DatabaseExecutionContext) {

  private val db = dbapi.database("default")

  private[models] val simpleStudent = {
    get[Option[Long]]("student.id") ~
      str("student.name") ~
      get[Option[Date]]("student.modify_time") ~
      get[Option[Date]]("student.create_time") map {
      case id ~ name ~ modifyTime ~ createTime =>
        Student(id, name, modifyTime, createTime)
    }
  }

  def insert(student: Student): Future[Option[Long]] = Future {
    db.withConnection { implicit connection =>
      SQL(
        """
        insert into student(name) values ({name})
      """).bind(student).executeInsert()
    }
  }(ec)
}
