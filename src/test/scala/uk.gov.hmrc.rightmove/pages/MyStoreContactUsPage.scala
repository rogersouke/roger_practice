package uk.gov.hmrc.rightmove.pages

import org.openqa.selenium.{By, WebDriver}
import uk.gov.hmrc.rightmove.pages.generic.BasePage

/**
  * Created by roger on 29/09/17.
  */
object MyStoreContactUsPage extends BasePage{

  def clickOnContactUsLink = click on id("contact-link")

  def selectSubjectHeading(subjectHeading: String): Unit = singleSel(name("id_contact")).value = subjectHeading
  def enterEmail(email: String): Unit = textField(id("email")).value = email
//  def selectOrderReference(orderReference: String): Unit = singleSel(name("search_query")).value = ""

  def enterComment(search_query: String): Unit = textArea(name("message")).value = search_query

  def fileUpload= find(id("fileUpload"))

  def uploadQueryFile(queryName: String)(implicit driver: WebDriver): Unit = {
    val resourceUrl = s"/src/test/resources/UploadData/$queryName"
    println(queryName)
    val workingDir = System.getProperty("user.dir");
    val filename = workingDir + resourceUrl
    driver.findElement(By.id("fileUpload")).sendKeys(filename)
  }

  def clickOnSend = click on(id("submitMessage"))

}
