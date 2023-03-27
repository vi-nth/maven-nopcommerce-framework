package factoryBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import commons.GlobalConstants;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CoccocDriverManager implements BrowserFactory {

	@Override
	public WebDriver getBrowserDriver() {

		// Cococ browser trừ đi 5-6 version
		WebDriverManager.chromedriver().driverVersion("99.0.4844.51").setup();
		ChromeOptions options = new ChromeOptions();
		if (GlobalConstants.OS_NAME.startsWith("Windows")) {
			options.setBinary("C:\\Program Files (x86)\\CocCoc\\Browser\\Application\\browser.exe");
		} else {
			options.setBinary("");
		}
		
		return new ChromeDriver(options);

	}

}
