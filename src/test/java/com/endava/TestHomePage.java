package com.endava;

import org.apache.log4j.Logger;
import com.endava.util.Utils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import com.endava.pages.HomePage;
import com.endava.pages.MenuPage;


/**
 * @author jana.djordjevic@endava.com
 *
 */
public class TestHomePage {

	private HomePage homePage;
	private MenuPage menuPage;
	private static Logger log = Logger.getLogger(TestHomePage.class);

	/**
	 * @author Vladimir Krekic
	 * @param browser web browser defined in testng.xml
	 */
	@BeforeTest
	@Parameters ({"browser"})
	public void setUp(String browser) {
		homePage = Utils.setUpWebBrowser(browser);
		log.info("setUp()");
	}

	/**
	 * Test validates that home page is opened by checking if contact buttons are
	 * visible on the page
	 * @author Vladimir Krekic
	 */
	@Test
	public void testHomePageIsOpened() {
		homePage.open();
		new WebDriverWait(homePage.driver, 5)
				.until(ExpectedConditions.visibilityOfElementLocated(homePage.contactButtons));
		log.info("testHomePageIsOpened()");
	}

	/**
	 * Test validates that home page is opened by checking if contact buttons are
	 * visible on the page and "burger" menu is active
	 * @author Vladimir Krekic
	 */
	@Test
	public void testOpenMenu() {
		homePage.open();
		new WebDriverWait(homePage.driver, 5)
				.until(ExpectedConditions.visibilityOfElementLocated(homePage.contactButtons));
		menuPage = homePage.openMenu();
		new WebDriverWait(menuPage.driver, 5)
				.until(ExpectedConditions.visibilityOfElementLocated(menuPage.navigationList));
		log.info("testOpenMenu()");
	}

	@AfterTest
	public void tearDown() {
		homePage.quit();
		log.info("tearDown()");
	}
}
