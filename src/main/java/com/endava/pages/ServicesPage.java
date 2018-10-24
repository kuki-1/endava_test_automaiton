package com.endava.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class ServicesPage extends BasePage {
	
	private static final String SERVICES_URL = "https://www.endava.com/en/Services";
	private static final String SERVICES_TITLE = "Services";
	private static Logger log = Logger.getLogger(ServicesPage.class);

	public ServicesPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * @author jelena.corak
	 * @return String page URL
	 */
	public static String getServicesUrl() {
		return SERVICES_URL;
	}

	/**
	 * @author jelena.corak
	 * @return String page title
	 */
	public static String getServicesTitle() {
		return SERVICES_TITLE;
	}
}
