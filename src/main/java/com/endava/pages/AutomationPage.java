package com.endava.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Goran.Kukolj
 */
public class AutomationPage extends BasePage {

	private static final String ENDAVA_AUTOMATION_URL = "https://www.endava.com/en/Automation";
	private static final String ENDAVA_AUTOMATION_TITLE = "Automation";
	private By automationPageLink = By.xpath("//*[@id=\"secondary-nav\"]/ul/li[3]");
	private static Logger log = Logger.getLogger(AutomationPage.class);

	public AutomationPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * @return true or false depending on status of link
	 * @author Goran.Kukolj
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
	 * @return web link element
	 * @author Goran.Kukolj
	 */
	public By getAutomationPageLink() {
		return automationPageLink;
	}

	/**
	 * @return String endava automation url
	 * @author Goran.Kukolj
	 */
	public static String getEndavaAutomationUrl() {
		return ENDAVA_AUTOMATION_URL;
	}

	/**
	 * @return String endava automation title
	 * @author Goran.Kukolj
	 */
	public static String getEndavaAutomationTitle() {
		return ENDAVA_AUTOMATION_TITLE;
	}
}
