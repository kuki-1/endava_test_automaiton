package com.endava.pages;

import com.endava.util.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	private By phoneIcon = By.className("fe_phone");
	private By socialMediaIcons = By.cssSelector("div.social:nth-child(1) > ul:nth-child(1)");
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
	 * @return By search context of the phone icon
	 */
	public By getPhoneIcon() {
		return phoneIcon;
	}
	
	/**
	 * @author jelena.corak
	 * @return By search context of social media icon list
	 */
	public By getSocialMediaIcons() {
		return socialMediaIcons;
	}
	
	/**
	 * Clicks on the element.
	 * 
	 * @author jelena.corak
	 * 
	 * @param By Search context of a web element	 * 
	 */
	public void clickOnElement(By context) {
		WebElement eventElement = driver.findElement(context);
		String elementClass = eventElement.getAttribute("class");
		Assert.assertTrue(eventElement.isDisplayed(), "Element is not present.");
		eventElement.click();
		log.debug("Clicked on element " + elementClass);
	}
	
	/**
	 * Returns String values of expected social media URLs.
	 * 
	 * @author jelena.corak
	 * @return List<String> social media URL list
	 */
	public List<String> getListOfSocialMediaUrls() {
		List<String> listofLinks = new ArrayList<>();
		listofLinks.add("https://www.facebook.com/endava");
		listofLinks.add("https://twitter.com/endava");
		listofLinks.add("https://www.linkedin.com/company/endava");
		listofLinks.add("https://www.instagram.com/endava/");
		listofLinks.add("https://plus.google.com/u/0/111956919197222464721/posts?_ga=1.55764843.114380448.1443786751");
		return listofLinks;
	}
	
	/**
	 * Returns list of social media icon elements with corresponding links.
	 * 
	 * @author jelena.corak
	 * @return List<WebElement> social media icon list
	 */
	public List<WebElement> getSocialMediaIconList() {
		List<WebElement> iconList = driver.findElement(By.cssSelector("div.social:nth-child(1) > ul:nth-child(1)"))
				.findElements(By.tagName("li")).stream().map(e -> e.findElement(By.tagName("a")))
				.collect(Collectors.toList());
		return iconList;
	}
}
