package commons;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import factoryBrowser.BrowserList;
import factoryEnvironment.BrowserStackFactory;
import factoryEnvironment.CrossBrowserFactory;
import factoryEnvironment.EnvironmentList;
import factoryEnvironment.GridFactory;
import factoryEnvironment.LamdaFactory;
import factoryEnvironment.LocalFactory;
import factoryEnvironment.SaucelabFactory;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	protected final Log log;

	@BeforeSuite
	public void initBeforeSuite() {
		deleteAllureReport();
	}

	protected BaseTest() {
		log = LogFactory.getLog(getClass());
	}
	
	protected WebDriver getBrowserDriver(String envName, String severName, String browserName, String ipAddress, String portNumber, String osName, String osVersion) {
		
		switch (envName) {
		case "local":
			driver = new LocalFactory(browserName).createDriver();
			break;
			
		case "grid":
			driver = new GridFactory(browserName, ipAddress, portNumber).createDriver();
			break;
			
		case "browserStack":
			driver = new BrowserStackFactory(browserName, osName, osVersion).createDriver();
			break;
			
		case "saucelab":
			driver = new SaucelabFactory(browserName, osName).createDriver();
			break;
			
		case "crossBrowser":
			driver = new CrossBrowserFactory(browserName, osName).createDriver();
			break;
			
		case "lamda":
			driver = new LamdaFactory(browserName, osName, osVersion).createDriver();
			break;

		default:
			driver = new LocalFactory(browserName).createDriver();
			break;
		}
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//driver.get(appUrl);
		//driver.get(GlobalConstants.PORTAL_PAGE_URL);
		System.out.println("Driver ID="+driver.toString());
		driver.get(getEnvironmentUrl(severName));
		return driver;
		
	}
	
	
	public WebDriver getDriverInstance() {
		return this.driver;
	}

	protected String getEnvironmentUrl(String severName) {
		String envUrl = null;
		EnvironmentList environment = EnvironmentList.valueOf(severName.toUpperCase());
		if (environment == EnvironmentList.QUANGBINH) {
			envUrl = "https://quangbinh-noikhu.vimap.vn/";
		}else if (environment == EnvironmentList.NOIKHU) {
			envUrl = "https://test-noikhu.vimap.vn/Account/Login";
		}else if (environment == EnvironmentList.EVN) {
			envUrl = "https:https://evn.vimap.vn/Account/Login";
		}else if (environment == EnvironmentList.MAP4D) {
			envUrl = "https://accounts-dev.map4d.vn/auth/realms/map4d-dev/protocol/openid-connect/auth?response_type=code&client_id=map4d-dev&redirect_uri=https://test.map4d.vn/Auth/Authentication";
		}
		System.out.println(envUrl);
		return envUrl;

	}
	
	protected WebDriver getBrowserDriver(String browserName, String appUrl) {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		if (browserList == BrowserList.FIREFOX) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			driver = new FirefoxDriver(options);

		} else if (browserList == BrowserList.CHROME) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
			driver = new ChromeDriver(options);
		} else {
			throw new RuntimeException("Browser name invalid");
		}
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(appUrl);
		return driver;
	}

	public int generateFakeNumber() {
		Random random = new Random();
		return random.nextInt(9999);
	}

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected boolean verifyTrue(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertTrue(condition);
			log.info(" -------------------------- PASSED -------------------------- ");

		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertFalse(condition);
			log.info(" -------------------------- PASSED -------------------------- ");

		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	public void deleteAllureReport() {
		try {
			String pathFolderDownload = GlobalConstants.PROJECT_PATH + "/allure-json";
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					new File(listOfFiles[i].toString()).delete();
				}
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}

	}

	protected void closeBrowserAndDriver() {
		String cmd = "";
		try {
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name = " + osName);

			String driverInstanceName = driver.toString().toLowerCase();
			log.info("Driver instance name = " + driverInstanceName);

			if (driverInstanceName.contains("chrome")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				} else {
					cmd = "pkill chromedriver";
				}
			} else if (driverInstanceName.contains("internetexplorer")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				}
			} else if (driverInstanceName.contains("firefox")) {
				if (osName.contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
				} else {
					cmd = "pkill geckodriver";
				}
			} else if (driverInstanceName.contains("edge")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
				} else {
					cmd = "pkill msedgedriver";
				}
			} else if (driverInstanceName.contains("opera")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq operadriver*\"";
				} else {
					cmd = "pkill operadriver";
				}
			} else if (driverInstanceName.contains("safari")) {
				if (osName.contains("mac")) {
					cmd = "pkill safaridriver";
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

	protected String getCurrentDay() {
		DateTime nowUTC = new DateTime();
		int day = nowUTC.getDayOfMonth();
		if (day < 10) {
			String dayValue = "0" + day;
			return dayValue;
		}
		return String.valueOf(day);
	}

	protected String getCurrentMonth() {
		DateTime now = new DateTime();
		int month = now.getMonthOfYear();
		if (month < 10) {
			String monthValue = "0" + month;
			return monthValue;
		}
		return String.valueOf(month);
	}

	protected String getCurrentYear() {
		DateTime now = new DateTime();
		return String.valueOf(now.getYear());
	}

	protected String getToday() {
		return getCurrentDay() + "/" + getCurrentMonth() + "/" + getCurrentYear();

	}
}