package com.endava.pages;

import org.openqa.selenium.WebDriver;

/**
 * @author jana.djordjevic@endava.com
 *
 */
public class BasePage {

	public WebDriver driver;

	protected BasePage(WebDriver driver) {
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
		}
	}
}
