package com.atmecs.Assessment3.assessmenttask.pageactions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;





public class PageActionsScrollDown {
	
	
	/**
	 * This method is used to scroll down through the page by the scroll value given
	 */
	public void pageScrollDown(WebDriver driver,String scrollValue)
	{
		JavascriptExecutor javaScriptExecutor = (JavascriptExecutor) driver;
		javaScriptExecutor.executeScript("window.scrollBy(0,"+scrollValue+")");
	}
	
	/**
	 *This method will scroll down till the element is visible
	 */
	public  void pageScrollDownTillElementVisible(WebDriver driver,WebElement elementToBeViewed) 
	{
		JavascriptExecutor javaScriptExecutor = (JavascriptExecutor) driver;
		javaScriptExecutor.executeScript("arguments[0].scrollIntoView();", elementToBeViewed);
	}

	
	
	public  void scrollToBottom(WebDriver driver)
	{
		JavascriptExecutor javaScriptExecutor = (JavascriptExecutor) driver;
		javaScriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	
	/**
	 *This method will scroll upwards in a page
	 */
	public  void pageScrollUp(WebDriver driver) 
	{
		JavascriptExecutor javaScriptExecutor = (JavascriptExecutor) driver;
		javaScriptExecutor.executeScript("window.scrollBy(0,-1700)");
	}

}
