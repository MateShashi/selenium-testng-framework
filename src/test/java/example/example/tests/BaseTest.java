package example.example.tests;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import example.example.context.WebDriverContext;
import example.example.listeners.LogListener;
import example.example.listeners.ReportListener;
import example.example.util.LoggerUtil;
import example.example.util.MailUtil;
import example.example.util.TestProperties;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Every test class should extend this calss.
 *
 * @author Bharathish
 */
@Listeners({ ReportListener.class, LogListener.class })
public class BaseTest {

	/** The driver. */
	protected WebDriver driver;

	/**
	 * Global setup.
	 */
	@BeforeSuite(alwaysRun = true)
	public void globalSetup() {
		LoggerUtil.log("************************** Test Execution Started ************************************");
		TestProperties.loadAllPropertie();
	}

	/**
	 * Wrap all up.
	 *
	 * @param context the context
	 */
	@AfterSuite(alwaysRun = true)
	public void wrapAllUp(ITestContext context) {
		int total = context.getAllTestMethods().length;
		int passed = context.getPassedTests().size();
		int failed = context.getFailedTests().size();
		int skipped = context.getSkippedTests().size();
		LoggerUtil.log("Total number of testcases : " + total);
		LoggerUtil.log("Number of testcases Passed : " + passed);
		LoggerUtil.log("Number of testcases Failed : " + failed);
		LoggerUtil.log("Number of testcases Skipped  : " + skipped);
		boolean mailSent = MailUtil.sendMail(total, passed, failed, skipped);
		LoggerUtil.log("Mail sent : " + mailSent);
		LoggerUtil.log("************************** Test Execution Finished ************************************");
	}

	/**
	 * Setup.
	 */
	@BeforeClass
	protected void setup() {
		String chromeDriverPath = "/usr/bin/chromedriver";
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		HashMap<String, Object> chromePrefs = new HashMap<>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", chromeDriverPath);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		options.addArguments("--no-sandbox");
		options.addArguments("--headless"); //should be enabled for Jenkins
		options.addArguments("--disable-dev-shm-usage"); //should be enabled for Jenkins
		options.addArguments("--window-size=1920x1080"); //should be enabled for Jenkins
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverContext.setDriver(driver);
	}

	/**
	 * Wrap up.
	 */
	@AfterClass
	public void wrapUp() {
		if (driver != null) {
			driver.close();
			driver.quit();
		}
	}
}
