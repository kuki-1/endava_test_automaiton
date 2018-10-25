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
  private By servicesRadioButton = By.xpath("//*[@id=\"contact-form\"]/fieldset[1]/p[1]/label");
  private By joinRadioButton = By.xpath("//*[@id=\"contact-form\"]/fieldset[1]/p[2]/label");
  private By joinMessage = By.xpath("//*[@id=\"please-visit\"]");
  private static final String MESSAGE = "Please visit the Careers section on our website to apply for job openings." +
            " Please use the form below if you have another question or important message.";

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
  
  /**
  * @author Vladimir Krekic
  * @return By Services radio button
  */
  public By getServicesRadioButton() {
        return servicesRadioButton;
    }

  /**
  * @author Vladimir Krekic
  * @return By Join Our Team radio button
  */
    public By getJoinRadioButton() {
        return joinRadioButton;
    }

  /**
  * @author Vladimir Krekic
  * @return By Join Our Team message
  */
    public By getJoinMessage() {
        return joinMessage;
    }

  /**
  * @author Vladimir Krekic
  * @return By Expected message after Join Our Team radio button
  */
    public String getMessage() {
        return MESSAGE;
    }
}
