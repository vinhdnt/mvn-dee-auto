package pageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.dee.BasePageUI;
import pageUIs.dee.HomePageUI;

public class HomePageObject extends BasePage {
	private WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isWordPressDisplayed() {
		waitForElementVisible(driver, HomePageUI.WORD_PRESS_HOME_PAGE);
		return isElementDisplayed(driver, HomePageUI.WORD_PRESS_HOME_PAGE);
	}

	public boolean isCookieConsentDisplayed() {
		waitForElementVisible(driver, HomePageUI.COOKIE_CONSENT_LAYER);
		return isElementDisplayed(driver, HomePageUI.COOKIE_CONSENT_LAYER);

	}

	public void clickOnAcceptCookieConsentButton() {
		waitForElementClickable(driver, HomePageUI.ACCEPT_COOKIE_CONSENT_BUTTON);
		clickOnElement(driver, HomePageUI.ACCEPT_COOKIE_CONSENT_BUTTON);
	}

	public boolean isHomePageDisplayed() {
		waitForElementVisible(driver, HomePageUI.HOMEPAGE_CONTENT_DIV);
		return isElementDisplayed(driver, HomePageUI.HOMEPAGE_CONTENT_DIV);
	}

	public ProductListPageObject clickOnCategoryHeaderByIndex(String categoryIndex) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_MAIN_CATEGORY_HEADER, categoryIndex);
		if (!isElementDisplayed(driver, BasePageUI.TOP_NAVIGATION)) {
			refreshCurrentPage(driver);
		}
		clickOnElement(driver, BasePageUI.DYNAMIC_MAIN_CATEGORY_HEADER, categoryIndex);
		return PageGeneratorManager.getProductListPage(driver);
	}

	public ArrayList<String> getAllLink() {
		ArrayList<String> allLinks = new ArrayList<String>();
		for (WebElement linkElement : getElements(driver, BasePageUI.ALL_URL)) {
			String link = linkElement.getAttribute("href");
			if (link != null && !link.isEmpty() && link.contains(getPageUrl(driver)) && !link.contains("css") && !link.contains("cart") && !link.contains("images")) {
				allLinks.add(link);
			}
		}
		return (ArrayList<String>) removeDuplicatesInList(allLinks);
	}

	public boolean isTopNavigationDisplayed() {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		int topNavigationSize = getElementsSize(driver, BasePageUI.TOP_NAVIGATION);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return (topNavigationSize >= 1);
	}

	public ArrayList<String> getBrokenLink(ArrayList<String> allLinks) {
		ArrayList<String> brokenLinks = new ArrayList<String>();
		for (String getUrl : allLinks) {
			driver.get(getUrl);
			if (getPageUrl(driver).contains("?noCache") || !isTopNavigationDisplayed()) {
				brokenLinks.add(getUrl);
			}
		}
		return brokenLinks;
	}

	public boolean isLogoutFieldOnHeaderDisplayed() {
		waitForElementVisible(driver, BasePageUI.LOGOUT_FIELD);
		return isElementDisplayed(driver, BasePageUI.LOGOUT_FIELD);
	}
	
	public boolean isLogoutFieldOnHeaderUndisplayed() {
		return isElementUndisplayed(driver, BasePageUI.LOGOUT_FIELD);
	}

}
