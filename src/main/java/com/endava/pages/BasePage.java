package com.endava.pages;

import com.endava.util.Utils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.interactions.internal.Coordinates;
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
	 * Verifies page URL.
	 * 
	 * @author jelena.corak
	 *
	 * @param String expected URL
	 */
	public void assertPageUrl(String expectedUrl) {
		Assert.assertEquals(driver.getCurrentUrl().toLowerCase(), expectedUrl.toLowerCase(), "Incorrect URL!");
	}

	/**
	 * Verifies page title.
	 * 
	 * @author jelena.corak
	 *
	 * @param String expected title
	 */
	public void assertPageTitle(String expectedTitle) {
		Assert.assertEquals(driver.getTitle().toLowerCase(), expectedTitle.toLowerCase(), "Incorrect title!");
	}

	/**
	 * Scrolls element into view.
	 * 
	 * @author jelena.corak
	 *
	 * @param By element search context
	 * 
	 */
	public void scrollIntoView(By context) {
		WebElement element = driver.findElement(context);
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception g) {
			log.debug(">>>> Exception in scrollIntoView! Element may not have been found: " + element);
			Assert.fail();
		}
	}

	/**
	 * Asserts that the link in the element is correct.
	 * 
	 * @author jelena.corak
	 * 
	 * @param WebElement web element whose link is being checked
	 * @param String     expected link
	 * 
	 */
	public static void assertElementLink(WebElement element, String expectedLink) {
		Assert.assertTrue(element.getAttribute("href").equalsIgnoreCase(expectedLink),
				"Incorrect link for icon " + element.getAttribute("class"));
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

	/**
	 * Clicks directly to element in case of overlay.
	 * 
	 * @author jelena.corak
	 *
	 * @param By        element search context
	 * 
	 */
	public void directClickOnElement(By context) {
		WebElement element = driver.findElement(context);
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		} catch (Exception ex) {
			log.debug(">>>> Exception in directClickOnElement! Element may not have been found: " + element);
			Assert.fail();
		}
	}

	/**
	 * @author Vladimir Krekic Method is selecting (clicking on) WebElement
	 * @param element WebElement
	 * @return boolean
	 */
	public boolean selectElement(WebElement element) {
		makeItVisible(element);
		if (element.isDisplayed()) {
			element.click();
			log.debug("WebElement clicked " + element.toString());
			return true;
		}
		log.debug("WebElement not visible " + element.toString());
		return false;
	}

	/**
	 * @author Vladimir Krekic
	 * @return search result text
	 */
	public String getSearchResult(WebElement element) {
		log.debug("Search Result found on element " + element.toString());
		return element.getText();
	}

	/**
	 * @author Vladimir Krekic Makes web element visible
	 * @param webElement
	 */
	public void makeItVisible(WebElement webElement) {
		Coordinates coordinates = ((Locatable) webElement).getCoordinates();
		coordinates.inViewPort();
	}

	/**
	 * @author Vladimir Krekic
	 * Method is selecting (clicking on) WebElement
	 * @param element WebElement
	 * @return boolean
	 */
	public boolean selectElement(WebElement element){
		Utils.makeItVisible(element);
		if(element.isDisplayed()){
			element.click();
			log.debug("WebElement clicked " + element.toString());
			return true;
		}
		log.debug("WebElement not visible " + element.toString());
		return false;
	}


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