package com.endava.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author jana.djordjevic@endava.com
 *
 */
public class HomePage extends BasePage {

	private final String ENDAVA_URL = "https://www.endava.com/";
	private final String ENDAVA_TITLE = "Endava";
	public By contactButtons = By.id("contact-buttons");
	public By burgerMenu = By.id("menu-toggle");
	public By solutionMenus = By.className("proposition-section");
	public By centerScroll = By.className("fe_downarrow");

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public void open() {
		driver.get(ENDAVA_URL);
		driver.manage().window().maximize();
	}

	public MenuPage openMenu() {
		driver.findElement(this.burgerMenu).click();
		return new MenuPage(driver);
	}

	public void scrollDown() {
		driver.findElement(this.centerScroll).click();
	}

	public boolean isSolutionMenusVisible() {
		return driver.findElement(solutionMenus).isDisplayed();
	}

	public boolean isTitleCorrect(String title) {
		return driver.getTitle().equalsIgnoreCase(title);
	}

	public String getENDAVA_URL() {
		return ENDAVA_URL;
	}

	public String getENDAVA_TITLE() {
		return ENDAVA_TITLE;
	}

}
