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
	private By investorsAboutUs = By.xpath("//*[@id='_ctrl0_ctl66_divModuleContainer']");
	private By investorsLogo = By.xpath("//img[@src='//s22.q4cdn.com/220269410/files/design/logo-endava.png']");	
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
	 * Returns search context of About Us element on the INVESTORS page.
	 * 
	 * @author jelena.corak
	 * 
	 * @return By search context of About Us element
	 */
	public By getInvestorsAboutUs() {
		return investorsAboutUs;
	}
	
	/**
	 * Returns search context of Endava logo element on the INVESTORS page.
	 * 
	 * @author jelena.corak
	 * 
	 * @return By search context of About Us element
	 */
	public By getInvestorsLogo() {
		return investorsLogo;
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
	public void clickOnInvestors() {
		WebElement investors = driver.findElement(investorsMenuItem);
		Assert.assertTrue(investors.isDisplayed(), "Element INVESTORS is not present.");
		investors.click();
	}

	/**
	 * Returns text contained in the web element.
	 * 
	 * @author jelena.corak
	 * @param By Search context of a web element
	 * 
	 * @return String text of the web element
	 */
	public String getTextFromElement(By context) {
		WebElement webElement = driver.findElement(context);
		if (!webElement.isDisplayed()) {
			Assert.fail("No element found.");			
		} 
		log.debug("Text contained in the following element(" + context + "): " + webElement.getText());
		return webElement.getText();
	}
	
	/**
	 * Clicks on the event element in the menu.
	 * 
	 * @author jelena.corak
	 * 
	 * @param By Search context of a web element
	 * 
	 */
	public void clickOnMenuEvent(By context) {
		WebElement eventElement = driver.findElement(context);
		String elementClass = eventElement.getAttribute("class");
		Assert.assertTrue(eventElement.isDisplayed(), "Element " + elementClass + " is not present.");		
		eventElement.click();
		log.debug("Clicked on element " + elementClass);
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
		menuPagesUrlList.add("https://www.endava.com/en/Digital");
		menuPagesUrlList.add("https://www.endava.com/en/Agile");
		menuPagesUrlList.add("https://www.endava.com/en/Automation");
		menuPagesUrlList.add("https://www.endava.com/en/Services");
		menuPagesUrlList.add("https://investors.endava.com/home/default.aspx");
		menuPagesUrlList.add("https://www.endava.com/en/Industries");
		menuPagesUrlList.add("https://www.endava.com/en/Success-Stories");
		menuPagesUrlList.add("https://www.endava.com/en/About");
		menuPagesUrlList.add("https://careers.endava.com/en");
		menuPagesUrlList.add("https://www.endava.com/en/Contact");
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
		menuPagesTitleList.add("Digital");
		menuPagesTitleList.add("Agile");
		menuPagesTitleList.add("Automation");
		menuPagesTitleList.add("Services");
		menuPagesTitleList.add("Investors");
		menuPagesTitleList.add("Industries");
		menuPagesTitleList.add("Success Stories");
		menuPagesTitleList.add("About");
		menuPagesTitleList.add("Be More");
		menuPagesTitleList.add(ContactPage.getContactTitle());
		return menuPagesTitleList;
	}
}