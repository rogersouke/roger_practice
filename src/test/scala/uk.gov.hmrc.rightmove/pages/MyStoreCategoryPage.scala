package uk.gov.hmrc.rightmove.pages

import org.openqa.selenium.By
import uk.gov.hmrc.rightmove.pages.generic.BasePage

/**
  * Created by roger on 28/08/17.
  */
object MyStoreCategoryPage extends BasePage {

  def chooseCategory(): Unit = driver.findElement(By.partialLinkText("Faded Short Sleeve T-shirt")).click()

  def addTshirtToCart(): Unit = driver.findElement(By.xpath(".//*[@id='add_to_cart']/button")).click()

  def clickOnQuickView = click on  cssSelector(".product_img_link > img:nth-child(1)")


  def clickOnOrangeColour = find(id("color_13")).get.underlying.getCssValue("background-color")

//  def assertColorIsBlue = find(id("color_14")).get.underlying.getCssValue("background-color"): Unit {

    def assertColorIsBlue(): Unit = {
      val colorIsBlue = find(id("color_14")).get.underlying.getCssValue("background-color")
      colorIsBlue shouldBe  "rgba(93, 156, 236, 1)"


  }

}
