package com.endava.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Vladimir Krekic
 */

public class AgilePage extends BasePage {

    private static final String AGILE_URL = "https://www.endava.com/en/Agile";
    private static final String AGILE_TITLE = "Agile";
    private By agileOnRibbonMenu = By.xpath("//*[@id=\"secondary-nav\"]/ul/li[2]");

    protected AgilePage(WebDriver driver) {
        super(driver);
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
}
