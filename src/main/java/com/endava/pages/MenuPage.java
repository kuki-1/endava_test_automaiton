package com.endava.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author jana.djordjevic@endava.com
 *
 */
public class MenuPage extends BasePage {

	private By automationMenuItem = By.xpath(".//*[@id='mCSB_1_container']/div[1]/nav/ul/li[3]/a");
	private By navigationList = By.className("navigation");
	private static Logger log = Logger.getLogger(MenuPage.class);

	public MenuPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Checks if link is present on the page and clicks on it
	 * 
	 * @author Goran.Kukolj
	 * @return driver to automationPage
	 */
	public AutomationPage openAutomationPage() {
		driver.findElement(this.automationMenuItem).click();
		log.debug("Finds automation menu item in burger menu and clicks on it");
		return new AutomationPage(driver);
	}

	public By getNavigationList() {
		return navigationList;
	}
}