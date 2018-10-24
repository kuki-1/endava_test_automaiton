package com.endava;

import com.endava.pages.AgilePage;
import com.endava.pages.HomePage;
import com.endava.pages.MenuPage;
import com.endava.util.Utils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * @author Vladimir Krekic
 */

class TestAgilePage {

    private HomePage homePage;
    private MenuPage menuPage;
    private AgilePage agilePage;
    private static Logger log = Logger.getLogger(TestAgilePage.class);

    @BeforeTest
    @Parameters({"browser"})
    public void setUp(String browser) {
        homePage = Utils.setUpWebBrowser(browser);
        log.info("setUp()");
    }

    /**
     * @author Vladimir Krekic
     * Test validates AGILE menu item is active in DIGITAL - AGILE - AUTOMATION menu
     */
    @Test
    public void testAgileItemActiveInDAAMenu() {
        homePage.open();
        menuPage = homePage.openMenu();
        Utils.webDriverWait(menuPage.driver, menuPage.getNavigationList());
        agilePage = homePage.openAgilePage();
        Assert.assertEquals(agilePage.driver.getCurrentUrl(), AgilePage.getAgileUrl(), "Incorrect AgilePage Url");
        agilePage.assertPageTitle(AgilePage.getAgileTitle());
        Assert.assertEquals(agilePage.driver.findElement(agilePage.getAgileOnRibbonMenu()).getAttribute("class"), "active");
        log.debug("testAgileItemActiveInDAAMenu() - Test passed - AGILE menu item is active in DIGITAL - AGILE - AUTOMATION menu");
    }
    
    @AfterMethod
	public void ifFailed(ITestResult testResult) {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			try {
				Utils.takeScreenShot(agilePage.driver, testResult.getMethod().getMethodName());
			} catch (Exception e) {
				log.error("Screenshot failed.", e);
			}
		}
	}
        
    @AfterTest
    public void tearDown() {
        homePage.quit();
    }
}
