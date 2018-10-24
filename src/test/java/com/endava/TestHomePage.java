package com.endava;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.endava.pages.CookiePolicyPage;
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

	@Test(priority = 2)
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
	 * Validates text in cookies policy message. Clicks on "Learn More" and validates that user is taken to Cookie Policy page.
	 * 
	 * @author jelena.corak
	 * 
	 */
	@Test(priority = 5)
	public void testCookiePolicy() {
		homePage.open();
		Utils.webDriverWait(homePage.driver, homePage.getContactButtons());
		homePage.assertPageUrl(homePage.getEndavaURL());
		homePage.assertPageTitle(homePage.getEndavaTitle());		
		homePage.validateCookiesPolicytext();
		homePage.clickOnElement(homePage.getCookiesLearnMore());
		homePage.assertPageUrl(CookiePolicyPage.getCookiePolicyUrl());
		homePage.assertPageTitle(CookiePolicyPage.getCookiePolicyTitle());		
		log.info("VALIDATION SUCCESSFUL! Cookie text is correct and click on Learn More takes to Cookies Policy page.");
	}

	@AfterClass
	public void tearDown() {
		homePage.quit();
		log.info("tearDown()");
	}
}
