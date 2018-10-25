package com.endava;

import org.apache.log4j.Logger;
import org.testng.Assert;
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
		log.info("Setting up chosen browser: " + browser);
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
		investorsPage = menuPage.clickOnInvestors();
		Assert.assertEquals(investorsPage.driver.getCurrentUrl().toLowerCase(),
				InvestorsPage.getInvestorsUrl().toLowerCase(), "Incorrect URL!");
		Utils.webDriverWait(investorsPage.driver, investorsPage.getInvestorsAboutUs());
		Assert.assertTrue(InvestorsPage.getTextFromElement(investorsPage.driver, investorsPage.getInvestorsAboutUs())
				.contains("ABOUT US"), "Text \"ABOUT US\" not found!");
		log.info("testAboutUsVisibility() : VALIDATION SUCCESSFUL!");
	}

	/**
	 * @author Vladimir Krekic From burger menu chooses Investors, validates url has
	 *         been changed, clicks on the Search, on a search box types "blahblah",
	 *         clicks on a "magnifying glass" and validates search result is "No
	 *         results found."
	 */
	@Test
	public void testInvestorsSearch() {
		homePage.open();
		menuPage = homePage.openMenu();
		Utils.webDriverWait(menuPage.driver, menuPage.getNavigationList());
		investorsPage = homePage.openInvestorsPage();
		Assert.assertEquals(InvestorsPage.getInvestorsUrl(), investorsPage.driver.getCurrentUrl(),
				"InvestorsPage Url does not match");
		investorsPage.selectElement(investorsPage.getSearch());
		investorsPage.fillSearchBox("blahblah");
		investorsPage.selectElement(investorsPage.getSubmitButton());
		Assert.assertEquals(
				investorsPage.getSearchResult(investorsPage.driver.findElement(investorsPage.getSearchResultElement())),
				investorsPage.getSearchResult(), "Wrong search result");
		log.info("testInvestorsSearch() - test passed");
	}

	@AfterTest
	public void tearDown() {
		homePage.quit();
		log.info("Closing browser");
	}
}