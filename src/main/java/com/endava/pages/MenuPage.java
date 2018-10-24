package com.endava.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

/**
 * @author jana.djordjevic@endava.com
 *
 */
public class MenuPage extends BasePage {

	private By automationMenuItem = By.xpath(".//*[@id='mCSB_1_container']/div[1]/nav/ul/li[3]/a");
	private By navigationList = By.className("navigation");
	private By investorsMenuItem = By.xpath(".//*[@id='mCSB_1_container']/div[1]/nav/ul/li[5]/a");		
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

	/**
	 * Returns search context of Investors element in the Menu.
	 * 
	 * @author jelena.corak
	 * 
	 * @return By search context of Investors element
	 */
	public By getInvestorsMenuItem() {
		return investorsMenuItem;
	}

	/**
	 * Finds the INVESTORS element in the menu and clicks on it.
	 * 
	 * @author jelena.corak
	 */
	public InvestorsPage clickOnInvestors() {
		WebElement investors = driver.findElement(investorsMenuItem);
		Assert.assertTrue(investors.isDisplayed(), "Element INVESTORS is not present.");
		investors.click();
		return new InvestorsPage(driver);
	}	
}