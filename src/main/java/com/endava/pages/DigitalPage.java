package com.endava.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class DigitalPage extends BasePage {	
	
	private static final String DIGITAL_URL = "https://www.endava.com/en/Digital";
	private static final String DIGITAL_TITLE = "Digital";	
	private static Logger log = Logger.getLogger(DigitalPage.class);
	
	public DigitalPage(WebDriver driver) {
		super(driver);		
	}

	/**
	 * @author jelena.corak
	 * @return String page URL
	 */
	public static String getDigitalUrl() {
		return DIGITAL_URL;
	}

	/**
	 * @author jelena.corak
	 * @return String page title
	 */
	public static String getDigitalTitle() {
		return DIGITAL_TITLE;
	}
	
	
}
