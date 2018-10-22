package com.endava.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 
 * @author Goran.Kukolj
 *
 */
public class AutomationPage extends BasePage {

	private By automationPageLink = By.xpath("//*[@id=\"secondary-nav\"]/ul/li[3]");
	private static Logger log = Logger.getLogger(AutomationPage.class);

	public AutomationPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * @author Goran.Kukolj
	 * @return true or false depending on status of link
	 */
	public boolean isAutomationPageLinkActive() {
		WebElement element = driver.findElement(this.automationPageLink);
		if (element.isDisplayed()) {
			log.debug("Checks if automation page link is present and active");
			return element.getAttribute("class").contains("active");
		} else
			log.debug("Automation link element is not found");
		return false;
	}

	/**
	 * @author Goran.Kukolj
	 * @return web link element
	 */
	public By getAutomationPageLink() {
		return automationPageLink;
	}
}
