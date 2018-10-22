package com.endava.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Vladimir Krekic
 */

public class AgilePage extends BasePage {

    private By agileOnRibbonMenu = By.xpath("//*[@id=\"secondary-nav\"]/ul/li[2]");

    protected AgilePage(WebDriver driver) {
        super(driver);
    }

    public By getAgileOnRibbonMenu() {
        return agileOnRibbonMenu;
    }
}
