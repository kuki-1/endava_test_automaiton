package com.endava.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

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

	public void quit() {
		if (this.driver != null) {
			driver.quit();
			log.debug("Browser closed");
		}
	}
}