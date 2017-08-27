package uk.gov.hmrc.rightmove.pages.generic

import org.openqa.selenium.{Keys, WebElement}
import org.scalatest._
import org.scalatest.concurrent.{Eventually, PatienceConfiguration}
import org.scalatest.selenium.WebBrowser
import org.scalatest.time.{Millis, Seconds, Span}
import uk.gov.hmrc.rightmove.util.{Env, ImplicitWebDriverSugar, NavigationSugar}


trait BasePage extends org.scalatest.selenium.Page
  with Matchers
  with NavigationSugar
  with WebBrowser
  with Eventually
  with PatienceConfiguration
  with Assertions
  with ImplicitWebDriverSugar
  with Env {

  override val url = ""

  override implicit val patienceConfig: PatienceConfig = PatienceConfig(timeout = scaled(Span(5, Seconds)), interval = scaled(Span(500, Millis)))

  def navigateTo(): Unit = go to s"$baseUrl/$url"

  def isCurrentPage: Boolean = false

  def back(): Unit = click on find(xpath(".//*[@class='back-link']")).get

  def buttonNext(): Unit = clickOn("ButtonNext")

  def submit(): Unit = click on find(xpath(".//*[@type='submit' and contains(text(),'Submit')]")).get

  def continue(): Unit = click on find(xpath(".//*[@type='submit' and contains(text(),'Continue')]")).get

  def agreeAndContinue(): Unit = click on find(xpath(".//*[@type='submit' and contains(text(),'Agree and continue')]")).get

  def confirm(): Unit = click on find(xpath(".//*[@type='submit' and contains(text(),'Confirm')]")).get

  def confirmPayment(): Unit = click on find(xpath(".//*[@type='submit' and contains(text(),'Confirm payments')]")).get

  def signIn(): Unit = click on find(xpath(".//*[@type='submit' and contains(text(),'Sign in')]")).get

  def recalculate(): Unit = click on find(xpath(".//*[@type='submit' and contains(text(),'Recalculate')]")).get


  def textField(id: String, value: String): Unit = {
    val elem = find(id)
    if (elem.isDefined) {
      val e = new TextField(elem.get.underlying)
      if (e.isDisplayed) e.value = value
    }
  }

  def numberField(id: String, value: String): Unit = {
    val elem = find(id)
    if (elem.isDefined) {
      val e = new NumberField(elem.get.underlying)
      if (e.isDisplayed) e.value = value
    }
  }

  def pressKeys(value: Keys): Unit = {
    val e: WebElement = webDriver.switchTo.activeElement
    e.sendKeys(value)
  }

  def singleSel(id: String, value: String): Unit = {
    val elem = find(id)
    if (elem.isDefined) {
      val e = new SingleSel(elem.get.underlying)
      if (e.isDisplayed) e.value = value
    }
  }

  def checkHeader(heading: String, text: String): Unit = {
    find(cssSelector(heading)).exists(_.text == text)
  }



}
