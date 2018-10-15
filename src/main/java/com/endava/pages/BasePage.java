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

	protected BasePage(WebDriver driver) {
		this.driver = driver;
	}

	public void quit() {
		if (this != null) {
			driver.quit();
			log.debug("quit()");
		}
	}
}
