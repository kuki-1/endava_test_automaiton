package com.endava.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class CareersPage extends BasePage {

	private static final String CAREERS_URL = "https://careers.endava.com/en";
	private static final String CAREERS_TITLE = "Be More";	
	private static Logger log = Logger.getLogger(CareersPage.class);
	
	public CareersPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * @author jelena.corak
	 * @return String page URL
	 */
	public static String getCareersUrl() {
		return CAREERS_URL;
	}

	/**
	 * @author jelena.corak
	 * @return String page title
	 */
	public static String getCareersTitle() {
		return CAREERS_TITLE;
	}

}
