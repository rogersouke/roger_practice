package uk.gov.hmrc.rightmove.pages

import uk.gov.hmrc.rightmove.pages.generic.BasePage

/**
  * Created by roger on 27/08/17.
  */
object MyStoreAccountPage extends BasePage {

  def assertPageHeader(): Unit = {
    val pageHeader = find(cssSelector(".page-heading")).get.text
    val expectedPageHeaderText: String = "MY ACCOUNT"

    pageHeader shouldBe expectedPageHeaderText

  }


  def clickOnCategory(): Unit = click on linkText("T-SHIRTS")

}
