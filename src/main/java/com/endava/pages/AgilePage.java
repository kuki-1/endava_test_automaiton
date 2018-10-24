package com.endava.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * @author Vladimir Krekic
 */

public class AgilePage extends BasePage {

	private By autofillWithLinkedinButton = By
			.xpath("//*[contains(@id,'li_ui_li_gen')and contains(text(), 'Sign in with LinkedIn')]");
	private By allowAccessButton = By.xpath("//*[@id=\"body\"]/div/form/div[2]/ul/li[1]/input");
	private By emailInputField = By.xpath("//*[@id=\"session_key-oauthAuthorizeForm\"]");
	private By cancelButton = By.xpath("//*[@id=\"body\"]/div/form/div[2]/ul/li[2]/button");
	private By signInToLinkedinMessage = By.xpath("//*[@id=\"body\"]/div/form/div[1]/p");
	private By enterValidEmailErrorMessage = By.xpath("//*[@id=\"session_key-oauthAuthorizeForm-error\"]");
	private By enterPasswordErrorMessage = By.xpath("//*[@id=\"session_password-oauthAuthorizeForm-error\"]");
	private By correctMarkedFieldsErrorMessage = By.xpath("//*[@id=\"global-error\"]/div/p/strong");
	private By agileOnRibbonMenu = By.xpath("//*[@id=\"secondary-nav\"]/ul/li[2]");
	private static final String POP_UP_WINDOW_TITLE = "Authorize | LinkedIn";
	private static final String POP_UP_WINDOW_SUBMIT_URL = "https://www.linkedin.com/uas/oauth/authorize/submit";
	private static final String SIGN_IN_TO_LINKEDIN = "Sign in to LinkedIn and allow access:";
	private static final String ENTER_VALID_EMAIL = "Please enter a valid email address.";
	private static final String ENTER_PASSWORD = "Please enter a password.";
	private static final String CORRECT_MARKED_FIELDS = "Please correct the marked field(s) below.";
	private static final String AGILE_URL = "https://www.endava.com/en/Agile";
	private static final String AGILE_TITLE = "Agile";
	private static Logger log = Logger.getLogger(AgilePage.class);

	protected AgilePage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Finds the autofill with linkedin button on the page and clicks on it
	 * 
	 * @author Goran.Kukolj
	 */
	public void clickOnAutofillWithLinkedinButton() {
		driver.findElement(this.autofillWithLinkedinButton).click();
		log.debug("Clicks on autofill button");
	}

	/**
	 * Scrolls to the autofill with linkedin button
	 * 
	 * @author Goran.Kukolj
	 */
	public void scrollToautofillWithLinkedinButton() {
		((JavascriptExecutor) driver).executeScript("scroll(0, 4800);");
		log.debug("Scrolls to autofill button");
	}

	/**
	 * Finds input email field and enters invalid email
	 * 
	 * @author Goran.Kukolj
	 */
	public void enterEmailAddress() {
		driver.findElement(emailInputField).sendKeys("inputSomething");
		log.debug("Enters invalid email address");
	}

	/**
	 * Finds allow access button and clicks on it
	 * 
	 * @author Goran.Kukolj
	 */
	public void clickOnAllowAccessButton() {
		driver.findElement(this.allowAccessButton).click();
		log.debug("Clicks on allow access button");
	}

	/**
	 * Finds cancel button and clicks on it
	 * 
	 * @author Goran.Kukolj
	 */
	public void clickOnCancelButton() {
		driver.findElement(this.cancelButton).click();
		log.debug("Clicks on cancel button");
	}

	public By getAgileOnRibbonMenu() {
		return agileOnRibbonMenu;
	}

	public String getAgileUrl() {
		return AGILE_URL;
	}

	public String getAgileTitle() {
		return AGILE_TITLE;
	}

	public By getAutofillWithLinkedinButton() {
		return autofillWithLinkedinButton;
	}

	public By getAllowAccessButton() {
		return allowAccessButton;
	}

	public By getEmailInputField() {
		return emailInputField;
	}

	public By getCancelButton() {
		return cancelButton;
	}

	public By getSignInToLinkedinMessage() {
		return signInToLinkedinMessage;
	}

	public By getEnterValidEmailErrorMessage() {
		return enterValidEmailErrorMessage;
	}

	public By getEnterPasswordErrorMessage() {
		return enterPasswordErrorMessage;
	}

	public By getCorrectMarkedFieldsErrorMessage() {
		return correctMarkedFieldsErrorMessage;
	}

	public String getPopUpWindowTitle() {
		return POP_UP_WINDOW_TITLE;
	}

	public String getPopUpWindowSubmitUrl() {
		return POP_UP_WINDOW_SUBMIT_URL;
	}

	public String getSignInToLinkedin() {
		return SIGN_IN_TO_LINKEDIN;
	}

	public String getEnterValidEmail() {
		return ENTER_VALID_EMAIL;
	}

	public String getEnterPassword() {
		return ENTER_PASSWORD;
	}

	public String getCorrectMarkedFields() {
		return CORRECT_MARKED_FIELDS;
	}
}
