package com.atmecs.Assessment3.assessmenttask.testbase;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;

import com.atmecs.assessmenttask.constants.FilePath;
import com.atmecs.assessmenttask.reports.ExtentReport;
import com.atmecs.assessmenttask.reports.LogReport;
import com.atmecs.assessmenttask.utils.PropertiesFileReader;

/**
 * This Class reads browser name and url from the config.properties file and
 * select the browser accordingly and also get the url,maximize the
 * window,implicit wait
 */
public class TestBase  {
	public WebDriver driver;
	LogReport log = new LogReport();
	Properties property;
	String url;
	String browser;

	@BeforeMethod
	public void initializeBrowser() throws Exception {

		property = new PropertiesFileReader().loadingPropertyFile(FilePath.CONFIG_FILE);

		url = property.getProperty("url");
	    browser = property.getProperty("browser");

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", FilePath.CHROME_FILE);
			driver = new ChromeDriver();
			log.info("Chrome browser opens");
		}
		else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", FilePath.FIREFOX_FILE);
			driver = new FirefoxDriver();
			log.info("Firefox browser opens");
		}
		else if (browser.equalsIgnoreCase("internet explorer")) {
			System.setProperty("webdriver.ie.driver", FilePath.IE_FILE);
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, url);
			driver = new InternetExplorerDriver(capabilities);
			log.info("IE browser opens");
		}

		driver.get(url);
		log.info("Application is open");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(4, TimeUnit.MINUTES);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}
}
