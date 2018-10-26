package com.endava.pages;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Vladimir Krekic
 */

public class AboutPage extends BasePage {

	private static final String ABOUT_URL = "https://www.endava.com/en/About";
	private static final String ABOUT_TITLE = "About";
	private static Logger log = Logger.getLogger(AboutPage.class);
	private WebElement rightArrow = driver.findElement(By.xpath("//*[@id=\"all-locations\"]/div[2]/div[2]/div[2]"));
	private By cities = By.xpath("//*[@id=\"all-locations\"]/div[1]/div/div[*]/div/h3");
	private By addresses = By.xpath("//*[@id=\"all-locations\"]/div[1]/div/div[*]/div/p");
	private List<WebElement> listOfCities = driver.findElements(cities);
	private List<WebElement> listOfAddresses = driver.findElements(addresses);
	private Set<String> allLocations = setAllLocations();
	private static final List<String> ADDRESSES_OF_ENDAVA_LOCATIONS = Arrays.asList("BELGRADE",
			"9đ, Milutina Milankovića St.", "BOGOTÁ", "Calle 96 No. 10-38, Edificio BOX, 7th & 8th Floor, Bogota D.C.",
			"ATLANTA", "One Glenlake Pkwy, Suite 784", "AMSTERDAM", "Laapersveld 43, Hilversum", "CARACAS",
			"Av. Francisco de MirandaTorre HP, Piso 18. Municipio Chacao", "BUCHAREST",
			"4G Vasile Milea Blvd., 9th floor, AFI 3 Business Park", "BUENOS AIRES", "San Martin 439", "CHISINAU",
			"15 Sfatul Tarii St.", "FRANKFURT", "Eschersheimer Landstraße 14", "COPENHAGEN", "UNIVATE, Njalsgade 76",
			"CLUJ-NAPOCA", "51 Al. Vaida Voevod St.", "DENVER", "2420 17th St.", "LONDON", "125 Old Broad Street",
			"MONTEVIDEO", "Rio Negro 1338/3", "MEDELLÍN", "Cra 48 A # 15 sur – 84", "IASI",
			"3E Palat St. Palas, United Business Center 1 - 5th Floor", "ROSARIO", "Urquiza 2284", "NEW YORK",
			"757 3rd Avenue Suite 1901, NY, 10017 USA", "NEW JERSEY", "5 Hilltop Road, Mendham", "PARANÁ",
			"Buenos Aires 60", "SOFIA", "89b Vitosha Blvd., Millenium Business Center, 9th & 10th Floor", "SEATTLE",
			"12900 NE 180th Street, #240", "SKOPJE", "Kale Building, UI. 11 Mart Br. 2");

	public AboutPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * @return Set of cities and addresses grabbed from web site
	 * @author Vladimir Krekic
	 */
	public Set<String> setAllLocations() {
		Set<String> setOfAllLocations = new LinkedHashSet<>();
		for (int counter = 0; counter < 6; counter++) {
			addLocations(setOfAllLocations, listOfCities);
			addLocations(setOfAllLocations, listOfAddresses);
			selectElement(rightArrow);
		}
		log.debug("Set of addresses grabbed");
		return setOfAllLocations;
	}

	/**
	 * @param set Set of cities and addresses in which elements will be added
	 * @param list partial List of cities or addresses to be added into a Set
	 * @return partial Set of cities and addresses grabbed from web site
	 * @author Vladimir Krekic
	 */
	private Set<String> addLocations(Set<String> set, List<WebElement> list) {
		set.addAll(
				list.stream().map(w -> w.getText()).filter(w -> !w.equalsIgnoreCase("")).collect(Collectors.toSet()));
		return set;
	}

	/**
	 * @param addresses Set of addresses grabbed from web page
	 * @return boolean - true if all elements are matching list of addresses and prints list of elements not matching if
	 *         false
	 * @author Vladimir Krekic
	 */
	public boolean checkAddresses(Set<String> addresses) {
		if (addresses.size() == ADDRESSES_OF_ENDAVA_LOCATIONS.size()) {
			return addresses.stream().filter(address -> !ADDRESSES_OF_ENDAVA_LOCATIONS.contains(address))
					.peek(log::debug) // prints list of elements not matching
					.count() == 0;
		}
		log.debug("Number of grabbed addresses not matching. Size of set should be "
				+ ADDRESSES_OF_ENDAVA_LOCATIONS.size() + " found: " + addresses.size());
		return false;
	}

	public Set<String> getAllLocations() {
		return allLocations;
	}

	public String getAboutUrl() {
		return ABOUT_URL;
	}

	public String getAboutTitle() {
		return ABOUT_TITLE;
	}
}
