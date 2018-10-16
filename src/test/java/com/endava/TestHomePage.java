package com.endava;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.endava.pages.BasePage;
import com.endava.pages.HomePage;
import com.endava.pages.MenuPage;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author jana.djordjevic@endava.com
 *
 */
public class TestHomePage {

	private HomePage homePage;
	private MenuPage menuPage;
	private BasePage basePage;

	@BeforeTest
	public void setUp() {
		WebDriverManager.chromedriver().setup();
	}

	/**
	 * Test validates that home page is opened by checking if contact buttons are
	 * visible on the page, compares current URL and expected URL to see if they
	 * match, and validates home page title
	 * 
	 * @author Goran.Kukolj
	 */
	@Test
	public void testHomePageIsOpened() {
		homePage = new HomePage(new ChromeDriver());
		basePage = homePage;
		homePage.open();
		Assert.assertEquals(homePage.driver.getCurrentUrl(), homePage.getEndavaURL());
		Assert.assertTrue(basePage.isTitleCorrect(homePage.driver, homePage.getEndavaTitle()));
		new WebDriverWait(homePage.driver, 5)
				.until(ExpectedConditions.visibilityOfElementLocated(homePage.getContactButtons()));

	}

	/**
	 * 
	 * Test validates that home page is opened by checking if contact buttons are
	 * visible on the page,validates that solution menus are visible on home page,
	 * and validates that burger menu is opened by checking if navigation list is
	 * visible on the page
	 * 
	 * @author Goran.Kukolj
	 */
	@Test
	public void testOpenMenu() {
		homePage = new HomePage(new ChromeDriver());
		homePage.open();
		new WebDriverWait(homePage.driver, 5)
				.until(ExpectedConditions.visibilityOfElementLocated(homePage.getContactButtons()));
		homePage.clickOnDownArrow();
		Assert.assertTrue(homePage.isSolutionMenusVisible());
		menuPage = homePage.openMenu();
		new WebDriverWait(menuPage.driver, 5)
				.until(ExpectedConditions.visibilityOfElementLocated(menuPage.getNavigationList()));
	}

	@AfterMethod
	public void tearDown() {
		homePage.quit();
	}

}
