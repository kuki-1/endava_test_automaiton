package com.endava.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class IndustriesPage extends BasePage {	
	
	private static final String INDUSTRIES_URL = "https://www.endava.com/en/Industries";
	private static final String INDUSTRIES_TITLE = "Industries";
	private static Logger log = Logger.getLogger(IndustriesPage.class);

	public IndustriesPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * @author jelena.corak
	 * @return String page URL
	 */
	public static String getIndustriesUrl() {
		return INDUSTRIES_URL;
	}

	/**
	 * @author jelena.corak
	 * @return String page title
	 */
	public static String getIndustriesTitle() {
		return INDUSTRIES_TITLE;
	}
}
