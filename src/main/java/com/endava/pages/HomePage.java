package com.endava.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author jana.djordjevic@endava.com
 *
 */
public class HomePage extends BasePage {

	private static final String ENDAVA_URL = "https://www.endava.com/";
	private static final String ENDAVA_TITLE = "Endava";
	private By contactButtons = By.id("contact-buttons");
	private By burgerMenu = By.id("menu-toggle");
	private By solutionMenus = By.className("proposition-section");
	private By centerScroll = By.className("fe_downarrow");

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

	public String getEndavaURL() {
		return ENDAVA_URL;
	}

	public String getEndavaTitle() {
		return ENDAVA_TITLE;
	}

	public By getContactButtons() {
		return contactButtons;
	}

}
