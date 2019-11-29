package com.atmecs.Assessment3.assessmenttask.pageactions;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.atmecs.assessmenttask.constants.FilePath;
import com.atmecs.assessmenttask.reports.LogReport;
import com.atmecs.assessmenttask.utils.LocatorSeparator;
import com.atmecs.assessmenttask.utils.PropertiesFileReader;

/**
 * This class has methods to perform actions in an element like clicking the
 * element,selecting the dropdown, sending the values to textbox, to check
 * whether an element is displayed or not,moveByOffset,MouseOver
 */




public class PageActions {
	LogReport log;
	WebDriver driver;
	LocatorSeparator separatelocator;
    ExplicitWaitHelpers waithelper;
    Properties properties;
	
    public PageActions() throws IOException {

		log = new LogReport();
		separatelocator = new LocatorSeparator();
		waithelper = new ExplicitWaitHelpers();
		properties= new PropertiesFileReader().loadingPropertyFile(FilePath.LOCATORS_FILE);
	}
	
	
	/**
	 * This method is used for clicking an element 
	 */
	public void clickingTheElement(WebDriver driver, String locatorWithType) {
		
		WebElement elementToBeClicked = driver.findElement(separatelocator.separatingLocators(locatorWithType));
		waithelper.explicitwaitForElementToBeClickable(driver, elementToBeClicked);
		elementToBeClicked.click();
	}
	
	
	/**
	 * This method is used for selecting the dropdown value by using the visisble text
	 */
	public void selectingTheDropdownvalueByVisibleText(WebElement element,String locatorWithType, String value) {

		waithelper.explicitwaitForElementToBeClickable(driver, element);
		WebElement dropDownElement = driver.findElement(separatelocator.separatingLocators(locatorWithType));
		Select select = new Select(dropDownElement);
		select.selectByVisibleText(value);
	}
	
	
	/**
	 * This method is used for sending the value to an text box
	 */
	public void sendKeys(WebDriver driver, String locatorWithType, String value) {

		WebElement element = driver.findElement(separatelocator.separatingLocators(locatorWithType));
		element.sendKeys(value);
	}

	
	/**
	 * This method is used for checking the given element is displayed or not
	 */
	public void isDisplayed(WebDriver driver, String locatorWithType) {

		boolean isDisplayed = driver.findElement(separatelocator.separatingLocators(locatorWithType)).isDisplayed();

		if (isDisplayed == true) {
			log.info("Element is displayed");
		} else {
			log.info("Element not found");
		}
	}
	
	
	/**
	 * This method is used to move the mouse and keep it over an element 
	 */
	public void moveToAnElement(WebDriver driver,WebElement element) {
	
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
	}


	/**
	 * This method will get the value attribute of the given attribute
	 */
	 public  String getAttributeValue(WebDriver driver,String locatorWithType,String value)
	    {
			WebElement element = driver.findElement(separatelocator.separatingLocators(locatorWithType));
			String text = element.getAttribute(value);
			return text;
		}
	 /**
	  * This method is used to get the text of the webelement for the locator given
	  */
	 public String gettingTextValue(WebDriver driver,String locatorWithType) {
		 
		 String elementText=driver.findElement(separatelocator.separatingLocators(locatorWithType)).getText();
		 return elementText;
	 }
	
	 /**
	  * This method is used to move the mouse by the given X,Y values
	  */
	 public void moveByOffsetHelper(WebDriver driver, String locatorWithType) {
			
		    WebElement elm = driver.findElement(separatelocator.separatingLocators(locatorWithType));
			Point pt = elm.getLocation();
			int NumberX = pt.getX();
			int NumberY = pt.getY();
			Actions act = new Actions(driver);
			act.moveByOffset(NumberX + 1, NumberY).click().build().perform();
		}
	 
		/**
		 * This method is used to check whether the element  with given element type is displayed or not
		 */
	 public void  checkingPageRedirection(WebDriver driver,String  targetPageElementLocator) {
	    	
		    WebElement targetPageElement=driver.findElement(separatelocator.separatingLocators(properties.getProperty(targetPageElementLocator)));
		    if(!(targetPageElement.isDisplayed())){
		    	log.info("Not in the correct targeted page");
		    }
	    	
	    	
	    }
	 
	 /**
		 * This method is used to check whether the element  with given element type is displayed or not
		 */
     public void isSelected(WebDriver driver,String locatorWithType) {
    	 WebElement elementToBeCheckedWhetherSelected=driver.findElement(separatelocator.separatingLocators(locatorWithType));
    
        if(elementToBeCheckedWhetherSelected.isSelected()) {
     	   log.info(elementToBeCheckedWhetherSelected+"is selected ");
     	   
        }else if(!(elementToBeCheckedWhetherSelected.isSelected())) {
     	   log.info(elementToBeCheckedWhetherSelected+"not selected ");
    	
    	
    }}
     /**
      * This method gets the value of the element by using the javascript executor-query selector
     */
     public void gettingDynamicValues(WebDriver driver,String elementCssLocator) {
   	 JavascriptExecutor javascript= (JavascriptExecutor)driver;
	 String vaalue=javascript.executeScript("return document.querySelector(\"input[name='adults']\").value").toString();
	 System.out.println(vaalue);
    }
    
}