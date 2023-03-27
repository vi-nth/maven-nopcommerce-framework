package factoryEnvironment;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import commons.GlobalConstants;

public class LamdaFactory {
	private WebDriver driver;
	public LamdaFactory(String browserName, String osName, String osVersion) {
		super();
		this.browserName = browserName;
		this.osName = osName;
		this.osVersion = osVersion;
	}
	private String browserName;
	private String osName;
	private String osVersion;
	
	public WebDriver createDriver() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("version", "latest");
        capabilities.setCapability("platform", osName); 
        capabilities.setCapability("video", true); 
        capabilities.setCapability("visual", true); 
        capabilities.setCapability("build", "LambdaTestSampleApp");
        capabilities.setCapability("name", "LambdaTestJavaSample");
        
        if (osName.contains("Windows")) {
        	capabilities.setCapability("screenResolution", "1920x1080");
		} else {
			capabilities.setCapability("screenResolution", "2560x1600");
		}
		
        capabilities.setCapability("name", "Run on" + osName + "|" +browserName);
        
        try {
            driver = new RemoteWebDriver(new URL(GlobalConstants.LAMDA_URL), capabilities);
        } catch (MalformedURLException e) {
            System.out.println("Invalid Lamda URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
		return driver;
	
	}
	

}
