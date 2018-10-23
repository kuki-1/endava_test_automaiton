package com.endava.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Vladimir Krekic
 */

public class LanguageBarPage extends BasePage {

    private static final String ENDAVA_DE_URL = "https://www.endava.com/de-DE";
    private static final String ENDAVA_EN_URL = "https://www.endava.com/en";

    private By englishLenguage = By.xpath("/html/body/header/div/div[1]/div[2]/div/nav/div/ul/li[2]/a");
    private By deutschLenguage = By.xpath("/html/body/header/div/div[1]/div[2]/div/nav/div/ul/li[1]/a");
    private By copyRightsMessage = By.xpath("/html/body/footer/section[2]/div/div/div[3]");

    public LanguageBarPage(WebDriver driver) {
        super(driver);
    }

    public By getEnglishLenguage() {
        return englishLenguage;
    }

    public By getDeutschLenguage() {
        return deutschLenguage;
    }

    public String getEndavaDeUrl() {
        return ENDAVA_DE_URL;
    }

    public String getEndavaEnUrl() {
        return ENDAVA_EN_URL;
    }

    public By getCopyRightsMessage() {
        return copyRightsMessage;
    }
}
