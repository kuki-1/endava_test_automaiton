package com.endava.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author jana.djordjevic@endava.com
 *
 */
public class MenuPage extends BasePage {

	private By navigationList = By.className("navigation");

	public MenuPage(WebDriver driver) {
		super(driver);
	}

	public By getNavigationList() {
		return navigationList;
	}

}
