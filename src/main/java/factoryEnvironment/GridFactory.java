package factoryEnvironment;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import commons.GlobalConstants;
import factoryBrowser.BrowserList;
import io.github.bonigarcia.wdm.WebDriverManager;

public class GridFactory {
	private WebDriver driver;
	private String browserName;
	private String ipAddress;
	private String portNumber;



	public GridFactory(String browserName, String ipAddress, String portNumber) {
		this.browserName = browserName;
		this.ipAddress = ipAddress;
		this.portNumber = portNumber;
	}

	public WebDriver createDriver() {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		DesiredCapabilities capability = null;

		if (browserList == BrowserList.FIREFOX) {
			WebDriverManager.firefoxdriver().setup();
			File pathBinary = new File("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
			FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);   
			FirefoxOptions options = new FirefoxOptions();
			
			capability = DesiredCapabilities.firefox();
			capability.setBrowserName("firefox");
			capability.setPlatform(Platform.ANY);
			capability.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options.setBinary(firefoxBinary));
			options.merge(capability);

		} else if (browserList == BrowserList.HEAD_FIREFOX) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080)");
			driver = new FirefoxDriver(options);

		} else if (browserList == BrowserList.CHROME) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			capability = DesiredCapabilities.chrome();
			capability.setBrowserName("chrome");
			capability.setPlatform(Platform.ANY);
			options.merge(capability);

		} else if (browserList == BrowserList.HEAD_CHROME) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080)");
			driver = new ChromeDriver(options);

		} else if (browserList == BrowserList.OPERA) {
			// Brave browser version nào thì dùng browser vesion đó
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();

		} else if (browserList == BrowserList.EDGE) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		} else if (browserList == BrowserList.COCCOC) {
			// Cococ browser trừ đi 5-6 version
			WebDriverManager.chromedriver().driverVersion("99.0.4844.51").setup();
			ChromeOptions options = new ChromeOptions();
			if (GlobalConstants.OS_NAME.startsWith("Windows")) {
				options.setBinary("C:\\Program Files (x86)\\CocCoc\\Browser\\Application\\browser.exe");
			} else {
				options.setBinary("");
			}
			driver = new ChromeDriver(options);

		} else if (browserList == BrowserList.BRAVE) {
			// Brave browser version nào thì dùng browser vesion đó
			WebDriverManager.chromedriver().driverVersion("100.0.4896.60").setup();
			ChromeOptions options = new ChromeOptions();
			options.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
			driver = new ChromeDriver(options);

		} else if (browserList == BrowserList.IE) {
			WebDriverManager.iedriver().arch32().setup();
			driver = new InternetExplorerDriver();

		} else {
			throw new RuntimeException("Browser name is invalid!");
		}

		try {
			driver = new RemoteWebDriver(new URL(String.format("http://%s:%s/wd/hub", ipAddress, portNumber)),capability);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return driver;

	}

}
