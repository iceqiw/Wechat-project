
import controllers.HomeController
import org.scalatest.concurrent.ScalaFutures
import play.api.test._
import play.api.test.Helpers._
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test.CSRFTokenHelper._

class FunctionalSpec extends PlaySpec with GuiceOneAppPerSuite with ScalaFutures {

  def dateIs(date: java.util.Date, str: String) = {
    new java.text.SimpleDateFormat("yyyy-MM-dd").format(date) == str
  }

  def homeController = app.injector.instanceOf(classOf[HomeController])

  "HomeController" should {

    "redirect to the computer list on /" in {
      val result = homeController.index(FakeRequest())

      status(result) must equal(SEE_OTHER)
      redirectLocation(result) mustBe Some("/computers")
    }

//    "list computers on the the first page" in {
//      val result = homeController.list(0, 2, "")(FakeRequest())
//
//      status(result) must equal(OK)
//      contentAsString(result) must include("574 computers found")
//    }
//
//    "filter computer by name" in {
//      val result = homeController.list(0, 2, "Apple")(FakeRequest())
//
//      status(result) must equal(OK)
//      contentAsString(result) must include("13 computers found")
//    }
  }
}
