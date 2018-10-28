package com.endava;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.endava.pages.AboutPage;
import com.endava.pages.HomePage;
import com.endava.pages.MenuPage;
import com.endava.util.Utils;

/**
 * @author Vladimir Krekic
 */

class TestAboutPage {

	private AboutPage aboutPage;
	private HomePage homePage;
	private MenuPage menuPage;
	private static Logger log = Logger.getLogger(AboutPage.class);

	@BeforeTest
	@Parameters({ "browser" })
	public void setUp(String browser) {
		homePage = Utils.setUpWebBrowser(browser);
		log.info("setUp()");
	}

	/**
	 * @author Vladimir Krekic Opens to endava site and validate new url, finds "WE DELIVER GLOBAL TRANSFORMATION"
	 *         section and validates all of 23 endava locations (both names of the cities and addresses) (e.g. Belgrade
	 *         9đ, Milutina Milankovica St.)
	 */
	@Test
	public void testAllLocations() {
		homePage.open();
		menuPage = homePage.openMenu();
		Utils.webDriverWait(menuPage.driver, menuPage.getNavigationList());
		aboutPage = homePage.openAboutPage();
		Assert.assertEquals(aboutPage.driver.getCurrentUrl(), aboutPage.getAboutUrl(), "Incorrect AboutPage Url");
		aboutPage.assertPageTitle(aboutPage.getAboutTitle());
		Assert.assertTrue(aboutPage.checkAddresses(aboutPage.getAllLocations()), "Locations do not mach");
		log.info("testAllLocations() - Test passed");
	}

	@AfterTest
	public void tearDown() {
		homePage.quit();
		log.info("tearDown()");
	}
}
