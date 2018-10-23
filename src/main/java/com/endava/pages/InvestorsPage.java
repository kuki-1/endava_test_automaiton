package com.endava.pages;

import com.endava.util.Utils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Vladimir Krekic
 */

public class InvestorsPage extends BasePage {

    private WebElement search = driver.findElement(By.className("search-link"));
    private WebElement searchBox = driver.findElement(By.xpath("//*[@id=\"_ctrl0_ctl36_txtSearchInput\"]"));
    private WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"_ctrl0_ctl36_btnSearch\"]"));
    private By searchResultElement = By.className("module_message");
    private static Logger log = Logger.getLogger(HomePage.class);

    protected InvestorsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * @author Vladimir Krekic
     * Finds searchBox and fills it with search text
     * @param searchText String to be searched for
     */
    public void fillSearchBox(String searchText){
        Utils.makeItVisible(searchBox);
        if(searchBox.isDisplayed()){
            searchBox.sendKeys(searchText);
            log.debug("fillSearchBox(String searchText)");
        }else {
            log.debug("Search box not present");
        }
    }

    public WebElement getSearch() {
        return search;
    }

    public By getSearchResultElement() {
        return searchResultElement;
    }

    public WebElement getSubmitButton() {
        return submitButton;
    }
}
