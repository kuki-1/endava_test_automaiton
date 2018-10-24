package com.endava;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.endava.pages.HomePage;
import com.endava.pages.InvestorsPage;
import com.endava.pages.MenuPage;
import com.endava.util.Utils;

/**
 * @author jelena.corak
 */

public class TestInvestorsPage {

	private HomePage homePage;
	private MenuPage menuPage;
	private InvestorsPage investorsPage;
	private static Logger log = Logger.getLogger(TestInvestorsPage.class);

	@BeforeTest
	@Parameters({ "browser" })
	public void setUp(String browser) {
		homePage = Utils.setUpWebBrowser(browser);
		log.info("setUp()");
	}

	/**
	 * @author jelena.corak
	 *
	 *         Test validates that the string "ABOUT US" is visible on the "INVESTORS" page in the menu.
	 */
	@Test
	public void testAboutUsVisibility() {
		homePage.open();
		homePage.assertPageUrl(homePage.getEndavaURL());
		homePage.assertPageTitle(homePage.getEndavaTitle());		
		Utils.webDriverWait(homePage.driver, homePage.getContactButtons());
		menuPage = homePage.openMenu();
		Utils.webDriverWait(menuPage.driver, menuPage.getNavigationList());
		investorsPage = menuPage.clickOnInvestors();
		investorsPage.assertPageUrl(InvestorsPage.getInvestorsUrl());		
		investorsPage.assertPageTitle(InvestorsPage.getInvestorsTitle());
		Utils.webDriverWait(investorsPage.driver, investorsPage.getInvestorsAboutUs());
		Assert.assertTrue(investorsPage.getTextFromElement(investorsPage.getInvestorsAboutUs()).contains("ABOUT US"), "Text \"ABOUT US\" not found!");
		log.info("testAboutUsVisibility() : VALIDATION SUCCESSFUL!");
	}
	
	@AfterMethod
	public void ifFailed(ITestResult testResult) {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			try {
				Utils.takeScreenShot(investorsPage.driver, testResult.getMethod().getMethodName());
			} catch (Exception e) {
				log.error("Screenshot failed.", e);
			}
		}
	}
	
	@AfterTest
	public void tearDown() {
		homePage.quit();
	}
}
