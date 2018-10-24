package com.endava.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class SuccessStoriesPage extends BasePage {		
	
	private static final String SUCCESS_STORIES_URL = "https://www.endava.com/en/Success-Stories";
	private static final String SUCCESS_STORIES_TITLE = "Success Stories";	
	private static Logger log = Logger.getLogger(SuccessStoriesPage.class);
	
	public SuccessStoriesPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * @author jelena.corak
	 * @return String page URL
	 */
	public static String getSuccessStoriesUrl() {
		return SUCCESS_STORIES_URL;
	}

	/**
	 * @author jelena.corak
	 * @return String page title
	 */
	public static String getSuccessStoriesTitle() {
		return SUCCESS_STORIES_TITLE;
	}
}
