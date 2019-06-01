package models

import java.util.Date

import anorm.SqlParser.{get, str}
import anorm._
import javax.inject.Inject
import play.api.db.DBApi
import play.api.libs.json.Json

import scala.concurrent.Future

case class User(id: Option[Long] = None,
                telephone: String,
                password: String,
                nickName: Option[String]=None,
                modifyTime: Option[Date]=None,
                createTime: Option[Date]=None)

object User {
  implicit val userWrites = Json.writes[User]
  implicit val userReads = Json.reads[User]

  implicit def toParameters: ToParameterList[User] =
    Macro.toParameters[User]
}

@javax.inject.Singleton
class UserRepository @Inject()(dbapi: DBApi)(implicit ec: DatabaseExecutionContext) {

  private val db = dbapi.database("default")

  private[models] val simple = {
    get[Option[Long]]("user.id") ~
      str("user.telephone") ~
      str("user.password") ~
      get[Option[String]]("user.nick_name") ~
      get[Option[Date]]("user.modify_time") ~
      get[Option[Date]]("user.create_time") map {
      case id ~ telephone ~ password ~ nickName ~ modifyTime ~ createTime =>
        User(id, telephone, password, nickName, modifyTime, createTime)
    }
  }

  def findByTelephone(telephone: String): Future[Option[User]] = Future {
    db.withConnection { implicit connection =>
      SQL"""select * from user
           where telephone  = $telephone
           limit 0,1
        """
        .as(simple.singleOpt)
    }
  }(ec)

  def insert(user: User): Future[Option[Long]] = Future {
    db.withConnection { implicit connection =>
      SQL(
        """
        insert into user(telephone,password,nick_name) values ({telephone}, {password}, {nickName}
        )
      """).bind(user).executeInsert()
    }
  }(ec)

}
