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
 */
public class TestHomePage {

    private HomePage homePage;
    private MenuPage menuPage;
    private static Logger log = Logger.getLogger(TestHomePage.class);

    @BeforeTest
    @Parameters({"browser"})
    public void setUp(String browser) {
        homePage = Utils.setUpWebBrowser(browser);
        log.info("setUp()");
    }

    @Test(priority = 1)
    public void testHomePageIsOpened() {
        homePage.open();
        Utils.webDriverWait(homePage.driver, homePage.getContactButtons());
        Assert.assertTrue(BasePage.isURLTheSame(homePage.driver, homePage.getEndavaURL()), "Incorrect HomePage Url");
        Assert.assertTrue(BasePage.isTitleCorrect(homePage.driver, homePage.getEndavaTitle()), "Incorrect HomePage Title ");
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
        Assert.assertTrue(BasePage.isURLTheSame(menuPage.driver, homePage.getEndavaURL()), "URL is not the same.");
        log.info("testOpenMenu()");
    }

    /**
     * @author Vladimir Krekic
     * At home page chooses 'Deutsch' language.
     * Verifies that language is changed: check that url is https://www.endava.com/de-DE and then
     * goes at bottom page and checks if copyright message has expected german text "Alle Rechte vorbehalten" (instead of 'All rights reserved').
     * Changes language back to 'English'.
     * Verifies that language is changed : url should be https://www.endava.com/en and then
     * goes at bottom page and check if copyright message has expected English text 'All rights reserved'.
     */
    @Test(dependsOnMethods = {"testOpenMenu"})
    public void testLanguageMenu() {
        homePage.open();
        Assert.assertEquals(homePage.driver.getCurrentUrl(), homePage.getEndavaURL(), "HomePage Url does not mach");
        Assert.assertTrue(HomePage.isTitleCorrect(homePage.driver, homePage.getEndavaTitle()), "HomePage Title does not mach");
        Utils.webDriverWait(homePage.driver, homePage.getLanguage());
        homePage.selectElement(homePage.driver.findElement(homePage.getLanguage()));
        Utils.webDriverWait(homePage.driver, homePage.getDeutschLanguage());
        homePage.selectElement(homePage.driver.findElement(homePage.getDeutschLanguage()));
        Assert.assertEquals(homePage.driver.getCurrentUrl(), homePage.getEndavaDeUrl(), "HomePage Deutsch lenguage does not mach");
        Assert.assertTrue(homePage.driver.findElement(homePage.getCopyRightsMessage()).getText().contains("Alle Rechte vorbehalten"), "DE Copy Rights message does not mach");
        Utils.webDriverWait(homePage.driver, homePage.getLanguage());
        homePage.selectElement(homePage.driver.findElement(homePage.getLanguage()));
        Utils.webDriverWait(homePage.driver, homePage.getEnglishLanguage());
        homePage.selectElement(homePage.driver.findElement(homePage.getEnglishLanguage()));
        Assert.assertEquals(homePage.driver.getCurrentUrl(), homePage.getEndavaEnUrl(), "HomePage English lenguage does not mach");
        Assert.assertTrue(homePage.driver.findElement(homePage.getCopyRightsMessage()).getText().contains(" All rights reserved"), "EN Copy Rights message does not mach");
    }

    @AfterClass
    public void tearDown() {
        homePage.quit();
        log.info("tearDown()");
    }
}
