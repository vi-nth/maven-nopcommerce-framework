package factoryBrowser;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import commons.GlobalConstants;
import io.github.bonigarcia.wdm.WebDriverManager;

public class HeadlessChromeDriverManager implements BrowserFactory{

	@Override
	public WebDriver getBrowserDriver() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		options.addArguments("window-size=1920x1080)");
	
		return new ChromeDriver(options);
	}

}
