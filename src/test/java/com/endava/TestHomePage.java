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
	 * Test validates that links of social media icons are correct.
	 * 
	 * @author jelena.corak
	 * 
	 */
	@Test(priority = 4)
	public void testSocialMediaIconsLinks() {
		homePage.open();
		Utils.webDriverWait(homePage.driver, homePage.getSocialMediaIcons());
		Assert.assertEquals(5, homePage.getSocialMediaIconList().size(), "Not all social media icons are visible on the home page.");
		log.info("testSocialMediaIconCount(): VALIDATION SUCCESSFUL! All icons are visible.");
		for (int i = 0; i < homePage.getSocialMediaIconList().size(); i++)
			BasePage.assertElementLink(homePage.getSocialMediaIconList().get(i), homePage.getListOfSocialMediaUrls().get(i));
		log.info("testSocialMediaIconsLinks(): VALIDATION SUCCESSFUL! All icons have correct links.");
	}

	@AfterClass
	public void tearDown() {
		homePage.quit();
		log.info("tearDown()");
	}
}
