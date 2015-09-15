package webDriver;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

//import com.opera.core.systems.OperaDriver;

public class WebDriverFactory {

  // Factory settings

  private static String defaultHub = null; // change to
                                           // "http://myserver:4444/wd/hub" to
                                           // use remote webdriver by default
                                           private static int restartFrequency = Integer.MAX_VALUE;  //Integer.MAX_VALUE
    private static String key = null;
    private static int count = 0;

  // Factory
  private static WebDriver driver;

    public static void setDefaultHub(String newDefaultHub) {
        defaultHub = newDefaultHub;
    }

    public static void setRestartFrequency(int newRestartFrequency) {
        restartFrequency = newRestartFrequency;
    }

  public static WebDriver getDriver(String hub, Capabilities capabilities) {
    count++;
    // 1. webDriver instance is not created yet
    if (driver == null) {
        driver = newWebDriver(hub, capabilities);
        driver.manage().window().setSize(new Dimension(1200,900));
//        driver.navigate().to(Loader.loadProperty("testUrl"));
        return driver;
    }
    // 2. Different flavour of webDriver is required
    String newKey = capabilities.toString() + ":" + hub;
    if (!newKey.equals(key)) {
      dismissDriver();
      key = newKey;
      return newWebDriver(hub, capabilities);
    }
    // 3. Browser is dead
    try {
      driver.getCurrentUrl();
    } catch (Throwable t) {
      t.printStackTrace();
        driver = newWebDriver(hub, capabilities);
//        driver.navigate().to(Loader.loadProperty("testUrl"));
        return driver;
    }
    // 4. It's time to restart
    if (count >= restartFrequency) {
      dismissDriver();
      return newWebDriver(hub, capabilities);
    }
    // 5. Just use existing webDriver instance
    return driver;
  }

  public static WebDriver getDriver(Capabilities capabilities) {
    return getDriver(defaultHub, capabilities);
  }

  public static void dismissDriver() {
    if (driver != null) {
      try {
        driver.quit();
        driver = null;
        key = null;
      } catch (WebDriverException ex) {
        // it can already be dead or unreachable
      }
    }
  }

  // Factory internals

  private static WebDriver newWebDriver(String hub, Capabilities capabilities) {
    driver = (hub == null)
        ? createLocalDriver(capabilities)
        : createRemoteDriver(hub, capabilities);
    key = capabilities.toString() + ":" + hub;
    count = 0;
    return driver;
  }

  private static WebDriver createRemoteDriver(String hub,
      Capabilities capabilities) {
    try {
      return new RemoteWebDriver(new URL(hub), capabilities);
    } catch (MalformedURLException e) {
      e.printStackTrace();
      throw new Error("Could not connect to webDriver hub", e);
    }
  }

  private static WebDriver createLocalDriver(Capabilities capabilities) {
    String browserType = capabilities.getBrowserName();
    if (browserType.equals("firefox")) {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.helperApps.neverAsk.openFile",
                "text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                "text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
        return new FirefoxDriver(capabilities);
    }
    if (browserType.startsWith("internet explorer")) {
        IEWebDriver.getInstance();
        return new InternetExplorerDriver(capabilities);
    }
    if (browserType.equals("chrome")) {
        ChromeWebDriver.getInstance();

        return new ChromeDriver(capabilities);
    }
//    if (browserType.equals("opera"))
//      return new OperaDriver(capabilities);
    throw new Error("Unrecognized browser type: " + browserType);
  }

    // dissmissDriver after KS_API_FormResultManagementService
//  static {
//    Runtime.getRuntime().addShutdownHook(new Thread() {
//      public void run() {
//        dismissDriver();
//      }
//    });
//  }
}
