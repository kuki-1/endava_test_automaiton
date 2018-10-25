package com.endava;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.endava.pages.AgilePage;
import com.endava.pages.HomePage;
import com.endava.pages.MenuPage;
import com.endava.util.Utils;

/**
 * @author Vladimir Krekic
 */

class TestAgilePage {

	private HomePage homePage;
	private MenuPage menuPage;
	private AgilePage agilePage;
	private static Logger log = Logger.getLogger(TestAgilePage.class);

	@BeforeTest
	@Parameters({ "browser" })
	public void setUp(String browser) {
		homePage = Utils.setUpWebBrowser(browser);
		log.info("setUp()");
	}

	/**
	 * @author Vladimir Krekic Test validates AGILE menu item is active in DIGITAL -
	 *         AGILE - AUTOMATION menu
	 */
	@Test(priority = 1)
	public void testAgileItemActiveInDAAMenu() {
		homePage.open();
		menuPage = homePage.openMenu();
		Utils.webDriverWait(menuPage.driver, menuPage.getNavigationList());
		agilePage = homePage.openAgilePage();

		agilePage.assertPageUrl(agilePage.getAgileUrl());
		agilePage.assertPageTitle(agilePage.getAgileTitle());
		Assert.assertEquals(agilePage.driver.findElement(agilePage.getAgileOnRibbonMenu()).getAttribute("class"),
				"active");
		log.debug(
				"testAgileItemActiveInDAAMenu() - Test passed - AGILE menu item is active in DIGITAL - AGILE - AUTOMATION menu");
	}

	/**
	 * Test checks autofill with linkedin button, validates new window pops up, and
	 * validates error messages when invalid email is input
	 * 
	 * @author Goran.Kukolj
	 */
	@Test(priority = 2)
	public void testAutofillWithLinkedin() {
		homePage.open();
		Utils.webDriverWait(homePage.driver, homePage.getContactButtons());
		menuPage = homePage.openMenu();
		Utils.webDriverWait(menuPage.driver, menuPage.getNavigationList());
		agilePage = homePage.openAgilePage();
		agilePage.assertPageTitle(agilePage.getAgileTitle());
		agilePage.assertPageUrl(agilePage.getAgileUrl());
		agilePage.scrollToAutofillWithLinkedinButton();
		agilePage.clickOnAutofillWithLinkedinButton();
		Utils.switchControlToNewWindow(agilePage.driver);
		Utils.webDriverWait(agilePage.driver, agilePage.getSignInToLinkedinMessage());
		Assert.assertTrue(agilePage.validateString(agilePage.driver, agilePage.getSignInToLinkedinMessage(),
				agilePage.getSignInToLinkedin()), "Strings for sign in are not the same");
		agilePage.assertPageTitle(agilePage.getPopUpWindowTitle());
		agilePage.enterEmailAddress();
		agilePage.clickOnAllowAccessButton();
		Assert.assertTrue(agilePage.validateString(agilePage.driver, agilePage.getEnterValidEmailErrorMessage(),
				agilePage.getEnterValidEmail()), "Strings for email are not the same");
		Assert.assertTrue(agilePage.validateString(agilePage.driver, agilePage.getEnterPasswordErrorMessage(),
				agilePage.getEnterPassword()), "Strings for password are not the same");
		Assert.assertTrue(agilePage.validateString(agilePage.driver, agilePage.getCorrectMarkedFieldsErrorMessage(),
				agilePage.getCorrectMarkedFields()), "Strings for marked fields are not the same");
		agilePage.assertPageUrl(agilePage.getPopUpWindowSubmitUrl());
		agilePage.clickOnCancelButton();
		Utils.switchControlToNewWindow(agilePage.driver);
		agilePage.assertPageUrl(agilePage.getAgileUrl());
	}

	@AfterClass
	public void tearDown() {
		homePage.quit();
	}
}