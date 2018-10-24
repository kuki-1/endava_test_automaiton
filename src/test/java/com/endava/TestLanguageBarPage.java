package com.endava;

import com.endava.pages.HomePage;
import com.endava.pages.LanguageBarPage;
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

class TestLanguageBarPage {

    private HomePage homePage;
    private LanguageBarPage lenguageBarPage;
    private static Logger log = Logger.getLogger(TestLanguageBarPage.class);

    @BeforeTest
    @Parameters({"browser"})
    public void setUp(String browser){
        homePage = Utils.setUpWebBrowser(browser);
        log.debug("Seting up " + browser + " browser in TestLangugeBarPage");
    }

    @Test
    public void testLenguage(){
        homePage.open();
        Assert.assertEquals(homePage.driver.getCurrentUrl(), homePage.getEndavaURL(), "HomePage Url does not mach");
        Assert.assertTrue(HomePage.isTitleCorrect(homePage.driver, homePage.getEndavaTitle()), "HomePage Title does not mach");
        Utils.webDriverWait(homePage.driver, homePage.getLenguage());
        lenguageBarPage = homePage.openLanguageBarPage();
        Utils.webDriverWait(lenguageBarPage.driver, lenguageBarPage.getDeutschLenguage());
        lenguageBarPage.selectElement(lenguageBarPage.driver.findElement(lenguageBarPage.getDeutschLenguage()));
        Assert.assertEquals(lenguageBarPage.driver.getCurrentUrl(), lenguageBarPage.getEndavaDeUrl(), "HomePage Deutsch lenguage does not mach");
        Assert.assertTrue(lenguageBarPage.driver.findElement(lenguageBarPage.getCopyRightsMessage()).getText().contains("Alle Rechte vorbehalten"), "DE Copy Rights message does not mach");
        Utils.webDriverWait(homePage.driver, homePage.getLenguage());
        lenguageBarPage.selectElement(homePage.driver.findElement(homePage.getLenguage()));
        Utils.webDriverWait(lenguageBarPage.driver, lenguageBarPage.getEnglishLenguage());
        lenguageBarPage.selectElement(lenguageBarPage.driver.findElement(lenguageBarPage.getEnglishLenguage()));
        Assert.assertEquals(lenguageBarPage.driver.getCurrentUrl(), lenguageBarPage.getEndavaEnUrl(), "HomePage English lenguage does not mach");
        Assert.assertTrue(lenguageBarPage.driver.findElement(lenguageBarPage.getCopyRightsMessage()).getText().contains(" All rights reserved"), "EN Copy Rights message does not mach");
    }


    @AfterTest
    public void tearDown() {
        homePage.quit();
        log.info("Closing browser in TestLanguageBarPage");
    }
}
