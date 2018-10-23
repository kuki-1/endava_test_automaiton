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

	@BeforeTest
	@Parameters({ "browser" })
	public void setUp(String browser) {
		homePage = Utils.setUpWebBrowser(browser);
		log.info("setUp()");
	}

	@Test(priority = 1)
	public void testHomePageIsOpened() {
		homePage.open();
		Utils.webDriverWait(homePage.driver, homePage.getContactButtons());
		Assert.assertTrue(BasePage.isURLTheSame(homePage.driver, homePage.getEndavaURL()), "Incorrect HomePage Url");
		Assert.assertTrue(BasePage.isTitleCorrect(homePage.driver, homePage.getEndavaTitle()),
				"Incorrect HomePage Title ");
	}

	/**
	 * Tests the share icon and validates that 4 share options are present
	 * 
	 * @author Goran.Kukolj
	 */
	@Test(priority = 2)
	public void testShareIcon() {
		homePage.open();
		Utils.webDriverWait(homePage.driver, homePage.getContactButtons());
		Assert.assertTrue(BasePage.isURLTheSame(homePage.driver, homePage.getEndavaURL()), "Incorrect HomePage Url");
		Assert.assertTrue(BasePage.isTitleCorrect(homePage.driver, homePage.getEndavaTitle()),
				"Incorrect HomePage Title ");
		homePage.clickOnShareIcon();
		Assert.assertTrue(Utils.validateString(homePage.driver, homePage.getShareMenu(), homePage.getShareMenuText()),
				"Share menu is not opened");
		Assert.assertTrue(homePage.areShareOptionsPresent(), "Share options are present");
		homePage.clickOnShareIcon();
	}

	@Test(priority = 3)
	public void testOpenMenu() {
		homePage.open();
		Utils.webDriverWait(homePage.driver, homePage.getContactButtons());
		homePage.clickOnDownArrow();
		Assert.assertTrue(homePage.isSolutionMenusVisible(), "Solution menus are not visible.");
		menuPage = homePage.openMenu();
		Utils.webDriverWait(menuPage.driver, menuPage.getNavigationList());
		Assert.assertTrue(BasePage.isURLTheSame(menuPage.driver, homePage.getEndavaURL()), "URL is not the same.");
	}

	@AfterClass
	public void tearDown() {
		homePage.quit();
	}
}
