package com.endava;

import org.apache.log4j.Logger;
import org.testng.Assert;
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
	 * @author jelena.corak
	 *
	 *         Test validates that the string "ABOUT US" is visible on the
	 *         "INVESTORS" page in the menu.
	 */
	@Test
	public void testAboutUsVisibility() {
		homePage.open();
		Assert.assertEquals(homePage.driver.getCurrentUrl().toLowerCase(), homePage.getEndavaURL().toLowerCase(),
				"Incorrect URL!");
		Assert.assertEquals(homePage.driver.getTitle().toLowerCase(), homePage.getEndavaTitle().toLowerCase(),
				"Incorrect title!");
		Utils.webDriverWait(homePage.driver, homePage.getContactButtons());
		menuPage = homePage.openMenu();
		Utils.webDriverWait(menuPage.driver, menuPage.getNavigationList());
		menuPage.clickOnInvestors();
		Assert.assertEquals(menuPage.driver.getCurrentUrl(), "https://investors.endava.com/home/default.aspx",
				"Incorrect URL for the INVESTORS page!");		
		Utils.webDriverWait(menuPage.driver, menuPage.getInvestorsAboutUs());
		Assert.assertTrue(menuPage.getTextFromElement(menuPage.getInvestorsAboutUs()).contains("ABOUT US"),
				"Text \"ABOUT US\" not found!");
		log.info("testAboutUsVisibility() : VALIDATION SUCCESSFUL!");
	}

	@AfterTest
	public void tearDown() {
		menuPage.quit();
		log.info("tearDown()");
	}
}
