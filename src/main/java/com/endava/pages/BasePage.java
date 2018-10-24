package com.endava.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

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
	 * Verifies page URL correctness.
	 * 
	 * @author jelena.corak
	 * 
	 * @param WebDriver driver
	 * @param String expected URL *
	 */
	public void assertUrl(WebDriver driver, String expectedUrl) {
		Assert.assertEquals(driver.getCurrentUrl().toLowerCase(), expectedUrl.toLowerCase(), "Incorrect URL!");
	}

	/**
	 * Verifies page title correctness.
	 * 
	 * @author jelena.corak
	 * 
	 * @param WebDriver driver
	 * @param String expected title
	 */
	public void assertTitle(WebDriver driver, String expectedTitle) {
		Assert.assertEquals(driver.getTitle().toLowerCase(), expectedTitle.toLowerCase(), "Incorrect title!");
	}

	/**
	 * Clicks on the element.
	 * 
	 * @author jelena.corak
	 * 
	 * @param By Search context of a web element *
	 */
	public void clickOnElement(By context) {
		WebElement eventElement = driver.findElement(context);
		String elementClass = eventElement.getAttribute("class");
		Assert.assertTrue(eventElement.isDisplayed(), "Element is not present.");
		eventElement.click();
		log.debug("Clicked on element " + elementClass);
	}

	public void quit() {
		if (this.driver != null) {
			driver.quit();
			log.debug("Browser closed");
		}
	}
}