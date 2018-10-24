package com.endava.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class AboutPage extends BasePage {
	
	private static final String ABOUT_URL = "https://www.endava.com/en/About";
	private static final String ABOUT_TITLE = "About";	
	private static Logger log = Logger.getLogger(AboutPage.class);

	public AboutPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * @author jelena.corak
	 * @return String page URL
	 */
	public static String getAboutUrl() {
		return ABOUT_URL;
	}
	
	/**
	 * @author jelena.corak
	 * @return String page title
	 */
	public static String getAboutTitle() {
		return ABOUT_TITLE;
	}
}
