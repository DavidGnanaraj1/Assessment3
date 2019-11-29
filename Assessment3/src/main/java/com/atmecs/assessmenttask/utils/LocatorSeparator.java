package com.atmecs.assessmenttask.utils;

import org.openqa.selenium.By;

/**
 * This class is to separate the locators value from the locator type and return
 * the By object
 */

public class LocatorSeparator {

	public By separatingLocators(String locatorWithType) {
		String locators[] = locatorWithType.split(",");
		String locatorType = locators[0];
		String locatorValue = locators[1];
		By by = null;

		switch (locators[0]) {
		case "XPATH":
			by = By.xpath(locatorValue);
			break;
		case "CSS":
			by = By.cssSelector(locatorValue);
			break;
		case "ID":
			by = By.id(locatorValue);
			break;
		case "PARTIALLINKTEXT":
			by = By.partialLinkText(locatorValue);
			break;
		case "LINKTEXT":
			by = By.linkText(locatorValue);
			break;
		case "NAME":
			by = By.name(locatorValue);
			break;
		case "CLASS":
			by = By.className(locatorValue);
			break;
		default:

			break;
		}
		return by;

	}
}
