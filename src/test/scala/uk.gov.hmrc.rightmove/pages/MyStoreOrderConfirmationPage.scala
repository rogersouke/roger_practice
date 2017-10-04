package uk.gov.hmrc.rightmove.pages

import uk.gov.hmrc.rightmove.tests.BaseFeatureSpec

/**
  * Created by roger on 04/10/17.
  */
object MyStoreOrderConfirmationPage extends BaseFeatureSpec {

  def pageTitle1 = find(tagName("h1"))

  def bodyHeader = find(tagName("h1")).map(_.text)




}
