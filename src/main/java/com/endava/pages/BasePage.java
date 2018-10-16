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
	public boolean isTitleCorrect(WebDriver driver, String title) {
		return driver.getTitle().equalsIgnoreCase(title);
	}

	public void quit() {
		if (this != null) {
			driver.quit();
			log.debug("quit()");
		}
	}
}
