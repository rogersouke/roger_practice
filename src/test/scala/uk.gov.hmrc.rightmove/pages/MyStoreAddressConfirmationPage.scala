package uk.gov.hmrc.rightmove.pages

import uk.gov.hmrc.rightmove.pages.generic.BasePage

/**
  * Created by roger on 06/09/17.
  */
object MyStoreAddressConfirmationPage extends BasePage{

  def clickOnProceedButton = click on xpath(".//*[@id='center_column']/form/p/button")

  def enterQueryComment(search_query: String): Unit = {
    enterComment(search_query)
  }

  def enterComment(search_query: String): Unit = textArea(name("message")).value = search_query

}
