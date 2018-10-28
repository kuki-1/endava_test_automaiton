package com.endava.util;

import java.util.Set;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.endava.pages.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author Vladimir Krekic
 */

public class Utils {

	private static Logger log = Logger.getLogger(Utils.class);

	/**
	 * @param browser String that represents chosen browser from testng.xml file
	 * @return HomePage
	 * @author Vladimir Krekic
	 */
	public static HomePage setUpWebBrowser(String browser) {
		HomePage homePage;
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			homePage = new HomePage(new ChromeDriver(disableInfobarsOption()));
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			homePage = new HomePage(new FirefoxDriver());
		} else if (browser.equalsIgnoreCase("internet explorer")) {
			WebDriverManager.iedriver().setup();
			homePage = new HomePage(new InternetExplorerDriver());
		} else
			throw new RuntimeException();
		log.debug("setUpWebBrowser(browser) - returns HomePage with chosen browser driver");
		return homePage;
	}

	/**
	 * @param driver
	 * @param locator
	 */
	public static void webDriverWait(WebDriver driver, By locator) {
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * Returns option that disables infobars in chrome browser
	 *
	 * @return ChromeOptions
	 * @author jelena.corak
	 */
	public static ChromeOptions disableInfobarsOption() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-infobars");
		return options;
	}

	/**
	 * Transfers control to other window
	 * 
	 * @author Goran.Kukolj
	 * @param driver
	 */
	public static void switchControlToNewWindow(WebDriver driver) {
		String mainWindow = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		for (String window : windows) {
			if (!window.equals(mainWindow)) {
				driver.switchTo().window(window);
				break;
			}
		}
		log.debug("Transfers control to other window");
	}
}
