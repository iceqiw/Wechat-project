package models

import java.util.Date

import anorm.SqlParser.{get, str}
import anorm._
import javax.inject.Inject
import play.api.db.DBApi
import play.api.libs.json.Json

import scala.concurrent.Future

case class Course(id: Option[Long] = None, title: String, createTime: Option[Date])

object Course {
  implicit val courseWrites = Json.writes[Course]

}

@javax.inject.Singleton
class CourseRepository @Inject()(dbapi: DBApi)(implicit ec: DatabaseExecutionContext) {

  private val db = dbapi.database("default")

  private[models] val simple = {
    get[Option[Long]]("course.id") ~
      str("course.title") ~
      get[Option[Date]]("course.create_time") map {
      case id ~ title ~ createTime => Course(id, title, createTime)
    }
  }

  def findLastOne(): Future[Option[Course]] = Future {
    db.withConnection { implicit connection =>
      SQL"""select * from course
           order by create_time desc
           limit 0,1
        """
        .as(simple.singleOpt)
    }
  }(ec)
}
