package com.endava.util;

import com.endava.pages.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


/**
 * @author Vladimir Krekic
 */

public class Utils {

    /**
     * @author Vladimir Krekic
     * @param browser String that represents chosen browser from testng.xml file
     * @return HomePage
     */
    public static HomePage setUpWebBrowser(String browser){
        HomePage homePage;
        if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            homePage = new HomePage(new ChromeDriver());
        }else if(browser.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            homePage = new HomePage(new FirefoxDriver());
        }else if(browser.equalsIgnoreCase("internet explorer")){
            WebDriverManager.iedriver().setup();
            homePage = new HomePage(new InternetExplorerDriver());
        }else throw new RuntimeException();

        return homePage;
    }
}
