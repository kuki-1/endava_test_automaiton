package com.endava;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.endava.pages.AutomationPage;
import com.endava.pages.HomePage;
import com.endava.pages.MenuPage;
import com.endava.util.Utils;

/**
 * @author Goran.Kukolj
 */
public class TestAutomationPage {

    private HomePage homePage;
    private MenuPage menuPage;
    private AutomationPage automationPage;
    private static Logger log = Logger.getLogger(TestAutomationPage.class);

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(String browser) {
        homePage = Utils.setUpWebBrowser(browser);
        log.info("Sets up web browser");
    }

<<<<<<< HEAD
    /**
     * Test validates that automation link on automation page is active, and checks
     * if URL has changed
     *
     * @author Goran.Kukolj
     */
    @Test
    public void testAutomationMenuIsActive() {
        homePage.open();
        Utils.webDriverWait(homePage.driver, homePage.getContactButtons());
        menuPage = homePage.openMenu();
        Utils.webDriverWait(menuPage.driver, menuPage.getNavigationList());
        automationPage = menuPage.openAutomationPage();
        Utils.webDriverWait(automationPage.driver, automationPage.getAutomationPageLink());
        Assert.assertTrue(BasePage.isTitleCorrect(automationPage.driver, automationPage.getEndavaAutomationTitle()),
                "Title is not the same.");
        Assert.assertTrue(automationPage.isAutomationPageLinkActive(), "Link is not active.");
        Assert.assertTrue(BasePage.isURLTheSame(automationPage.driver, automationPage.getEndavaAutomationUrl()),
                "URL is not the same.");
    }
=======
	/**
	 * Test validates that automation link on automation page is active, and checks
	 * if URL has changed
	 * 
	 * @author Goran.Kukolj
	 */
	@Test
	public void testAutomationMenuIsActive() {
		homePage.open();
		Utils.webDriverWait(homePage.driver, homePage.getContactButtons());
		menuPage = homePage.openMenu();
		Utils.webDriverWait(menuPage.driver, menuPage.getNavigationList());
		automationPage = menuPage.openAutomationPage();
		Utils.webDriverWait(automationPage.driver, automationPage.getAutomationPageLink());
		automationPage.assertPageTitle(automationPage.getEndavaAutomationTitle());
		Assert.assertTrue(automationPage.isAutomationPageLinkActive(), "Link is not active.");
		automationPage.assertPageUrl(automationPage.getEndavaAutomationUrl());
	}
>>>>>>> 772b66bd05b6f2c690c8a8b843c35cbd8de03393

    @AfterMethod
    public void tearDown() {
        homePage.quit();
    }
}
