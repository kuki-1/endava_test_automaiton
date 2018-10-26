package com.endava;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import com.endava.pages.HomePage;
import com.endava.pages.MenuPage;
import com.endava.util.Utils;

/**
 * @author jelena.corak
 */
public class TestMenuPage {

	private HomePage homePage;
	private MenuPage menuPage;
	private static Logger log = Logger.getLogger(TestMenuPage.class);

	/**
	 * @param browser web browser defined in testng.xml
	 * @author jelena.corak
	 */
	@BeforeMethod
	@Parameters({ "browser" })
	public void setUp(String browser) {
		homePage = Utils.setUpWebBrowser(browser);
		log.info("setUp()");
	}

	@AfterClass
	public void tearDown() {
		menuPage.quit();
		log.info("tearDown()");
	}
}
