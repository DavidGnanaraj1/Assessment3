package com.atmecs.Assessment3.assessmenttask.pageactions;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.atmecs.assessmenttask.constants.FilePath;
import com.atmecs.assessmenttask.utils.LocatorSeparator;
import com.atmecs.assessmenttask.utils.PropertiesFileReader;

/**
 * This class has methods for various assertion like asserting two string text,
 * verifying the expected page title with the actual page title
 */


/**
 * This method is used for checking the actual 
 */
public class AssertionHelpers {
	LocatorSeparator separatinglocators;
	Properties properties;
	PageActions pageactions;
	Properties testdatapropertiesfile;
	
	/**
	 * This constructor loads the locators.properties file
	 */
	public AssertionHelpers() throws IOException {
		
		separatinglocators=new LocatorSeparator();
		properties= new PropertiesFileReader().loadingPropertyFile(FilePath.LOCATORS_FILE);
		pageactions= new PageActions();
		testdatapropertiesfile= new PropertiesFileReader().loadingPropertyFile(FilePath.EXPECTEDDATA_FILE);
	}
	

	/**
	 * This method gets the actual string text from webpage and get the expected string text from the properties
	 *  assert whether actual string texts and expected string texts are equal or not
	 */
	public void assertingStringTexts(WebDriver driver,String actualElementLocator,String expectedTextValue) throws IOException {
		
		WebElement actualTextElement =driver.findElement(separatinglocators.separatingLocators(properties.getProperty(actualElementLocator)));
	    String actualTextValue=actualTextElement.getText();
	   
	    
	    Assert.assertEquals(actualTextValue, expectedTextValue);
	    
}
	
	/**
	 * This method gets the actual pagetitle from webpage and get the expected page title from properties file
	 *  assert whether actual pagetitle and expected pagetitle are equal or not
	 */
	public  void assertingPageTitle(WebDriver driver,String expectedTitleLocator) {

		String actualPageTitle=driver.getTitle();
		String expectedPageTitle=testdatapropertiesfile.getProperty(expectedTitleLocator);
		Assert.assertEquals(actualPageTitle, expectedPageTitle);
	}
	
	
	public String assertingMultipleValues(WebDriver driver,String expectedDataArray[],int iterationNumber,int numberOfProducts,String actualCommonElementLocator) {
		String returnStatement = null;
		for(int index=0;index<iterationNumber;index++) {
	    
		List<WebElement> actualdata=driver.findElements(separatinglocators.separatingLocators(properties.getProperty(actualCommonElementLocator)));
	    String actualdataarray[]= new String [numberOfProducts];
	    actualdataarray[index]=actualdata.get(index).getText();
	    try {
			Assert.assertEquals(actualdataarray[index], expectedDataArray[index]);
			return returnStatement="Validation completed for "+expectedDataArray[index];
		} catch (Exception e) {
			e.printStackTrace();
			return returnStatement="Assertion failed" + actualdataarray[index] + "," +expectedDataArray[index] + " not matching";

		}
		}
	        return returnStatement;
	    
		    	
   	}
	}
   
	


