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

	public void quit() {
		if (this != null) {
			driver.quit();
		}
	}
}
