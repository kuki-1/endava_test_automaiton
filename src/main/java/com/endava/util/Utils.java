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
import org.openqa.selenium.JavascriptExecutor;
import com.endava.pages.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.interactions.internal.Coordinates;

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
			homePage = new HomePage(new ChromeDriver(disableInfobarsOption()));
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
	 * Scrolls element into view.
	 * 
	 * @author jelena.corak
	 * 
	 * @param WebDriver
	 *            driver
	 * @param By
	 *            element search context
	 * 
	 */
	public static void scrollIntoView(WebDriver driver, By context) {
		WebElement element = driver.findElement(context);
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception g) {
			log.debug(">>>> Exception in scrollIntoView! Element may not have been found: " + element);
			Assert.fail("Element not found.");
		}
	}	

	/**
	 * Returns text contained in the web element.
	 * 
	 * @author jelena.corak
	 * @param WebDriver
	 *            driver
	 * @param By
	 *            Search context of a web element
	 * 
	 * @return String text of the web element
	 */
	public static String getTextFromElement(WebDriver driver, By context) {
		WebElement webElement = driver.findElement(context);
		if (!webElement.isDisplayed()) {
			Assert.fail("No element found.");
		}
		log.debug("Text contained in the following element(" + context + "): " + webElement.getText());
		return webElement.getText();
	}
}
