package com.atmecs.assessmenttask.helpermethods;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.atmecs.Assessment3.assessmenttask.pageactions.AssertionHelpers;
import com.atmecs.Assessment3.assessmenttask.pageactions.PageActions;
import com.atmecs.Assessment3.assessmenttask.pageactions.PageActionsScrollDown;
import com.atmecs.assessmenttask.constants.FilePath;
import com.atmecs.assessmenttask.reports.ExtentReport;
import com.atmecs.assessmenttask.reports.LogReport;
import com.atmecs.assessmenttask.utils.ExcelFileReader;
import com.atmecs.assessmenttask.utils.ExcelFileWriter;
import com.atmecs.assessmenttask.utils.LocatorSeparator;
import com.atmecs.assessmenttask.utils.PropertiesFileReader;

public class BookingHotels_AutomationHelpers {
	PageActionsScrollDown pageScroll = new PageActionsScrollDown();
	Properties properties;
	PageActions pageactions;
	Properties testdata;
	LocatorSeparator separatelocator;
	LogReport log;
	AssertionHelpers assertionhelpers;
	String sheetName;
	ExcelFileReader excelreader;
	ExcelFileWriter excelwriter;
	ExtentReport extentReport = new ExtentReport();
	String noOfStars[];
	String noOfStarsExpected = "4/5";
	String dateSplittedToArray[];
	int checkInDateIntFormat;
	String checkInDate;
	int hotelPriceArray[];
	int priceIndex = 0, finalIndex;
	int lowestPriceIndex;

	int size;
	int noOfAdults;
	int noOfChilds, checkOutDate;


	public BookingHotels_AutomationHelpers() throws IOException {

		pageactions = new PageActions();
		properties = new PropertiesFileReader().loadingPropertyFile(FilePath.LOCATORS_FILE);
		testdata = new PropertiesFileReader().loadingPropertyFile(FilePath.EXPECTEDDATA_FILE);
		separatelocator = new LocatorSeparator();
		assertionhelpers = new AssertionHelpers();

		log = new LogReport();
		excelreader = new ExcelFileReader(FilePath.TESTDATA_FILE);
		excelwriter = new ExcelFileWriter();
	}

	public WebElement webElement(WebDriver driver,String elementLocator) {
		WebElement targetElement=driver.findElement(separatelocator.separatingLocators(properties.getProperty("elementLocator")));
		return targetElement;
	}
	
	public int calculatingCheckInDate() {

		LocalDate currentDate = LocalDate.now();
		LocalDate dateAfterTenDays = currentDate.plusDays(26);
		checkInDate = dateAfterTenDays.toString();

		dateSplittedToArray = new String[3];
		dateSplittedToArray = checkInDate.split("-");
		checkInDateIntFormat = Integer.parseInt(dateSplittedToArray[2]);
		return checkInDateIntFormat;

	}

	public void searchingTheHotels(WebDriver driver) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		noOfAdults = Integer.parseInt(testdata.getProperty("expdata.noofadults"));
		noOfChilds = Integer.parseInt(testdata.getProperty("expdata.noofchild"));
		sheetName = testdata.getProperty("expdata.sheetname");

		assertionhelpers.assertingPageTitle(driver, "expdata.pagetitle");
		log.info("PageTitle validated");

		driver.findElement(By.xpath("//span[@class='select2-match'][contains(.,'Bangalore')]"))
				.sendKeys(testdata.getProperty("expdata.cityname"));

		pageactions.clickingTheElement(driver, properties.getProperty("loc.hotelslinktext"));
		pageactions.clickingTheElement(driver, properties.getProperty("loc.checkindate"));
		pageactions.clickingTheElement(driver, properties.getProperty("loc.navbutton"));

		pageactions.clickingTheElement(driver, properties.getProperty("loc.navbutton"));
		pageactions.clickingTheElement(driver, properties.getProperty(properties.getProperty("loc.checkoutbox")));
		pageactions.clickingTheElement(driver, properties.getProperty("loc.navbutton"));
		for(int adultsIndex=0;adultsIndex<noOfAdults;adultsIndex++) {
			
		pageactions.clickingTheElement(driver, properties.getProperty("loc.adultplusbtn"));	
		Thread.sleep(3000);
		}
		for(int childIndex=0;childIndex<noOfChilds;childIndex++) {
			pageactions.clickingTheElement(driver, properties.getProperty("loc.childplusbtn"));		
		}
		pageactions.clickingTheElement(driver,properties.getProperty("loc.button"));	
	    
		log.info("Redirecting to the Searched results");
	}

	public void findingTheFourStarRatedHotelAtLowestPrice(WebDriver driver) {
		List<Double> listOfHotelPrice = new ArrayList<Double>();
		List<WebElement> hotelRatingsElement = driver.findElements(By.xpath("//span[@class='bg-primary']"));
		noOfStars = new String[hotelRatingsElement.size()];

		for (int index = 0; index < hotelRatingsElement.size(); index++) {

			pageScroll.pageScrollDownTillElementVisible(driver, hotelRatingsElement.get(index));

			if (!(hotelRatingsElement.get(index).isDisplayed())) {
				WebElement viewMoreButton = driver
						.findElement(separatelocator.separatingLocators(properties.getProperty("loc.viewmore")));
				pageScroll.pageScrollDownTillElementVisible(driver, viewMoreButton);
				break;
			}

			noOfStars[index] = hotelRatingsElement.get(index).getText();

			if (noOfStars[index] == noOfStarsExpected) {

				String priceText = driver
						.findElement(By
								.xpath("(//div[@class='product-long-item']//div[@class='price']/span)[" + index + "]"))
						.getText();
				System.out.println("Hotels price With four star ratings" + priceText);
				double removedSymbols = new Double(priceText).doubleValue();
				listOfHotelPrice.add(priceIndex, removedSymbols);
				priceIndex++;
			}
			log.info("Found all the Hotel in the 4 star rating with lowest price");
		}
		List<Double> listOfHotelPriceAfterSorting = new ArrayList<Double>();

		for (int duplicatingIndex = 0; duplicatingIndex < listOfHotelPrice.size(); duplicatingIndex++) {
			listOfHotelPriceAfterSorting.add(duplicatingIndex, listOfHotelPrice.get(duplicatingIndex));
		}

		Collections.sort(listOfHotelPriceAfterSorting);

		for (int findingTheNoIndex = 0; findingTheNoIndex < listOfHotelPrice.size(); findingTheNoIndex++) {
			if (listOfHotelPrice.get(findingTheNoIndex) == listOfHotelPriceAfterSorting.get(0)) {
				lowestPriceIndex = findingTheNoIndex;
				break;
			}

			List<WebElement> detailsButton = driver
					.findElements(separatelocator.separatingLocators(properties.getProperty("loc.detailsbtn")));

			driver.navigate().to(testdata.getProperty("expdata.url"));

			pageScroll.pageScrollDownTillElementVisible(driver, detailsButton.get(finalIndex));
			detailsButton.get(finalIndex).click();
			if (!(detailsButton.get(finalIndex).isDisplayed())) {
				pageactions.clickingTheElement(driver, properties.getProperty("loc.viewmore"));
			} else {
				detailsButton.get(finalIndex).click();
			}
		}
		log.info("Found the four star rating hotel with the lowest price");

	}
	public void hotelDetailValidation(WebDriver driver) throws IOException {
		
		driver.findElement(separatelocator.separatingLocators(properties.getProperty("loc.hotelsdetailsbutton")));
		driver.navigate().to(testdata.getProperty("expdata.hoteldetails"));
		
		
		assertionhelpers.assertingStringTexts(driver, "loc.hotelname", testdata.getProperty("expdata.hotelname"));
		
		assertionhelpers.assertingStringTexts(driver, "loc.hotelplace", testdata.getProperty(""));
		
		
		
		
		
		
		
		
	}

}
