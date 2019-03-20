
import org.scalatest.concurrent.ScalaFutures
import org.scalatestplus.play._
import org.scalatestplus.play.guice.GuiceOneAppPerSuite

class ModelSpec extends PlaySpec with GuiceOneAppPerSuite with ScalaFutures {

  import models._

  import scala.concurrent.ExecutionContext.Implicits.global

  // -- Date helpers

  def dateIs(date: java.util.Date, str: String) = {
    new java.text.SimpleDateFormat("yyyy-MM-dd").format(date) == str
  }

  // --

  def computerService: ComputerRepository = app.injector.instanceOf(classOf[ComputerRepository])

  "Computer model" should {

    "be retrieved by id" in {
      whenReady(computerService.findById(21)) { maybeComputer =>
        val macintosh = maybeComputer.get

        macintosh.name must equal("Macintosh")
        macintosh.introduced.value must matchPattern {
          case date: java.util.Date if dateIs(date, "1984-01-24") =>
        }
      }
    }

    "be listed along its companies" in {
      whenReady(computerService.list()) { computers =>

        computers.total must equal(574)
        computers.items must have length (10)
      }
    }

    "be updated if needed" in {

      val result = computerService.findById(21).flatMap { computer =>
        computerService.update(21, Computer(name = "The Macintosh",
          introduced = None,
          discontinued = None,
          companyId = Some(1))).flatMap { _ =>
          computerService.findById(21)
        }
      }

      whenReady(result) { computer =>
        val macintosh = computer.get

        macintosh.name must equal("The Macintosh")
        macintosh.introduced mustBe None
      }
    }
  }

  def courseService: CourseRepository = app.injector.instanceOf(classOf[CourseRepository])

  "Course model" should {

    "be found last one" in {
      whenReady(courseService.findLastOne()) { maybeCourse =>
        val course = maybeCourse.get

        course.id.getOrElse(0) must equal(1)
      }
    }
  }

  def userService: UserRepository = app.injector.instanceOf(classOf[UserRepository])

  "User model" should {

    "be saved" in {
      val userId=userService.insert(User(telephone = "123123123",password = "1231313"))
      whenReady(userId) { id =>
        id.getOrElse(0) must equal(1)
      }
    }

    "be found by telephone" in {
      val user=userService.findByTelephone("123123123")
      whenReady(user) { user =>
        user.getOrElse(null) must not equal(null)
        user.get.id.get must equal(1)
      }
    }

  }

}
