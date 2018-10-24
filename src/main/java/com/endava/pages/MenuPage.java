package com.endava.pages;

import java.util.ArrayList;
import java.util.List;

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
	private By logo = By.cssSelector("span.pos-rel > a:nth-child(1)");
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
	 * Returns search context of Endava logo element in menu pages 
	 * (except Investors page).
	 * 
	 * @author jelena.corak
	 * 
	 * @return By search context of About Us element
	 */
	public By getLogo() {
		return logo;
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
	
	/**
	 * Returns list of menu pages URLs.
	 * 
	 * @author jelena.corak
	 * 
	 * @return List<String> list of menu pages URLs
	 * 
	 */
	public List<String> getMenuPagesUrlList() {
		List<String> menuPagesUrlList = new ArrayList<>();
		menuPagesUrlList.add(DigitalPage.getDigitalUrl());
		menuPagesUrlList.add(AgilePage.getAgileUrl());
		menuPagesUrlList.add(AutomationPage.getEndavaAutomationUrl());
		menuPagesUrlList.add(ServicesPage.getServicesUrl());
		menuPagesUrlList.add(InvestorsPage.getInvestorsUrl());
		menuPagesUrlList.add(IndustriesPage.getIndustriesUrl());
		menuPagesUrlList.add(SuccessStoriesPage.getSuccessStoriesUrl());
		menuPagesUrlList.add(AboutPage.getAboutUrl());
		menuPagesUrlList.add(CareersPage.getCareersUrl());
		menuPagesUrlList.add(ContactPage.getContactUrl());
		return menuPagesUrlList;
	}
	
	/**
	 * Returns list of menu pages URLs.
	 * 
	 * @author jelena.corak
	 * 
	 * @return List<String> list of menu pages URLs
	 * 
	 */
	public List<String> getMenuPagesTitleList() {
		List<String> menuPagesTitleList = new ArrayList<>();
		menuPagesTitleList.add(DigitalPage.getDigitalTitle());
		menuPagesTitleList.add(AgilePage.getAgileTitle());
		menuPagesTitleList.add(AutomationPage.getEndavaAutomationTitle());
		menuPagesTitleList.add(ServicesPage.getServicesTitle());
		menuPagesTitleList.add(InvestorsPage.getInvestorsTitle());
		menuPagesTitleList.add(IndustriesPage.getIndustriesTitle());
		menuPagesTitleList.add(SuccessStoriesPage.getSuccessStoriesTitle());
		menuPagesTitleList.add(AboutPage.getAboutTitle());
		menuPagesTitleList.add(CareersPage.getCareersTitle());
		menuPagesTitleList.add(ContactPage.getContactTitle());
		return menuPagesTitleList;
	}
}