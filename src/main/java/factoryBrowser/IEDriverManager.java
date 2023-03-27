package factoryBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import static org.apache.commons.lang3.SystemUtils.IS_OS_WINDOWS;

import io.github.bonigarcia.wdm.WebDriverManager;

public class IEDriverManager implements BrowserFactory{

	@Override
	public WebDriver getBrowserDriver() {
		if (! IS_OS_WINDOWS) {
			throw new BrowserNotSupportedException("IE is not suppotetes on"+System.getProperty("os.name"));
		}
		
		WebDriverManager.iedriver().arch32().setup();
		// Selenium 2x
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		InternetExplorerOptions options = new InternetExplorerOptions(capabilities);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setCapability(InternetExplorerDriver.ELEMENT_SCROLL_BEHAVIOR, true);
		capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
		capabilities.setCapability("ignoreProtectedModeSettings", true);
		capabilities.setCapability("ignoreZoomSettings", true);
		capabilities.setCapability("requireWindowFocus", true);
		capabilities.setCapability("enableElementCacheCleanup", true);
		
//		capabilities.setJavascriptEnabled(true);
//		capabilities.setBrowserName("internet explore");
//		capabilities.setPlatform(org.openqa.selenium.Platform.ANY);
//		return new InternetExplorerDriver(capabilities);
		
		// Selenium 3x
//		InternetExplorerOptions options = new InternetExplorerOptions(capabilities);
//		options.setCapability("requireWindowFocus", true);
//		options.setCapability("ie.userPerProcessProxy", "true");
//		options.setCapability("ie.browserCommandLineSwitches", "-private");
//		options.setCapability("ie.ensureCleanSession", "true");
		
		return new InternetExplorerDriver(options);
	}

}
