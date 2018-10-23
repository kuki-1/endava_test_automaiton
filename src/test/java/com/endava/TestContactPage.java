package com.endava;

import com.endava.pages.ContactsPage;
import com.endava.pages.HomePage;
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

class TestContactPage {

    private static Logger log = Logger.getLogger(ContactsPage.class);
    private HomePage homePage;
    private MenuPage menuPage;
    private ContactsPage contactsPage;

    @BeforeTest
    @Parameters({"browser"})
    public void setUp(String browser) {
        homePage = Utils.setUpWebBrowser(browser);
        log.info("setUp()");
    }

    /**
     * @author Vladimir Krekic
     * From Burger menu go to Contact page, validate new URL is correct,
     * validate both radio buttons ("Interested in our services?" and "Want to join our team?") are not selected,
     * click on "Want to join our team? " Radio button, validate it's selected
     * Validate message "Please visit the Careers section on our website to apply for job openings.
     * Please use the form below if you have another question or important message." appears.
     */
    @Test
    public void testContactPage() {
        homePage.open();
        menuPage = homePage.openMenu();
        Utils.webDriverWait(menuPage.driver, menuPage.getNavigationList());
        contactsPage = homePage.openContactsPage();
        Assert.assertEquals(contactsPage.getContactPageUrl(), contactsPage.driver.getCurrentUrl(), "CuntactPage Url does not mach");
        Assert.assertFalse(contactsPage.driver.findElement(contactsPage.getServicesRadioButton()).isSelected(),
                "Element \"ServiceRadioButton\" selected");
        Assert.assertFalse(contactsPage.driver.findElement(contactsPage.getJoinRadioButton()).isSelected(),
                "Element \"JoinRadioButton\" selected");
        Utils.selectElement(contactsPage.driver.findElement(contactsPage.getJoinRadioButton()));
        Assert.assertFalse(Utils.getSearchResult(contactsPage.driver.findElement(contactsPage.getJoinMessage())).isEmpty(),
                "Element \"JoinRadioButton\" not selected");
        Assert.assertEquals(contactsPage.getMessage(),
                Utils.getSearchResult(contactsPage.driver.findElement(contactsPage.getJoinMessage())),
                "JoinMessage does not mach");
        log.debug("testContactPage() - test passed");
    }

    @AfterTest
    public void tearDown() {
        homePage.quit();
        log.info("tearDown()");
    }
}
