package com.endava;

import org.apache.log4j.Logger;
import com.endava.util.Utils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.endava.pages.BasePage;
import com.endava.pages.HomePage;
import com.endava.pages.MenuPage;


/**
 * @author jana.djordjevic@endava.com
 *
 */
public class TestHomePage {

	private HomePage homePage;
	private MenuPage menuPage;
	private BasePage basePage;
	private static Logger log = Logger.getLogger(TestHomePage.class);

	/**
	 * @author Vladimir Krekic
	 * @param browser web browser defined in testng.xml
	 */
	@BeforeTest
	@Parameters({"browser"})
	public void setUp(String browser) {
		homePage = Utils.setUpWebBrowser(browser);
		basePage = Utils.setUpWebBrowser(browser);
		log.info("setUp()");
	}

	/**
	 * Test validates that home page is opened by checking if contact buttons are
	 * visible on the page, compares current URL and expected URL to see if they
     * match, and validates home page title
	 * @author Vladimir Krekic
	 */
	@Test
	public void testHomePageIsOpened() {
		homePage.open();
		Assert.assertEquals(homePage.driver.getCurrentUrl(), homePage.getEndavaURL());
		Assert.assertTrue(basePage.isTitleCorrect(homePage.driver, homePage.getEndavaTitle()));
		new WebDriverWait(homePage.driver, 5)
				.until(ExpectedConditions.visibilityOfElementLocated(homePage.getContactButtons()));
		log.info("testHomePageIsOpened()");
	}

	/**
   * @author Vladimir Krekic
	 * Test validates that home page is opened by checking if contact buttons are
	 * visible on the page,validates that solution menus are visible on home page,
	 * and validates that burger menu is opened by checking if navigation list is
	 * visible on the page
	 */
	@Test (dependsOnMethods = {"testHomePageIsOpened"})
	public void testOpenMenu() {
		homePage.open();
		new WebDriverWait(homePage.driver, 5)
				.until(ExpectedConditions.visibilityOfElementLocated(homePage.getContactButtons()));
		homePage.clickOnDownArrow();
		Assert.assertTrue(homePage.isSolutionMenusVisible());
		menuPage = homePage.openMenu();
		new WebDriverWait(menuPage.driver, 5)
				.until(ExpectedConditions.visibilityOfElementLocated(menuPage.getNavigationList()));
		log.info("testOpenMenu()");
	}

	@AfterTest
	public void tearDown() {
		homePage.quit();
		basePage.quit();
		log.info("tearDown()");
	}
}
