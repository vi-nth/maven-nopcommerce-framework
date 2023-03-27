package factoryEnvironment;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

import commons.GlobalConstants;
import factoryBrowser.BraveDriverManager;
import factoryBrowser.BrowserList;
import factoryBrowser.BrowserNotSupportedException;
import factoryBrowser.ChromeDriverManager;
import factoryBrowser.CoccocDriverManager;
import factoryBrowser.EdgeDriverManager;
import factoryBrowser.FirefoxDriverManager;
import factoryBrowser.HeadlessChromeDriverManager;
import factoryBrowser.HeadlessFirefoxDriverManager;
import factoryBrowser.IEDriverManager;
import factoryBrowser.OperaDriverManager;
import factoryBrowser.SafariDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LocalFactory {
	private WebDriver driver;
	private String browserName;

	public LocalFactory(String browserName) {
		this.browserName = browserName;
	}

	public WebDriver createDriver() {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		switch (browserList) {
		case CHROME:
			driver = new ChromeDriverManager().getBrowserDriver();
			break;

		case FIREFOX:
			driver = new FirefoxDriverManager().getBrowserDriver();
			break;

		case SAFARI:
			driver = new SafariDriverManager().getBrowserDriver();
			break;

		case IE:
			driver = new IEDriverManager().getBrowserDriver();
			break;

		case EDGE:
			driver = new EdgeDriverManager().getBrowserDriver();
			break;

		case HEAD_CHROME:
			driver = new HeadlessChromeDriverManager().getBrowserDriver();
			break;

		case HEAD_FIREFOX:
			driver = new HeadlessFirefoxDriverManager().getBrowserDriver();
			break;
			
		case COCCOC:
			driver = new CoccocDriverManager().getBrowserDriver();
			break;
			
		case BRAVE:
			driver = new BraveDriverManager().getBrowserDriver();
			break;
			
		case OPERA:
			driver = new OperaDriverManager().getBrowserDriver();
			break;

		default:
			throw new BrowserNotSupportedException(browserName);
		}
		return driver;
	}
}
