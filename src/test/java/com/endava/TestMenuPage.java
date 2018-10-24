package com.endava;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.endava.pages.HomePage;
import com.endava.pages.InvestorsPage;
import com.endava.pages.MenuPage;
import com.endava.util.Utils;

/**
 * @author jelena.corak
 *
 */
public class TestMenuPage {

	private HomePage homePage;
	private MenuPage menuPage;
	private InvestorsPage investorsPage;
	private static Logger log = Logger.getLogger(TestMenuPage.class);

	/**
	 * @author jelena.corak
	 * @param browser
	 *            web browser defined in testng.xml
	 */
	@BeforeMethod
	@Parameters({ "browser" })
	public void setUp(String browser) {
		homePage = Utils.setUpWebBrowser(browser);
		log.info("setUp()");
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
			 * Logo locator on Investors page is different from the logo locator on other pages. 
			 * Logo element on this page has an overlay (info on accepting cookies)
			 * and requires a direct click on the logo element.
			 * Title of this page is incorrect, thus it is skipped in title verification.
			 */
			if (i == 5) {
				homePage.open();
				Utils.webDriverWait(homePage.driver, homePage.getContactButtons());
				homePage.assertPageUrl(homePage.getEndavaURL());
				homePage.assertPageTitle(homePage.getEndavaTitle());
				menuPage = homePage.openMenu();
				Utils.webDriverWait(menuPage.driver, menuPage.getNavigationList());				
				menuPage.scrollIntoView(menuPage.getInvestorsMenuItem());
				investorsPage = menuPage.clickOnInvestors();
				investorsPage.assertPageUrl(menuPage.getMenuPagesUrlList().get(i-1));				
				investorsPage.directClickOnElement(investorsPage.getInvestorsLogo());
				investorsPage.assertPageUrl(homePage.getEndavaURL() + "en");
				log.info("VALIDATION SUCCESSFUL: (INVESTORS) Endava logo is link to home page.");
			} else {				
				homePage.open();
				Utils.webDriverWait(homePage.driver, homePage.getContactButtons());
				homePage.assertPageUrl(homePage.getEndavaURL());
				homePage.assertPageTitle(homePage.getEndavaTitle());
				menuPage = homePage.openMenu();
				Utils.webDriverWait(menuPage.driver, menuPage.getNavigationList());
				menuPage.scrollIntoView(By.cssSelector(cssSelector));
				menuPage.clickOnElement(By.cssSelector(cssSelector));				
				menuPage.assertPageUrl(menuPage.getMenuPagesUrlList().get(i-1));				
				menuPage.assertPageTitle(menuPage.getMenuPagesTitleList().get(i-1));
				menuPage.clickOnElement(menuPage.getLogo());
				menuPage.assertPageUrl(homePage.getEndavaURL() + "en");
				menuPage.assertPageTitle(homePage.getEndavaTitle());
				log.info("VALIDATION SUCCESSFUL: Endava logo is link to home page.");
			}
		}
		log.info("VALIDATION SUCCESSFUL: Endava logo is link to home page in all menu pages.");
	}
	
	@AfterClass
	public void tearDown() {
		menuPage.quit();
		log.info("tearDown()");
	}
}
