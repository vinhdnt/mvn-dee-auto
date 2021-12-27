package url;

import commons.BasePage;
import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.HomePageObject;

import java.util.ArrayList;

public class Url_02_Check_Switch_Lang extends BaseTest {
	WebDriver driver;
	BasePage basepage;
	private ArrayList<String> allLink, brokenLink;
	
	@Parameters({ "browser", "url" })
	@BeforeMethod
	public void initBrowser(String browserName, String url) {
		log.info("Pre-condition - Step 01:Init browser and go to Homepage to: " + url);
		driver = getBrowserDriver(browserName, url);
		log.info("Browser name and version is: " + getBrowserInitName());
		homePage = PageGeneratorManager.getHomePage(driver);
	}
	
	@Test
	public void TC_01_Check_Switch_Lang() {
		log.info("TC_01_Check_Switch_Lang - Step 1: Click on 'accept Cookie Consent' button");
		homePage.clickOnAcceptCookieConsentButton();

		log.info("TC_01_Check_Switch_Lang - Check and close 'promo-layer'");
		homePage.refreshCurrentPage(driver);
		homePage.checkAndClosePromoLayer(driver);

		log.info("TC_01_Check_Switch_Lang - Step 2: Get all link");
		allLink = homePage.getAllLink();
		
		log.info("TC_01_Check_Switch_Lang - Step 3: Open each link and switch lang to verify");
		brokenLink = homePage.getBrokenLinkAfterSwitchLang(allLink);

		log.info("TC_01_Check_Switch_Lang - Step 4: Verify have broken link or not");
		verifyTrue(brokenLink.isEmpty());
		
		for (String i : brokenLink) {
			log.info(i);
		}
	}
	
	@AfterMethod
	public void afterMethod() {
		log.info("Close browser");
		cleanBrowserAndDriver();
	}
	
	HomePageObject homePage;

}
