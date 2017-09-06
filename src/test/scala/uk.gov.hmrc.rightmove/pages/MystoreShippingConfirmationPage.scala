package uk.gov.hmrc.rightmove.pages

import uk.gov.hmrc.rightmove.pages.generic.BasePage

/**
  * Created by roger on 06/09/17.
  */
object MystoreShippingConfirmationPage extends BasePage {

def radioButtonGroup(): Unit = radioButtonGroup("delivery_option[26454]").value = "2,"
  def checkBox(): Unit = checkbox("cgv").select()
  def clickOnProceedToCheckOut = click on xpath(".//*[@id='form']/p/button")

}
