package com.endava;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.endava.pages.ContactPage;
import com.endava.pages.HomePage;
import com.endava.pages.MenuPage;
import com.endava.util.Utils;

/**
 * @author Vladimir Krekic
 */

class TestContactPage {

	private static Logger log = Logger.getLogger(ContactPage.class);
	private HomePage homePage;
	private MenuPage menuPage;
	private ContactPage contactsPage;

	@BeforeTest
	@Parameters({ "browser" })
	public void setUp(String browser) {
		homePage = Utils.setUpWebBrowser(browser);
		log.info("Seting up " + browser + " browser in TestContactPage");
	}

	/**
	 * @author Vladimir Krekic From Burger menu go to Contact page, validate new URL is correct, validate both radio buttons
	 *         ("Interested in our services?" and "Want to join our team?") are not selected, click on "Want to join our
	 *         team? " Radio button, validate it's selected Validate message "Please visit the Careers section on our
	 *         website to apply for job openings. Please use the form below if you have another question or important
	 *         message." appears.
	 */
	@Test
	public void testContactPage() {
		homePage.open();
		menuPage = homePage.openMenu();
		Utils.webDriverWait(menuPage.driver, menuPage.getNavigationList());
		contactsPage = homePage.openContactsPage();
		Assert.assertEquals(ContactPage.getContactUrl(), contactsPage.driver.getCurrentUrl(), "ContactPage Url does not mach");
		contactsPage.assertPageTitle(ContactPage.getContactTitle());
		Assert.assertFalse(contactsPage.driver.findElement(contactsPage.getServicesRadioButton()).isSelected(),
				"Element \"ServiceRadioButton\" selected");
		Assert.assertFalse(contactsPage.driver.findElement(contactsPage.getJoinRadioButton()).isSelected(), "Element \"JoinRadioButton\" selected");
		contactsPage.selectElement(contactsPage.driver.findElement(contactsPage.getJoinRadioButton()));
		Assert.assertFalse(contactsPage.getSearchResult(contactsPage.driver.findElement(contactsPage.getJoinMessage())).isEmpty(),
				"Element \"JoinRadioButton\" not selected");
		Assert.assertEquals(contactsPage.getMessage(), contactsPage.getSearchResult(contactsPage.driver.findElement(contactsPage.getJoinMessage())),
				"JoinMessage does not mach");
		log.debug("testContactPage() - test passed");
	}

	@AfterTest
	public void tearDown() {
		homePage.quit();
		log.info("Closing browser in TestContactPage");
	}
}
