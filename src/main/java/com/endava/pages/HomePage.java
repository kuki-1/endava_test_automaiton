package com.endava.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author jana.djordjevic@endava.com
 */
public class HomePage extends BasePage {

    private static final String ENDAVA_URL = "https://www.endava.com/";
    private static final String ENDAVA_TITLE = "Endava";
    private static final String ENDAVA_DE_URL = "https://www.endava.com/de-DE";
    private static final String ENDAVA_EN_URL = "https://www.endava.com/en";
    private By contactButtons = By.id("contact-buttons");
    private By burgerMenu = By.id("menu-toggle");
    private By solutionMenus = By.className("proposition-section");
    private By centerScroll = By.className("fe_downarrow");
    private By agileItem = By.xpath("//*[@id=\"mCSB_1_container\"]/div[1]/nav/ul/li[2]/a");
    private By language = By.xpath("//*[@id=\"selected-lang\"]");
    private By englishLanguage = By.xpath("/html/body/header/div/div[1]/div[2]/div/nav/div/ul/li[2]/a");
    private By deutschLanguage = By.xpath("/html/body/header/div/div[1]/div[2]/div/nav/div/ul/li[1]/a");
    private By copyRightsMessage = By.xpath("/html/body/footer/section[2]/div/div/div[3]");
	  private By about = By.xpath("//*[@id=\"mCSB_1_container\"]/div[1]/nav/ul/li[8]/a");
	  private By contact = By.xpath("//*[@id=\"mCSB_1_container\"]/div[1]/nav/ul/li[10]/a");
  	private By investors = By.xpath("//*[@id=\"mCSB_1_container\"]/div[1]/nav/ul/li[5]/a");
  	private By phoneIcon = By.className("fe_phone");
  	private static Logger log = Logger.getLogger(HomePage.class);


    private static Logger log = Logger.getLogger(HomePage.class);

    public HomePage(WebDriver driver) {
        super(driver);
    }


    public void open() {
        driver.get(ENDAVA_URL);
        driver.manage().window().maximize();
        log.debug("Opens " + getEndavaURL());
    }

	/**
	 * Opens AgilePage and instantiate AgilePage object if Agile item is present on
	 * "burger" menu
	 * 
	 * @author Vladimir Krekic
	 * @return AgilePage
	 */
	public AgilePage openAgilePage() {
		if(selectElement(driver.findElement(this.agileItem))){
			log.debug("AgilePage opened and instantiated");
			return new AgilePage(driver);
		} else {
			log.debug("Agile item on \"burger\" menu is not present");
			return null;
		}
	}

    public MenuPage openMenu() {
        driver.findElement(this.burgerMenu).click();
        log.debug("Finds burger menu and clicks on it");
        return new MenuPage(driver);
    }

    /**
     * Opens AgilePage and instantiate AgilePage object
     * if Agile item is present on "burger" menu
     *
     * @return AgilePage
     * @author Vladimir Krekic
     */
    public AgilePage openAgilePage() {
        if (selectElement(driver.findElement(this.agileItem))) {
            log.debug("AgilePage opened and instantiated");
            return new AgilePage(driver);
        } else {
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
     * @return true or false depending on the visibility of solution menus
     * @author Goran.Kukolj
     */
    public boolean isSolutionMenusVisible() {
        log.debug("Checks if solution menus are visible");
        return driver.findElement(solutionMenus).isDisplayed();
    }

    /**
     * @return String endava URL
     * @author Goran.Kukolj
     */
    public String getEndavaURL() {
        return ENDAVA_URL;
    }

    /**
     * @return String endava title
     * @author Goran.Kukolj
     */
    public String getEndavaTitle() {
        return ENDAVA_TITLE;
    }

    /**
     * @return contact button element
     * @author Goran.Kukolj
     */
    public By getContactButtons() {
        return contactButtons;
    }

    /**
     * @return By language menu element on HomePage
     * @author Vladimir Krekic
     */
    public By getLanguage() {
        return language;
    }

    /**
     * @return String Endava Deutsch language Url
     * @author Vladimir Krekic
     */
    public String getEndavaDeUrl() {
        return ENDAVA_DE_URL;
    }

    /**
     * @return String Endava English Url
     * @author Vladimir Krekic
     */
    public String getEndavaEnUrl() {
        return ENDAVA_EN_URL;
    }

    /**
     * @return By Endava English element in language menu
     * @author Vladimir Krekic
     */
    public By getEnglishLanguage() {
        return englishLanguage;
    }

    /**
     * @return By Endava Deutsch element in language menu
     * @author Vladimir Krekic
     */
    public By getDeutschLanguage() {
        return deutschLanguage;
    }

    /**
     * @return By Copy Rights text on HomePage
     * @author Vladimir Krekic
     */
    public By getCopyRightsMessage() {
        return copyRightsMessage;
    }
	
	/**
	 * Opens ContactPage and instantiate ContactPage object
	 * if "Contacts" item is present on "burger" menu
	 * @author Vladimir Krekic
	 * @return ContactPage
	 */
	public ContactPage openContactsPage(){
		if(selectElement(driver.findElement(this.contact))){
			log.debug("ContactPage opened and instantiated");
			return new ContactPage(driver);
		}else {
			log.debug("Contacts item on \"burger\" menu is not present");
/**
	 * Opens AboutPage and instantiate AboutPage object
	 * if About item is present on "burger" menu
	 * @author Vladimir Krekic
	 * @return AboutPage
	 */
	public AboutPage openAboutPage(){
		if(selectElement(driver.findElement(this.about))){
			log.debug("AboutPage opened and instantiated");
			return new AboutPage(driver);
		}else {
			log.debug("About item on \"burger\" menu is not present");
			return null;
		}
	}
	
	/**
	 * @author jelena.corak
	 * @return By search context of the phone icon
	 */
	public By getPhoneIcon() {
		return phoneIcon;
	}
  
  /**
     * Opens InvestorsPage and instantiate InvestorsPage object
     * if "Investors" item is present on "burger" menu
     * @author Vladimir Krekic
     * @return InvestorsPage
     */
    public InvestorsPage openInvestorsPage(){
        if(selectElement(driver.findElement(this.investors))){
            log.debug("InvestorsPage opened and instantiated");
            return new InvestorsPage(driver);
        }else {
            log.debug("Investors item on \"burger\" menu is not present");
            return null;
        }
    }    
}
