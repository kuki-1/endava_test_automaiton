package com.endava.pages;

import com.endava.util.Utils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

/**
 * @author jana.djordjevic@endava.com
 *
 */
public class HomePage extends BasePage {

	private static final String ENDAVA_URL = "https://www.endava.com/";
	private static final String ENDAVA_TITLE = "Endava";
	private By contactButtons = By.id("contact-buttons");
	private By burgerMenu = By.id("menu-toggle");
	private By solutionMenus = By.className("proposition-section");
	private By centerScroll = By.className("fe_downarrow");
	private By agileItem = By.xpath("//*[@id=\"mCSB_1_container\"]/div[1]/nav/ul/li[2]/a");
	private By cookiesPolicyMessage = By.xpath("//*[@id=\"homePage\"]/div[7]");
	private By cookiesLearnMore = By.xpath("//*[@id=\"homePage\"]/div[7]/div/div/div[2]/div/div[1]/p/a");
  private static Logger log = Logger.getLogger(HomePage.class);

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public void open() {
		driver.get(ENDAVA_URL);
		driver.manage().window().maximize();
		log.debug("Opens " + getEndavaURL());
	}

	public MenuPage openMenu() {
		driver.findElement(this.burgerMenu).click();
		log.debug("Finds burger menu and clicks on it");
		return new MenuPage(driver);
	}

	/**
	 * Opens AgilePage and instantiate AgilePage object
	 * if Agile item is present on "burger" menu
	 * @author Vladimir Krekic
	 * @return AgilePage
	 */
	public AgilePage openAgilePage() {
		if(Utils.selectElement(driver.findElement(this.agileItem))){
			log.debug("AgilePage opened and instantiated");
			return new AgilePage(driver);
		}else {
			log.debug("Agile item on \"burger\" menu is not present");
			return null;
		}
	}

	/**
	 * finds the down arrow element and clicks on it
	 * 
	 * @author Goran.Kukolj
	 */
	public void clickOnDownArrow() {
		driver.findElement(this.centerScroll).click();
		log.debug("Finds the down arrow element and clicks on it");
	}

	/**
	 * @author Goran.Kukolj
	 * @return true or false depending on the visibility of solution menus
	 */
	public boolean isSolutionMenusVisible() {
		log.debug("Checks if solution menus are visible");
		return driver.findElement(solutionMenus).isDisplayed();
	}

	/**
	 * @author Goran.Kukolj
	 * @return String endava URL
	 */
	public String getEndavaURL() {
		return ENDAVA_URL;
	}

	/**
	 * @author Goran.Kukolj
	 * @return String endava title
	 */
	public String getEndavaTitle() {
		return ENDAVA_TITLE;
	}

	/**
	 * @author Goran.Kukolj
	 * @return contact button element
	 */
	public By getContactButtons() {
		return contactButtons;
	}
	
	/**
	 * @author jelena.corak
	 * @return By search context of cookies policy message element
	 */
	public By getCookiesPolicyMessage() {
		return cookiesPolicyMessage;
	}
	
	/**
	 * @author jelena.corak
	 * @return By search context of Learn More link element in the cookies policy message.
	 */
	public By getCookiesLearnMore() {
		return cookiesLearnMore;
	}

	public void validateCookiesPolicytext() {
		WebElement cookiesMessage = driver.findElement(cookiesPolicyMessage);
		if (cookiesMessage.isDisplayed()) {
			WebElement cookiesTextElement = driver.findElement(By.xpath("//*[@id=\"homePage\"]/div[7]/div/div/div[2]/div/div[1]/p/span"));
			Assert.assertEquals(cookiesTextElement.getText(), 
					"By using this site you agree to the use of cookies for analytics, personalized content and ads.", 
					"Text in the cookie policy message is not correct!");
		} else {
			log.debug("Cookies policy message not present!");
		}
	}
}
