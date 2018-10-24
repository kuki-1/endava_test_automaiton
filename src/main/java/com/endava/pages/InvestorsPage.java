package com.endava.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InvestorsPage extends BasePage {

	private static final String INVESTORS_URL = "https://investors.endava.com/home/default.aspx";
	private static final String INVESTORS_TITLE = "Investors";
	private By investorsAboutUs = By.xpath("//*[@id='_ctrl0_ctl66_divModuleContainer']");
	private static Logger log = Logger.getLogger(InvestorsPage.class);

	public InvestorsPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Returns INVESTORS page URL.
	 * 
	 * @author jelena.corak
	 * 
	 */
	public static String getInvestorsUrl() {
		return INVESTORS_URL;
	}

	/**
	 * Returns INVESTORS page title.
	 * 
	 * @author jelena.corak
	 * 
	 */
	public static String getInvestorsTitle() {
		return INVESTORS_TITLE;
	}

	/**
	 * Returns search context of About Us element on the INVESTORS page.
	 * 
	 * @author jelena.corak
	 * 
	 * @return By search context of About Us element
	 */
	public By getInvestorsAboutUs() {
		return investorsAboutUs;
	}

}