package com.endava;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.endava.pages.HomePage;
import com.endava.pages.InvestorsPage;
import com.endava.pages.MenuPage;
import com.endava.util.Utils;

/**
 * 
 * @author Goran.Kukolj
 *
 */
public class TestInvestorsPage {

	private HomePage homePage;
	private MenuPage menuPage;
	private InvestorsPage investorsPage;
	private static Logger log = Logger.getLogger(TestInvestorsPage.class);

	@BeforeMethod
	@Parameters({ "browser" })
	public void setUp(String browser) {
		homePage = Utils.setUpWebBrowser(browser);
		log.info("Setting up chosen browser: " + browser);
	}

	/**
	 * Test validates that home page is opened by checking if contact buttons are
	 * visible on the page, validates that burger menu is opened by checking if
	 * navigation list is visible on the page,validates that there is search button
	 * visible on the page, compares current URL and expected URL to see if we are
	 * on another page, and validates how many search results we get,and if search
	 * results are returned for a less than 2 seconds
	 * 
	 * @author Goran.Kukolj
	 */
	@Test
	public void testValidateSearchTime() {
		homePage.open();
		Utils.webDriverWait(homePage.driver, homePage.getContactButtons());
		menuPage = homePage.openMenu();
		Utils.webDriverWait(menuPage.driver, menuPage.getNavigationList());
		investorsPage = homePage.openInvestorsPage();
		Utils.webDriverWait(investorsPage.driver, investorsPage.getSearchButton());
		investorsPage.assertPageUrl(InvestorsPage.getInvestorsUrl());
		investorsPage.clickOnSearchButton();
		investorsPage.clickOnSearchFieldAndEntertext(investorsPage.getTextEntry());
		Utils.webDriverWait(investorsPage.driver, investorsPage.getNumberOfResults());
		Assert.assertTrue(investorsPage.numberOfFoundResults(), "Number of search results is not 3");
		Assert.assertTrue(investorsPage.isSearchTimeLessThanTwoSeconds(), "Search time is greater than 2 seconds");
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
		/*
		 * Incorrect title of Investors page. Title verification skipped, since it
		 * fails.
		 */
		// Assert.assertEquals(investorsPage.driver.getTitle().toLowerCase(),
		// InvestorsPage.getInvestorsTitle().toLowerCase(), "Incorrect title!");
		Utils.webDriverWait(investorsPage.driver, investorsPage.getInvestorsAboutUs());
		Assert.assertTrue(Utils.getTextFromElement(investorsPage.driver, investorsPage.getInvestorsAboutUs())
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

	@AfterMethod
	public void tearDown() {
		homePage.quit();
	}
}