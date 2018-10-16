package com.endava.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



/**
 * @author jana.djordjevic@endava.com
 *
 */
public class HomePage extends BasePage {

	private final String ENDAVA_URL = "http://www.endava.com";
	private static Logger log = Logger.getLogger(HomePage.class);

	public By contactButtons = By.id("contact-buttons");
	public By burgerMenu = By.id("menu-toggle");

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public void open() {
		driver.get(ENDAVA_URL);
		driver.manage().window().maximize();
		log.debug("open()");
	}

	public MenuPage openMenu() {
		driver.findElement(this.burgerMenu).click();
		log.debug("openMenu()");
		return new MenuPage(driver);
	}
}
