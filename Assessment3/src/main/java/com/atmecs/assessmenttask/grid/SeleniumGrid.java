package com.atmecs.assessmenttask.grid;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;

import com.atmecs.assessmenttask.reports.LogReport;



public class SeleniumGrid {
	public WebDriver driver; 
	LogReport log =new LogReport();
	@BeforeTest
	public void gridMethod() throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setBrowserName("firefox");		
		String hubUrl ="http://50.50.50.254:2000/wd/hub";
		driver = new RemoteWebDriver(new URL(hubUrl), capabilities);
		driver.get("http://www.atmecs.com");
	
		log.info("Application is open");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(4, TimeUnit.MINUTES);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
}
 