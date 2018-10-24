package com.endava;

import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
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

    @AfterMethod
	public void ifFailed(ITestResult testResult) {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			try {
				Utils.takeScreenShot(menuPage.driver, testResult.getMethod().getMethodName());
			} catch (Exception e) {
				log.error("Screenshot failed.", e);
			}
		}
	}
	
	@AfterClass
	public void tearDown() {
		menuPage.quit();
		log.info("tearDown()");
	}
}
