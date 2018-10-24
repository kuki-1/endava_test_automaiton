package com.endava;

import com.endava.pages.BasePage;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.endava.pages.HomePage;
import com.endava.pages.MenuPage;
import com.endava.pages.BasePage;
import com.endava.pages.ContactPage;
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
		Assert.assertTrue(BasePage.isTitleCorrect(homePage.driver, homePage.getEndavaTitle()), "Incorrect HomePage Title ");
		log.info("testHomePageIsOpened()");
	}

	@Test(priority = 2, dependsOnMethods = {"testHomePageIsOpened"})
	public void testOpenMenu() {
		homePage.open();
		Utils.webDriverWait(homePage.driver, homePage.getContactButtons());
		homePage.clickOnDownArrow();
		Assert.assertTrue(homePage.isSolutionMenusVisible(), "Solution menus are not visible.");
		menuPage = homePage.openMenu();
		Utils.webDriverWait(menuPage.driver, menuPage.getNavigationList());
		Assert.assertTrue(BasePage.isURLTheSame(menuPage.driver, homePage.getEndavaURL()), "URL is not the same.");
    log.info("testOpenMenu()");
	}
	
	/**
	 * Test validates that click on the phone icon is a link to the Contact page.
	 * 
	 * @author jelena.corak
	 * 
	 */
	@Test(priority = 3)
	public void testPhoneIconLink() {
		homePage.open();
		Utils.webDriverWait(homePage.driver, homePage.getContactButtons());
		Utils.directClickOnElement(homePage.driver, homePage.getPhoneIcon());
		homePage.assertUrl(homePage.driver, ContactPage.getContactUrl());
		homePage.assertTitle(homePage.driver, ContactPage.getContactTitle());
		log.info("testPhoneIconLink(): VALIDATION SUCCESSFUL! Phone icon link is a link to Contacts page.");
	}

	@AfterClass
	public void tearDown() {
		homePage.quit();
		log.info("tearDown()");
	}
}
