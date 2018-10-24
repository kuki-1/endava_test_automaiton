package com.endava.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 
 * @author Goran.Kukolj
 *
 */
public class InvestorsPage extends BasePage {

	private By searchButton = By.xpath("//*[@id=\"_ctrl0_ctl33_divModuleContainer\"]/div/div/div/span/i");
	private By searchField = By.xpath("//*[@id=\"_ctrl0_ctl36_txtSearchInput\"]");
	private By numberOfResults = By.xpath("//*[@id=\"_ctrl0_ctl72_lblSummary\"]/strong[2]");
	private By searchResults = By.xpath("//*[@id=\"_ctrl0_ctl72_lblSummary\"]");
	private static final String ENDAVA_INVESTORS_URL = "https://investors.endava.com/home/default.aspx";
	private static final String ENDAVA_INVESTORS_TITLE = "Endava - Home";
	private static final String TEXT_ENTRY = "DAVA";
	private static final String EXPECTED_NUMBER_OF_RESULTS = "3";
	private static final String MAX_SEARCH_TIME_IN_SECONDS = "2";
	private static Logger log = Logger.getLogger(InvestorsPage.class);

	public InvestorsPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Finds the search button on the page by scrolling to it,and clicks on it
	 * 
	 * @author Goran.Kukolj
	 */
	public void clickOnSearchButton() {
		((JavascriptExecutor) driver).executeScript("scroll(0, 250);");
		driver.findElement(this.searchButton).click();
		log.debug("Scrolling to search button");
	}

	/**
	 * Finds the search field, enters some text and press enter
	 * 
	 * @author Goran.Kukolj
	 */
	public void clickOnSearchFieldAndEntertext(String text) {
		WebElement element = driver.findElement(this.searchField);
		element.sendKeys(text);
		element.sendKeys(Keys.ENTER);
		log.debug("Searched for " + text);
	}

	/**
	 * @author Goran.Kukolj
	 * 
	 * @return true or false depending on whether the search time is less than 2
	 *         seconds
	 */
	public boolean isSearchTimeLessThanTwoSeconds() {
		WebElement element = driver.findElement(this.searchResults);
		String resultText = element.getText();
		String searchTimeInSeconds = resultText.substring(resultText.indexOf('(') + 1, resultText.indexOf('(') + 7);
		log.debug("Comparing search time and expected search time");
		return (searchTimeInSeconds.compareTo(MAX_SEARCH_TIME_IN_SECONDS) < 0);
	}

	/**
	 * @author Goran.Kukolj
	 * @return true or false depending on equality of strings
	 */
	public boolean numberOfFoundResults() {
		String exactNumberOfResults = driver.findElement(this.numberOfResults).getText();
		log.debug("Search results are equal to " + exactNumberOfResults);
		return exactNumberOfResults.equals(EXPECTED_NUMBER_OF_RESULTS);
	}

	/**
	 * @author Goran.Kukolj
	 * @return search button element
	 */
	public By getSearchButton() {
		return searchButton;
	}

	/**
	 * @author Goran.Kukolj
	 * @return search field element
	 */
	public By getSearchField() {
		return searchField;
	}

	/**
	 * @author Goran.Kukolj
	 * @return number of results element
	 */
	public By getNumberOfResults() {
		return numberOfResults;
	}

	/**
	 * @author Goran.Kukolj
	 * @return String endava investors page title
	 */
	public String getEndavaInvestorsTitle() {
		return ENDAVA_INVESTORS_TITLE;
	}

	/**
	 * @author Goran.Kukolj
	 * @return String endava investors page URL
	 */
	public String getEndavaInvestorsUrl() {
		return ENDAVA_INVESTORS_URL;
	}

	public String getTextEntry() {
		return TEXT_ENTRY;
	}

	public String getExpectedNumberOfResults() {
		return EXPECTED_NUMBER_OF_RESULTS;
	}

	public static String getMaxSearchTimeInSeconds() {
		return MAX_SEARCH_TIME_IN_SECONDS;
	}
}