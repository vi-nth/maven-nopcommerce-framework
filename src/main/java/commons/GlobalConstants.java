package commons;

import java.io.File;

public class GlobalConstants {
//	public static final String JQUREY_PAGE_URL = "https://www.jqueryscript.net/demo/CRUD-Data-Grid-Plugin-jQuery-Quickgrid/";
	public static final String JQUREY_PAGE_URL = "https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/";
	public static final String JQUREY_UPLOAD_PAGE_URL = "https://blueimp.github.io/jQuery-File-Upload/";
	public static final String PORTAL_PAGE_URL = "https://demo.nopcommerce.com/";
	public static final String ADMIN_PAGE_URL = "https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F";

	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String JAVA_VERSION = System.getProperty("java.version");
	public static final String OS_NAME = System.getProperty("os.name");
	public static final String UPLOAD_FILE_FOLDER = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
	public static final String REPORTNG_SCREENSHOT = PROJECT_PATH + File.separator + "ReportNGImages" + File.separator;
	public static final String DOWNLOAD_FILE_FOLDER = PROJECT_PATH + File.separator + "downloadFiles";
	public static final String BROWSER_LOG_FOLDER = PROJECT_PATH + File.separator + "browserLogs";
	public static final String DRAG_DROP_HTML5 = PROJECT_PATH + File.separator + "dragDropHTML5";
	public static final String AUTO_IT_SCRIPT = PROJECT_PATH + File.separator + "autoIT";

	/**
	 * DB account/ user/ pass/ port -->ip = 192.168.1.15:9860
	 */
	public static final String DB_DEV_URL = "32.18.252.185:9860";
	public static final String DB_DEV_USER = "automationfc";
	public static final String DB_DEV_PASS = "Abc@123";

	public static final String DB_TEST_URL = "32.18.195.23:9860";
	public static final String DB_TEST_USER = "automationfc";
	public static final String DB_TEST_PASS = "Abc@123";

	public static final long SHORT_TIMEOUT = 5;
	public static final long LONG_TIMEOUT = 10;
	public static final long RETRY_TEST_FAIL = 3;

	public static final String BROWSER_USERNAME = "vinguyen_4ACIay";
	public static final String BROWSER_AUTOMATE_KEY = "9K53KAwVssbdJDTbMkmY";
	public static final String BROWSER_STACK_URL = "http://" + BROWSER_USERNAME + ":" + BROWSER_AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
	
	public static final String SAUCELAB_USERNAME = "oauth-nguyenvidue-3fa28";
	public static final String SAUCELAB_AUTOMATE_KEY = "221b7945-3784-4bea-af20-4ee014021820";
	public static final String SAUCELAB_URL = "https://" + SAUCELAB_USERNAME + ":" + SAUCELAB_AUTOMATE_KEY + "@ondemand.apac-southeast-1.saucelabs.com:443/wd/hub";
	
	public static final String CROSS_USERNAME = "nguyenvidue@gmail.com".replaceAll("@", "%40");
	public static final String CROSS_AUTOMATE_KEY = "af20-4ee014021820";
	public static final String CROSS_URL = "https://" + CROSS_USERNAME + ":" + CROSS_AUTOMATE_KEY + "@hub.crossbrowsertesting.com:80/wd/hub";
	
	public static final String LAMDA_USERNAME = "nguyenvidue";
	public static final String LAMDA_AUTOMATE_KEY = "dsmWuRAlf4PLS7dwOXXRrmGTHquPFMAf2G9aLn4igqezFVLrJY";
	public static final String LAMDA_URL = "https://" + LAMDA_USERNAME + ":" + LAMDA_AUTOMATE_KEY + "@hub.lambdatest.com/wd/hub";

}
