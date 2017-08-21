package uk.gov.hmrc.rightmove.util

import java.io.{FileNotFoundException, IOException}
import java.net.{InetSocketAddress, URL}
import java.util.Properties
import java.util.concurrent.TimeUnit
import java.util.logging.Level

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import net.lightbody.bmp.client.ClientUtil
import net.lightbody.bmp.proxy.auth.AuthType
import net.lightbody.bmp.{BrowserMobProxy, BrowserMobProxyServer}
import org.openqa.selenium.chrome.{ChromeDriver, ChromeOptions}
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.logging.{LogType, LoggingPreferences}
import org.openqa.selenium.remote.{CapabilityType, DesiredCapabilities, RemoteWebDriver}
import org.openqa.selenium.{Proxy, WebDriver}

import scala.collection.JavaConversions._
import scala.io.Source

object SingletonDriver {

  var sessionID = ""

  private val DRIVER_INFO_FLAG = true

  private var instance: WebDriver = _

  private var baseWindowHandle: String = _

  val proxy: BrowserMobProxy = new BrowserMobProxyServer()

  private val systemProperties = System.getProperties

  private val os: String = Option(systemProperties.getProperty("os.name")).getOrElse(sys.error("Could not read OS name"))

  private val isMac: Boolean = os.startsWith("Mac")

  private val isLinux: Boolean = os.startsWith("Linux")

  private val linuxArch: String = Option(systemProperties.getProperty("os.arch")).getOrElse(sys.error("Could not read OS arch"))

  private val isJsEnabled: Boolean = true

  private val driverDirectory: String = "drivers"

  def getInstance(): WebDriver = {
    if (instance == null) initialiseBrowser()
    instance
  }

  def initialiseBrowser() {
    instance = createBrowser()
    instance.manage().window().maximize()
    instance.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS) //polls for 10 seconds before timeout
    baseWindowHandle = instance.getWindowHandle
  }

  private def createBrowser(): WebDriver = {

    val environmentProperty = System.getProperty("browser", "chrome").toLowerCase()

    environmentProperty match {
      case "firefox" => createFirefoxDriver
      case "browserstack" => createBrowserStackDriver
      case "chrome" => createChromeDriver
      case "ie" => createIEDriver
      case "" => throw new IllegalArgumentException("No browser set")
      case _ => throw new IllegalArgumentException(s"Unrecognised browser: $environmentProperty")
    }
  }

  val logs = new LoggingPreferences()
  logs.enable(LogType.BROWSER, Level.SEVERE)

  private def createFirefoxDriver: WebDriver = {
    val capabilities = DesiredCapabilities.firefox()
    addQaProxy(capabilities)
    capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs)
    capabilities.setJavascriptEnabled(isJsEnabled)
    capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true)
    new FirefoxDriver(capabilities)
  }

  private def createIEDriver: WebDriver = {
    val capabilities = DesiredCapabilities.internetExplorer()
    addQaProxy(capabilities)
    capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "https://www.google.co.uk")
    new InternetExplorerDriver(capabilities)
  }

  private def createChromeDriver: WebDriver = {
    if (isMac) {
      systemProperties.setProperty("webdriver.chrome.driver", s"$driverDirectory/chromedriver_mac")
    } else if (isLinux && linuxArch == "amd32") {
      systemProperties.setProperty("webdriver.chrome.driver", s"$driverDirectory/chromedriver_linux32")
    } else if (isLinux) {
      systemProperties.setProperty("webdriver.chrome.driver", s"$driverDirectory/chromedriver")
    } else {
      systemProperties.setProperty("webdriver.chrome.driver", s"$driverDirectory/chromedriver.exe")
    }

    val capabilities = DesiredCapabilities.chrome()
    val options = new ChromeOptions()

    options.addArguments("test-type")
    options.addArguments("--disable-gpu")

    capabilities.setJavascriptEnabled(isJsEnabled)
    capabilities.setCapability(ChromeOptions.CAPABILITY, options)

    new ChromeDriver(capabilities)
  }

  private def createBrowserStackDriver: WebDriver = {
    var userName: String = null
    var automateKey: String = null

    try {
      val prop: Properties = new Properties()
      prop.load(this.getClass.getResourceAsStream("/browserConfig.properties"))

      userName = prop.getProperty("username")
      automateKey = prop.getProperty("automatekey")
    }
    catch {
      case e: FileNotFoundException => e.printStackTrace();
      case e: IOException => e.printStackTrace();
    }

    // create capabilities with device/browser settings from config file
    val bsCaps = getBrowserStackCapabilities
    val desCaps = new DesiredCapabilities(bsCaps)

    // set additional generic capabilities
    desCaps.setCapability("browserstack.debug", "true")
    desCaps.setCapability("browserstack.local", "true")
    desCaps.setCapability("project", "")
    desCaps.setCapability("build", "")

    val bsUrl = s"http://$userName:$automateKey@hub-cloud.browserstack.com/wd/hub"
    val rwd = new RemoteWebDriver(new URL(bsUrl), desCaps)
    val sessionId = rwd.getSessionId.toString
    sessionID = sessionId
    rwd
  }

  private def getBrowserStackCapabilities: Map[String, Object] = {
    val testDevice = System.getProperty("testDevice", "BS_Win8_1_IE_11")
    val resourceUrl = s"/browserstackdata/$testDevice.json"
    val cfgJsonString = Source.fromURL(getClass.getResource(resourceUrl)).mkString

    val mapper = new ObjectMapper() with ScalaObjectMapper
    mapper.registerModule(DefaultScalaModule)
    mapper.readValue[Map[String, Object]](cfgJsonString)
  }


  private def addQaProxy(capabilities: DesiredCapabilities): Unit = {
    if (Option(System.getProperty("qa.proxy")).isDefined) {
      val proxySettingPattern = """(.+):(.+)@(.+):(\d+)""".r
      System.getProperty("qa.proxy") match {
        case proxySettingPattern(user, password, host, port) =>
          proxy.chainedProxyAuthorization(user, password, AuthType.BASIC)
          proxy.setChainedProxy(new InetSocketAddress(host, port.toInt))
        case _ => throw new RuntimeException("QA Proxy settings must be provided as username:password@proxyHost:proxyPortNumber")
      }
      proxy.setTrustAllServers(true)
      proxy.start()
      val seleniumProxy: Proxy = ClientUtil.createSeleniumProxy(proxy)
      seleniumProxy.setNoProxy("*www-qa*")
      capabilities.setCapability(CapabilityType.PROXY, seleniumProxy)
    }
  }

}