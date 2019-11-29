package com.atmecs.assessmenttask.testscripts;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.atmecs.Assessment3.assessmenttask.pageactions.AssertionHelpers;
import com.atmecs.Assessment3.assessmenttask.pageactions.PageActions;
import com.atmecs.Assessment3.assessmenttask.testbase.TestBase;
import com.atmecs.assessmenttask.constants.FilePath;
import com.atmecs.assessmenttask.helpermethods.BookingHotels_AutomationHelpers;
import com.atmecs.assessmenttask.reports.ExtentReport;
import com.atmecs.assessmenttask.reports.LogReport;
import com.atmecs.assessmenttask.utils.ExcelFileReader;
import com.atmecs.assessmenttask.utils.ExcelFileWriter;
import com.atmecs.assessmenttask.utils.LocatorSeparator;
import com.atmecs.assessmenttask.utils.PropertiesFileReader;

public class BookingHotels_Automation extends TestBase {
	JavascriptExecutor js = (JavascriptExecutor) driver;
	int size;
	int noOfAdults, checkInDate;
	int noOfChilds, checkOutDate;
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
	BookingHotels_AutomationHelpers bookingHotelsHelpers = new BookingHotels_AutomationHelpers();

	public BookingHotels_Automation() throws IOException {

		pageactions = new PageActions();
		properties = new PropertiesFileReader().loadingPropertyFile(FilePath.LOCATORS_FILE);
		testdata = new PropertiesFileReader().loadingPropertyFile(FilePath.EXPECTEDDATA_FILE);
		separatelocator = new LocatorSeparator();
		assertionhelpers = new AssertionHelpers();

		log = new LogReport();
		excelreader = new ExcelFileReader(FilePath.TESTDATA_FILE);
		excelwriter = new ExcelFileWriter();
	}

	/**
	 * This method is to function the booking of the hotels in an automated way
	 *
	 */
	@Test
	public void bookingHotels() throws IOException, InterruptedException {

		assertionhelpers.assertingPageTitle(driver,"expdata.hotelresultstitle");
		log.info("Result of Hotel Search page verified");
		
		checkInDate = bookingHotelsHelpers.calculatingCheckInDate();
		checkOutDate = checkInDate + Integer.parseInt(testdata.getProperty("expdata.noofchild"));
		log.info("CheckIn date and CheckOut dates are" +checkInDate+","+ checkOutDate);

		bookingHotelsHelpers.findingTheFourStarRatedHotelAtLowestPrice(driver);
        bookingHotelsHelpers.searchingTheHotels(driver);
        bookingHotelsHelpers.hotelDetailValidation(driver);
	}

	@AfterTest
	public void closingTheBrowser(WebDriver driver) {

		driver.close();

	}

}
