package com.endava;

import com.endava.pages.HomePage;
import com.endava.pages.InvestorsPage;
import com.endava.pages.MenuPage;
import com.endava.util.Utils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * @author Vladimir Krekic
 */

class TestInvestorsPage {

    private HomePage homePage;
    private MenuPage menuPage;
    private InvestorsPage investorsPage;
    private static Logger log = Logger.getLogger(InvestorsPage.class);

    @BeforeTest
    @Parameters({"browser"})
    public void setUp(String browser) {
        homePage = Utils.setUpWebBrowser(browser);
        log.info("setUp()");
    }

    /**
     * @author Vladimir Krekic
     * From burger menu chooses Investors, validates url has been changed,
     * clicks on the Search, on a search box types "blahblah",
     * clicks on a "magnifying glass" and validates search result is "No results found."
     */
    @Test
    public void testInvestorsSearch() {
        homePage.open();
        menuPage = homePage.openMenu();
        Utils.webDriverWait(menuPage.driver, menuPage.getNavigationList());
        investorsPage = homePage.openInvestorsPage();
        Assert.assertNotEquals(homePage.getEndavaURL(),
                homePage.driver.getCurrentUrl(),"Url did not changed");
        Utils.selectElement(investorsPage.getSearch());
        investorsPage.fillSearchBox("blahblah");
        Utils.selectElement(investorsPage.getSubmitButton());
        Assert.assertEquals(Utils.getSearchResult
                        (investorsPage.driver.findElement(investorsPage.getSearchResultElement())),
                "No results found.","Wrong search result");
        log.info("testInvestorsSearch() - test passed");
    }

    @AfterTest
    public void tearDown() {
        homePage.quit();
        log.info("tearDown()");
    }
}
