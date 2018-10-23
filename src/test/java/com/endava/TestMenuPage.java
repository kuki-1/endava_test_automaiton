package com.endava;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.endava.pages.HomePage;
import com.endava.pages.MenuPage;
import com.endava.util.Utils;

/**
 * @author jelena.corak
 *
 */
public class TestMenuPage {

	private HomePage homePage;
	private MenuPage menuPage;
	private static Logger log = Logger.getLogger(TestMenuPage.class);

	/**
	 * @author jelena.corak
	 * @param browser web browser defined in testng.xml
	 */
	@BeforeMethod
	@Parameters({ "browser" })
	public void setUp(String browser) {
		homePage = Utils.setUpWebBrowser(browser);
		log.info("setUp()");
	}

	/**
	 * @author jelena.corak
	 *
	 *         Test validates that the string "ABOUT US" is visible on the
	 *         "INVESTORS" page in the menu.
	 */
	@Test
	public void testAboutUsVisibility() {
		homePage.open();
		Utils.assertUrl(homePage.driver, homePage.getEndavaURL());
		Utils.assertTitle(homePage.driver, homePage.getEndavaTitle());
		Utils.webDriverWait(homePage.driver, homePage.getContactButtons());
		menuPage = homePage.openMenu();
		Utils.webDriverWait(menuPage.driver, menuPage.getNavigationList());
		menuPage.clickOnInvestors();
		Utils.assertUrl(menuPage.driver, "https://investors.endava.com/home/default.aspx");
		Utils.webDriverWait(menuPage.driver, menuPage.getInvestorsAboutUs());
		Assert.assertTrue(menuPage.getTextFromElement(menuPage.getInvestorsAboutUs()).contains("ABOUT US"),
				"Text \"ABOUT US\" not found!");
		log.info("testAboutUsVisibility() : VALIDATION SUCCESSFUL!");
	}	

	/**
	 * Test validates that Endava logo is a link to the home page in all Menu pages.
	 * 
	 * @author jelena.corak
	 * 
	 */
	@Test
	public void testIsEndavaLogoLinkToHomePageInAllMenuPages() {

		for (int i = 1; i <= 10; i++) {
			String cssSelector = String.format(".navList > li:nth-child(%d) > a:nth-child(1)", i);
			/*
			 * Logo locator on Investors page is different from the logo locator on other
			 * pages. 
			 * Logo element on this page has an overlay (info on accepting cookies)
			 * and requires a direct click on the logo element.
			 * Title of this page is incorrect, thus it is skipped in title verification.
			 */
			if (i == 5) {
				homePage.open();
				Utils.webDriverWait(homePage.driver, homePage.getContactButtons());
				Utils.assertUrl(homePage.driver, homePage.getEndavaURL());
				Utils.assertTitle(homePage.driver, homePage.getEndavaTitle());
				menuPage = homePage.openMenu();
				Utils.webDriverWait(menuPage.driver, menuPage.getNavigationList());				
				Utils.scrollIntoView(menuPage.driver, menuPage.getInvestorsMenuItem());
				menuPage.clickOnInvestors();
				Utils.assertUrl(menuPage.driver, menuPage.getMenuPagesUrlList().get(i-1));				
				Utils.directClickOnElement(menuPage.driver, menuPage.getInvestorsLogo());
				Utils.assertUrl(menuPage.driver, homePage.getEndavaURL() + "en");
				log.info("VALIDATION SUCCESSFUL: (INVESTORS) Endava logo is link to home page.");
			} else {				
				homePage.open();
				Utils.webDriverWait(homePage.driver, homePage.getContactButtons());
				Utils.assertUrl(homePage.driver, homePage.getEndavaURL());
				Utils.assertTitle(homePage.driver, homePage.getEndavaTitle());
				menuPage = homePage.openMenu();
				Utils.webDriverWait(menuPage.driver, menuPage.getNavigationList());
				Utils.scrollIntoView(menuPage.driver, By.cssSelector(cssSelector));
				menuPage.clickOnMenuEvent(By.cssSelector(cssSelector));
				Utils.assertUrl(menuPage.driver, menuPage.getMenuPagesUrlList().get(i-1));
				Utils.assertTitle(homePage.driver, menuPage.getMenuPagesTitleList().get(i-1));
				menuPage.clickOnMenuEvent(menuPage.getLogo());
				Utils.assertUrl(menuPage.driver, homePage.getEndavaURL() + "en");
				Utils.assertTitle(homePage.driver, homePage.getEndavaTitle());
				log.info("VALIDATION SUCCESSFUL: Endava logo is link to home page.");
			}
		}
		log.info("VALIDATION SUCCESSFUL: Endava logo is link to home page in all menu pages.");
	}

	@AfterMethod
	@AfterTest
	public void tearDown() {
		menuPage.quit();
		log.info("tearDown()");
	}
}
