package com.endava.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.endava.util.Utils;

/**
 * @author jana.djordjevic@endava.com
 *
 */
public class BasePage {

	public WebDriver driver;
	private static Logger log = Logger.getLogger(BasePage.class);

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * @author Goran.Kukolj
	 * @param driver
	 * @param title
	 * @return true or false depending on equality of actual title and expected
	 *         title
	 */
	public static boolean isTitleCorrect(WebDriver driver, String title) {
		log.debug("Checks if the title is correct");
		return driver.getTitle().equalsIgnoreCase(title);
	}

	/**
	 * @author Goran.Kukolj
	 * @param driver
	 * @param title
	 * @return true or false depending on equality of URL
	 */
	public static boolean isURLTheSame(WebDriver driver, String title) {
		log.debug("Checks if the URL has changed");
		return driver.getCurrentUrl().equalsIgnoreCase(title);
	}

	/**
	 * @author Vladimir Krekic Method is selecting (clicking on) WebElement
	 * @param element WebElement
	 * @return boolean
	 */
	public boolean selectElement(WebElement element) {
		Utils.makeItVisible(element);
		if (element.isDisplayed()) {
			element.click();
			log.debug("WebElement clicked");
			return true;
		}
		log.debug("WebElement not visible");
		return false;
	}

	/**
	 * @author Goran.Kukolj
	 * @param driver
	 * @param locator
	 * @param originalText
	 * @return true or false depending on comparing two strings
	 */
	public boolean validateString(WebDriver driver, By locator, String originalText) {
		WebElement element = driver.findElement(locator);
		String textToCompare = element.getText();
		log.debug("Compares two strings");
		return textToCompare.equals(originalText);
	}

	public void quit() {
		if (this.driver != null) {
			driver.quit();
			log.debug("Browser closed");
		}
	}
}