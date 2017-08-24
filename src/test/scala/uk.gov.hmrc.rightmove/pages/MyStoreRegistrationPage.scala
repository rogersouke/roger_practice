package uk.gov.hmrc.rightmove.pages

import uk.gov.hmrc.rightmove.pages.generic.BasePage

object MyStoreRegistrationPage extends BasePage {

  def selectTitle(): Unit = {
    clickOn(id("id_gender1"))
  }

  def enterNameAndEmailDetails(firstName: String, lastName: String, email: String, password: String): Unit = {
    enterFirstName(firstName)
    enterLastName(lastName)
    enterEmail(email)
    enterPassword(password)
  }

  def enterFirstName(firstName: String): Unit = textField(id("customer_firstname")).value = firstName

  def enterLastName(lastName: String): Unit = textField(id("customer_lastname")).value = lastName

  def enterEmail(email: String): Unit = textField(id("email")).value = email

  def enterPassword(password: String): Unit = pwdField(id("passwd")).value = password

  def dateOfBirthDetails(days: String, month: String, year: String): Unit = {
    selectDate(days)
    selectMonth(month)
    selectYear(year)
  }

  def selectDate(days: String): Unit = singleSel(name("days")).value = days

  def selectMonth(month: String): Unit = singleSel(name("months")).value = month

  def selectYear(year: String): Unit = singleSel(name("years")).value = year

  def clickOnCheckBox = checkbox("newsletter").select()

  def enterAddressDetails(address: String, city: String, state: String, postode: String, country: String): Unit = {
    enterAddress(address)
    enterCity(city)
    selectState(state)
    enterPostcode(postode)
    selectCountry(country)
  }

  def enterAddress(address: String): Unit = textField(name("address1")).value = address
  def enterCity(city: String): Unit = textField(name("city")).value= city
  def selectState(state: String): Unit = singleSel(name("id_state")).value = state
  def enterPostcode(postcode: String): Unit = textField(name("postcode")).value = postcode
  def selectCountry(country: String): Unit = singleSel(name("id_country")).value = country

  def enterMobilePhoneNumberAndAliasAddress(number: String, aliasAddres: String): Unit = {

    enterMobilePhoneNumber(number)
    enterAliasAddress(aliasAddres)
  }

  def enterMobilePhoneNumber(number: String): Unit = telField(name("phone_mobile")).value = number
  def enterAliasAddress(alias: String): Unit = textField(name("alias")).value = alias

  def clickOnRegisterButton = click on id("submitAccount")





}
