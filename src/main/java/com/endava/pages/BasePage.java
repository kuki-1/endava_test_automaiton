package com.endava.pages;

import com.endava.util.Utils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.testng.Assert;

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
     * Verifies page URL.
     *
     * @param String expected URL
     * @author jelena.corak
     */
    public void assertPageUrl(String expectedUrl) {
        Assert.assertEquals(driver.getCurrentUrl().toLowerCase(), expectedUrl.toLowerCase(), "Incorrect URL!");
    }

    /**
     * Verifies page title.
     *
     * @param String expected title
     * @author jelena.corak
     */
    public void assertPageTitle(String expectedTitle) {
        Assert.assertEquals(driver.getTitle().toLowerCase(), expectedTitle.toLowerCase(), "Incorrect title!");
    }

    /**
     * Scrolls element into view.
     *
     * @param By element search context
     * @author jelena.corak
     */
    public void scrollIntoView(By context) {
        WebElement element = driver.findElement(context);
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception g) {
            log.debug(">>>> Exception in scrollIntoView! Element may not have been found: " + element);
            Assert.fail();
        }
    }

    /**
     * Asserts that the link in the element is correct.
     *
     * @param WebElement web element whose link is being checked
     * @param String     expected link
     * @author jelena.corak
     */
    public static void assertElementLink(WebElement element, String expectedLink) {
        Assert.assertTrue(element.getAttribute("href").equalsIgnoreCase(expectedLink),
                "Incorrect link for icon " + element.getAttribute("class"));
    }

    /**
     * Clicks on the element.
     *
     * @param By Search context of a web element *
     * @author jelena.corak
     */
    public void clickOnElement(By context) {
        WebElement eventElement = driver.findElement(context);
        String elementClass = eventElement.getAttribute("class");
        Assert.assertTrue(eventElement.isDisplayed(), "Element is not present.");
        eventElement.click();
        log.debug("Clicked on element " + elementClass);
    }

    /**
     * Clicks directly to element in case of overlay.
     *
     * @param By element search context
     * @author jelena.corak
     */
    public void directClickOnElement(By context) {
        WebElement element = driver.findElement(context);
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        } catch (Exception ex) {
            log.debug(">>>> Exception in directClickOnElement! Element may not have been found: " + element);
            Assert.fail();
        }
    }

    /**
     * @param element WebElement
     * @return boolean
     * @author Vladimir Krekic Method is selecting (clicking on) WebElement
     */
    public boolean selectElement(WebElement element) {
        makeItVisible(element);
        if (element.isDisplayed()) {
            element.click();
            log.debug("WebElement clicked " + element.toString());
            return true;
        }
        log.debug("WebElement not visible " + element.toString());
        return false;
    }

    /**
     * @return search result text
     * @author Vladimir Krekic
     */
    public String getSearchResult(WebElement element) {
        log.debug("Search Result found on element " + element.toString());
        return element.getText();
    }

    /**
     * @param webElement
     * @author Vladimir Krekic Makes web element visible
     */
    public void makeItVisible(WebElement webElement) {
        Coordinates coordinates = ((Locatable) webElement).getCoordinates();
        coordinates.inViewPort();
    }

    /**
     * @param driver
     * @param locator
     * @param originalText
     * @return true or false
     * depending on
     * comparing two
     * strings
     * @author Goran.Kukolj
     */

    public boolean validateString(WebDriver driver, By locator, String originalText) {
        WebElement element = driver.findElement(locator);
        String textToCompare = element.getText();
        log.debug("Compares two strings");
        return textToCompare.equals(originalText);
    }

    public void quit() {
        if (this.driver != null) {
            driver.quit();
            log.debug("Browser closed");
        }
    }
}