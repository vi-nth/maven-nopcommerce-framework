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

public class ChromeDriverManager implements BrowserFactory{

	@Override
	public WebDriver getBrowserDriver() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.setAcceptInsecureCerts(true);
		
		//options.addArguments("--incognito");  // chay voi an danh
		
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_settings.popups", 0);
		prefs.put("download.default_directory", GlobalConstants.DOWNLOAD_FILE_FOLDER);
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		options.setExperimentalOption("prefs", prefs);

		// Setting language
		options.addArguments("--lang=vi");
		options.addArguments("--disable-notifications"); 
		options.addArguments("--disable-geolocation");
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		
		// Disable log in Chrome
		System.setProperty("webdriver.chrome.args", "--disable-logging");
		System.setProperty("webdriver.chrome.silentOutput", "true");

		// Add extension
		File file = new File(GlobalConstants.PROJECT_PATH + "\\browserExtentions\\google-translate.crx");
		options.addExtensions(file);
		return new ChromeDriver(options);
	}

}
