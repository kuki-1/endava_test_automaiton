package com.endava.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.endava.pages.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Vladimir Krekic
 */

public class Utils {

	private static Logger log = Logger.getLogger(Utils.class);
	/**
	 * @author Vladimir Krekic
	 * @param browser String that represents chosen browser from testng.xml file
	 * @return HomePage
	 */
	public static HomePage setUpWebBrowser(String browser){
		HomePage homePage;
		if(browser.equalsIgnoreCase("chrome")){
			WebDriverManager.chromedriver().setup();
			homePage = new HomePage(new ChromeDriver());
		}else if(browser.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup();
			homePage = new HomePage(new FirefoxDriver());
		}else if(browser.equalsIgnoreCase("internet explorer")){
			WebDriverManager.iedriver().setup();
			homePage = new HomePage(new InternetExplorerDriver());
		}else throw new RuntimeException();
		log.debug("setUpWebBrowser(browser) - returns HomePage with chosen browser driver");
		return homePage;
	}

	/**
	 * @param driver
	 * @param locator
	 */
	public static void webDriverWait(WebDriver driver, By locator) {
		new WebDriverWait(driver, 5)
				.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * Returns option that disables infobars in chrome browser
	 *
	 * @author jelena.corak
	 * @return ChromeOptions
	 */
	public static ChromeOptions disableInfobarsOption() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-infobars");
		return options;
	}

	/**
	 * @author Vladimir Krekic
	 * Method is selecting (clicking on) WebElement
	 * @param element WebElement
	 * @return boolean
	 */
	public static boolean selectElement(WebElement element){
		makeItVisible(element);
		if(element.isDisplayed()){
			element.click();
			log.debug("WebElement clicked");
			return true;
		}
		log.debug("WebElement not visible");
		return false;
	}

	/**
	 * @author Vladimir Krekic
	 * Makes web element visible
	 * @param webElement
	 */
	public static void makeItVisible(WebElement webElement){
		Coordinates coordinates = ((Locatable) webElement).getCoordinates();
		coordinates.inViewPort();
	}
	
	/**
	 * Verifies page URL.
	 * 
	 * @author jelena.corak
	 * 
	 * @param WebDriver driver
	 * @param String expected URL 
	 */
	public static void assertUrl(WebDriver driver, String expectedUrl) {
		Assert.assertEquals(driver.getCurrentUrl().toLowerCase(), expectedUrl.toLowerCase(), "Incorrect URL!");
	}
	
	/**
	 * Asserts that the link in the element is correct.
	 * 
	 * @author jelena.corak
	 * 
	 * @param WebElement web element whose link is being checked
	 * @param String expected link
	 * 
	 */
	public static void assertUrl(WebElement element, String expectedUrl) {		
		Assert.assertTrue(element.getAttribute("href").equalsIgnoreCase(expectedUrl),
				"Incorrect link for icon " + element.getAttribute("class"));
	}

	/**
	 * Verifies page title.
	 * 
	 * @author jelena.corak
	 * 
	 * @param WebDriver driver
	 * @param String expected title
	 */
	public static void assertTitle(WebDriver driver, String expectedTitle) {
		Assert.assertEquals(driver.getTitle().toLowerCase(), expectedTitle.toLowerCase(), "Incorrect title!");
	}
	
	/**
	 * Clicks on the element.
	 * 
	 * @author jelena.corak
	 * 
	 * @param WebDriver driver
	 * @param By Search context of a web element
	 * 
	 */
	public static void clickOnElement(WebDriver driver, By context) {
		WebElement element = driver.findElement(context);		
		Assert.assertTrue(element.isDisplayed(), "Element " + context + " is not present.");		
		element.click();
		log.debug("Clicked on element " + context);
	}
}
