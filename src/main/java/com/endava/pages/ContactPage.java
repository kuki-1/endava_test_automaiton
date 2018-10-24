package com.endava.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * 
 * @author jelena.corak
 *
 */

public class ContactPage extends BasePage {	

	private static final String CONTACT_URL = "https://www.endava.com/en/Contact";
	private static final String CONTACT_TITLE = "Contact Us";
	private By contactMenuItem = By.xpath(".//*[@id='mCSB_1_container']/div[1]/nav/ul/li[10]/a");
	private static Logger log = Logger.getLogger(ContactPage.class);
		
	public ContactPage(WebDriver driver) {
		super(driver);		
	}
	
	/**	 
	 * @author jelena.corak
	 * @return String Contact page URL 
	 */
	public static String getContactUrl() {
		return CONTACT_URL;
	}
	
	/**	  
	 * @author jelena.corak
	 * @return String Contact page title
	 */
	public static String getContactTitle() {
		return CONTACT_TITLE;
	}
	
	/** 
	 * @author jelena.corak
	 * @return By Contact menu item search context
	 */
	public By getContactMenuItem() {
		return contactMenuItem;
	}
}
