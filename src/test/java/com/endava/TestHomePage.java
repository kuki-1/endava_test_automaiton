package com.endava;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.endava.pages.BasePage;
import com.endava.pages.HomePage;
import com.endava.pages.MenuPage;
import com.endava.util.Utils;

/**
 * @author jana.djordjevic@endava.com
 *
 */
public class TestHomePage {

	private HomePage homePage;
	private MenuPage menuPage;
	private static Logger log = Logger.getLogger(TestHomePage.class);

	/**
	 * @author Vladimir Krekic
	 * @param browser web browser defined in testng.xml
	 */
	@BeforeTest
	@Parameters({ "browser" })
	public void setUp(String browser) {
		homePage = Utils.setUpWebBrowser(browser);
		log.info("Sets up web browser");
	}

	/**
	 * Test validates that home page is opened by checking if contact buttons are
	 * visible on the page, compares current URL and expected URL to see if they
	 * match, and validates home page title
	 * 
	 * @author Vladimir Krekic
	 */
	@Test
	public void testHomePageIsOpened() {
		homePage.open();
		Utils.webDriverWait(homePage.driver, homePage.getContactButtons());
		Assert.assertTrue(BasePage.isURLTheSame(homePage.driver, homePage.getEndavaURL()));
		Assert.assertTrue(BasePage.isTitleCorrect(homePage.driver, homePage.getEndavaTitle()));
		log.info("Tests if home page is opened");
	}

	/**
	 * Test validates that home page is opened by checking if contact buttons are
	 * visible on the page,validates that solution menus are visible on home page,
	 * and validates that burger menu is opened by checking if navigation list is
	 * visible on the page
	 * 
	 * @author Vladimir Krekic
	 */
	@Test
	public void testOpenMenu() {
		homePage.open();
		Utils.webDriverWait(homePage.driver, homePage.getContactButtons());
		homePage.clickOnDownArrow();
		Assert.assertTrue(homePage.isSolutionMenusVisible());
		menuPage = homePage.openMenu();
		Utils.webDriverWait(menuPage.driver, menuPage.getNavigationList());
		Assert.assertTrue(BasePage.isURLTheSame(menuPage.driver, homePage.getEndavaURL()));
		log.info("Tests if home page is opened,and opens burger menu");
	}

	@AfterClass
	public void tearDown() {
		homePage.quit();
		log.info("Closes the browsers");
	}
}
