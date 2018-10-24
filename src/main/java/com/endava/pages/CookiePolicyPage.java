package com.endava.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class CookiePolicyPage extends BasePage {
	
	private static final String COOKIE_POLICY_URL = "https://www.endava.com/en/Cookie-Policy";
	private static final String COOKIE_POLICY_TITLE = "Cookie Policy";	
	private static Logger log = Logger.getLogger(CookiePolicyPage.class);

	public CookiePolicyPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Returns page URL.	  
	 * @author jelena.corak 
	 */
	public static String getCookiePolicyUrl() {
		return COOKIE_POLICY_URL;
	}

	/**
	 * Returns page title.	  
	 * @author jelena.corak 
	 */
	public static String getCookiePolicyTitle() {
		return COOKIE_POLICY_TITLE;
	}
}
