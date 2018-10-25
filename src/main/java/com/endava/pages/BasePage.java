package com.endava.pages;

import com.endava.util.Utils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author jana.djordjevic@endava.com
 */
public class BasePage {

    public WebDriver driver;
    private static Logger log = Logger.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * @param driver
     * @param title
     * @return true or false depending on equality of actual title and expected
     * title
     * @author Goran.Kukolj
     */
    public static boolean isTitleCorrect(WebDriver driver, String title) {
        log.debug("Checks if the title is correct");
        return driver.getTitle().equalsIgnoreCase(title);
    }

    /**
     * @param driver
     * @param title
     * @return true or false depending on equality of URL
     * @author Goran.Kukolj
     */
    public static boolean isURLTheSame(WebDriver driver, String title) {
        log.debug("Checks if the URL has changed");
        return driver.getCurrentUrl().equalsIgnoreCase(title);
    }

    /**
     * @param element WebElement
     * @return boolean
     * @author Vladimir Krekic
     * Method is selecting (clicking on) WebElement
     */
    public boolean selectElement(WebElement element) {
        Utils.makeItVisible(element);
        if (element.isDisplayed()) {
            element.click();
            log.debug("WebElement clicked " + element.toString());
            return true;
        }
        log.debug("WebElement not visible " + element.toString());
        return false;
    }


    public void quit() {
        if (this.driver != null) {
            driver.quit();
            log.debug("Browser closed");
        }
    }
}