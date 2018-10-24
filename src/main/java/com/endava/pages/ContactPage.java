package com.endava.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Vladimir Krekic
 */

public class ContactPage extends BasePage {

    private static final String CONTACT_PAGE_URL = "https://www.endava.com/en/Contact";
    private static final String CONTACT_PAGE_TITLE = "Contact Us";
    private By servicesRadioButton = By.xpath("//*[@id=\"contact-form\"]/fieldset[1]/p[1]/label");
    private By joinRadioButton = By.xpath("//*[@id=\"contact-form\"]/fieldset[1]/p[2]/label");
    private By joinMessage = By.xpath("//*[@id=\"please-visit\"]");
    private static final String MESSAGE = "Please visit the Careers section on our website to apply for job openings." +
            " Please use the form below if you have another question or important message.";

    public ContactPage(WebDriver driver) {
        super(driver);
    }

    public By getServicesRadioButton() {
        return servicesRadioButton;
    }

    public By getJoinRadioButton() {
        return joinRadioButton;
    }

    public By getJoinMessage() {
        return joinMessage;
    }

    public String getMessage() {
        return MESSAGE;
    }

    public String getContactPageUrl() {
        return CONTACT_PAGE_URL;
    }

    public String getContactPageTitle() {
        return CONTACT_PAGE_TITLE;
    }
}
