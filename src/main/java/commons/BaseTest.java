package commons;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class BaseTest {
  private WebDriver driver;
  protected final Log log;

  protected BaseTest() {
    log = LogFactory.getLog(getClass());
  }

  private enum BROWSER {
    CHROME,
    FIREFOX,
    IE,
    SAFARI,
    EDGE_LEGACY,
    EDGE_CHROMIUM,
    H_CHROME,
    H_FIREFOX,
    REMOTE
  }

  protected WebDriver getBrowserDriver(String browserName, String appUrl) {
    BROWSER browser = BROWSER.valueOf(browserName.toUpperCase());
    String hubUrl = System.getProperty("browserUrl");
    if (StringUtils.isNotEmpty(hubUrl)) {
      browser = BROWSER.REMOTE;
    }
    switch (browser) {
      case FIREFOX:
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        break;
      case CHROME:
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        break;
      case REMOTE:
        WebDriverManager.seleniumServerStandalone().setup();
        ChromeOptions capabilities = new ChromeOptions();
        try {
          driver = new RemoteWebDriver(new URL(hubUrl), capabilities);
        } catch (MalformedURLException e) {
          e.printStackTrace();
          log.error("Error: " + e.getMessage());
        }
      default:
        new RuntimeException("Pls input browser name");
        break;
    }
    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    driver.manage().window().maximize();
    driver.get(appUrl);
    return driver;
  }

  public void sleepInSecond(long timeoutInSecond) {
    try {
      Thread.sleep(timeoutInSecond * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public String getCurrentlyTenant() {
    if (driver.getCurrentUrl().contains(".de/de")) {
      return "de";
    } else if (driver.getCurrentUrl().contains(".at/de")) {
      return "at";
    } else if (driver.getCurrentUrl().contains(".ch/de")) {
      return "ch";
    } else if (driver.getCurrentUrl().contains(".ch/fr")) {
      return "fr";
    } else {
      return "nl";
    }
  }

  public static long getRandomNumberByDateTime() {
    return Calendar.getInstance().getTimeInMillis() % 100000;
  }

  public String getRandomEmail() {
    return "postmaster+"
        + getCurrentlyTenant()
        + getRandomNumberByDateTime()
        + "@deerberg.dev.mgm-tp.com";
  }

  private boolean checkTrue(boolean condition) {
    boolean pass = true;
    try {
      if (condition == true) {
        log.info(" -------------------------- PASSED -------------------------- ");
      } else {
        log.info(" -------------------------- FAILED -------------------------- ");
      }
      Assert.assertTrue(condition);
    } catch (Throwable e) {
      pass = false;
      VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
      Reporter.getCurrentTestResult().setThrowable(e);
    }
    return pass;
  }

  protected boolean verifyTrue(boolean condition) {
    return checkTrue(condition);
  }

  private boolean checkFailed(boolean condition) {
    boolean pass = true;
    try {
      if (condition == false) {
        log.info(" -------------------------- PASSED -------------------------- ");
      } else {
        log.info(" -------------------------- FAILED -------------------------- ");
      }
      Assert.assertFalse(condition);
    } catch (Throwable e) {
      pass = false;
      VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
      Reporter.getCurrentTestResult().setThrowable(e);
    }
    return pass;
  }

  protected boolean verifyFalse(boolean condition) {
    return checkFailed(condition);
  }

  private boolean checkEquals(Object actual, Object expected) {
    boolean pass = true;
    try {
      Assert.assertEquals(actual, expected);
      log.info(" -------------------------- PASSED -------------------------- ");
    } catch (Throwable e) {
      pass = false;
      log.info(" -------------------------- FAILED -------------------------- ");
      VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
      Reporter.getCurrentTestResult().setThrowable(e);
    }
    return pass;
  }

  protected boolean verifyEquals(Object actual, Object expected) {
    return checkEquals(actual, expected);
  }

  protected void cleanBrowserAndDriver() {
    String cmd = "";
    try {
      String osName = System.getProperty("os.name").toLowerCase();
      log.info("OS name = " + osName);

      String driverInstanceName = driver.toString().toLowerCase();
      log.info("Driver instance name = " + osName);

      if (driverInstanceName.contains("chrome")) {
        if (osName.contains("window")) {
          cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
        } else {
          cmd = "pkill chromedriver";
        }
      } else if (driverInstanceName.contains("firefox")) {
        if (osName.contains("windows")) {
          cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
        } else {
          cmd = "pkill geckodriver";
        }
      }

      if (driver != null) {
        driver.manage().deleteAllCookies();
        driver.quit();
      }
    } catch (Exception e) {
      log.info(e.getMessage());
    } finally {
      try {
        Process process = Runtime.getRuntime().exec(cmd);
        process.waitFor();
      } catch (IOException e) {
        e.printStackTrace();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
