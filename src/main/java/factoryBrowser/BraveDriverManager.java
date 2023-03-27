package factoryBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BraveDriverManager implements BrowserFactory {

	@Override
	public WebDriver getBrowserDriver() {
		// Brave browser version nào thì dùng browser vesion đó
		WebDriverManager.chromedriver().driverVersion("100.0.4896.60").setup();
		ChromeOptions options = new ChromeOptions();
		options.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
		return new ChromeDriver(options);

	}

}
