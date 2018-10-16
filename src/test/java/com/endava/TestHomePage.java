package com.endava;

import org.apache.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.endava.pages.HomePage;
import com.endava.pages.MenuPage;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author jana.djordjevic@endava.com
 *
 */
public class TestHomePage {

	private HomePage homePage;
	private MenuPage menuPage;	
	private static Logger log = Logger.getLogger(TestHomePage.class);

	@BeforeTest
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		log.info("setUp()");
	}

	/*
	 * Test validates that home page is opened by checking if contact buttons are
	 * visible on the page
	 */
	@Test
	public void testHomePageIsOpened() {
		homePage = new HomePage(new ChromeDriver());
		homePage.open();
		new WebDriverWait(homePage.driver, 5)
				.until(ExpectedConditions.visibilityOfElementLocated(homePage.contactButtons));
		log.info("testHomePageIsOpened()");
	}

	@Test
	public void testOpenMenu() {
		homePage = new HomePage(new ChromeDriver());
		homePage.open();
		new WebDriverWait(homePage.driver, 5)
				.until(ExpectedConditions.visibilityOfElementLocated(homePage.contactButtons));
		menuPage = homePage.openMenu();
		new WebDriverWait(menuPage.driver, 5)
				.until(ExpectedConditions.visibilityOfElementLocated(menuPage.navigationList));
		log.info("testOpenMenu()");
	}

	@AfterMethod
	public void tearDown() {
		homePage.quit();
		log.info("tearDown()");
	}

}
