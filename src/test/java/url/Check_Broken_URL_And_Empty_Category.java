package url;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.HomePageObject;

public class Check_Broken_URL_And_Empty_Category extends BaseTest {
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
	public void TC_01_Check_Broken_URL_And_Empty_Category() {
		log.info("TC_01_Check_Broken_URL_And_Empty_Category - Step 1: Click on 'accept Cookie Consent' button");
		homePage.clickOnAcceptCookieConsentButton();
		
		log.info("TC_01_Check_Broken_URL_And_Empty_Category - Step 2: Get all link");
		allLink = homePage.getAllLink();
		
		log.info("TC_01_Check_Broken_URL_And_Empty_Category - Step 3: Open each link to verify");
		brokenLink = homePage.getBrokenLink(allLink);

		log.info("TC_01_Check_Broken_URL_And_Empty_Category - Step 4: Verify have broken link or not");
		verifyTrue(brokenLink.isEmpty());
		
		for (String i : brokenLink) {
			log.info(i);
		}
	}
	
	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
	
	HomePageObject homePage;

}
