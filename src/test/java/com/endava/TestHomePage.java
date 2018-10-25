package com.endava;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.endava.pages.ContactPage;
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
		homePage.assertPageUrl(homePage.getEndavaURL());
		homePage.assertPageTitle(homePage.getEndavaTitle());
		log.info("testHomePageIsOpened()");
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
		homePage.assertPageUrl(homePage.getEndavaURL());
		homePage.assertPageTitle(homePage.getEndavaTitle());
		homePage.clickOnShareIcon();
		Assert.assertTrue(
				homePage.validateString(homePage.driver, homePage.getShareMenu(), homePage.getShareMenuText()),
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
		menuPage.assertPageUrl(homePage.getEndavaURL());
		log.info("testOpenMenu()");
	}

	/**
	 * Test validates that click on the phone icon is a link to the Contact page.
	 * 
	 * @author jelena.corak
	 * 
	 */
	@Test(priority = 4)
	public void testPhoneIconLink() {
		homePage.open();
		Utils.webDriverWait(homePage.driver, homePage.getContactButtons());
		homePage.assertPageTitle(homePage.getEndavaTitle());
		homePage.assertPageUrl(homePage.getEndavaURL());
		homePage.directClickOnElement(homePage.getPhoneIcon());
		homePage.assertPageUrl(ContactPage.getContactUrl());
		homePage.assertPageTitle(ContactPage.getContactTitle());
		log.info("testPhoneIconLink(): VALIDATION SUCCESSFUL! Phone icon link is a link to Contacts page.");
	}

	@AfterClass
	public void tearDown() {
		homePage.quit();
	}
}
