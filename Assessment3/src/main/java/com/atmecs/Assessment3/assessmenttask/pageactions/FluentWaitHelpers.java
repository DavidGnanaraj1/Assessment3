
package com.atmecs.Assessment3.assessmenttask.pageactions;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import freemarker.template.utility.Constants;


/**
 * This class has method for fluent wait of different types by provided type of exception ,the fluentwait type is selected
 *
 */
public class FluentWaitHelpers {
	
	
	
	@SuppressWarnings("deprecation")
	public void fluentWait(WebDriver driver,String exceptionToBeIgnored) {
	
	FluentWait<WebDriver> fluentWait;
	String exceptionType=exceptionToBeIgnored.toLowerCase();
	
	switch(exceptionType) {
	
	case "staleelementexception":
	fluentWait = new FluentWait<WebDriver>(driver)
			.withTimeout(30,TimeUnit.SECONDS)
			.pollingEvery(5,TimeUnit.SECONDS)
			.ignoring(StaleElementReferenceException.class);
	    break;
	case "elementnotinteractableexception":
		fluentWait = new FluentWait<WebDriver>(driver)
				.withTimeout(30,TimeUnit.SECONDS)
				.pollingEvery(5,TimeUnit.SECONDS)
				.ignoring(ElementNotInteractableException.class);
		break;
	case "elementnotselectableexception":
		fluentWait = new FluentWait<WebDriver>(driver)
				.withTimeout(30,TimeUnit.SECONDS)
				.pollingEvery(5,TimeUnit.SECONDS)
				.ignoring(ElementNotSelectableException.class);
		break;
	case "elementnotvisibleexception":
		fluentWait = new FluentWait<WebDriver>(driver)
				.withTimeout(30,TimeUnit.SECONDS)
				.pollingEvery(5,TimeUnit.SECONDS)
				.ignoring(ElementNotVisibleException.class);
		break;
		
	default :
	fluentWait = new FluentWait<WebDriver>(driver)
	.withTimeout(30,TimeUnit.SECONDS)
	.pollingEvery(5,TimeUnit.SECONDS)
	.ignoring(NoSuchElementException.class);
	break;
	}
	}
	public void sleepWaitCommand(int timeInMilliSeconds) throws InterruptedException {
		Thread.sleep(timeInMilliSeconds);
	}
}
