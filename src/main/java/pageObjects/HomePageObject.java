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
import pageUIs.dee.ProductDetailPageUI;
import pageUIs.dee.ProductListPageUI;

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
		overrideGlobalTimeOut(driver, 10);
		int topNavigationSize = getElementsSize(driver, BasePageUI.TOP_NAVIGATION);
		overrideGlobalTimeOut(driver, 60);
		return (topNavigationSize >= 1);
	}


	public ArrayList<String> getBrokenLink(ArrayList<String> allLinks) {
		ArrayList<String> brokenLinks = new ArrayList<String>();
		for (String getUrl : allLinks) {
			driver.get(getUrl);
			if (getPageUrl(driver).contains("?noCache") || !isTopNavigationDisplayed() || isCategoryEmpty()) {
				brokenLinks.add(getUrl);
			}
		}
		return brokenLinks;
	}

	public ArrayList<String> getBrokenLinkAfterSwitchLang(ArrayList<String> allLinks) {
		ArrayList<String> brokenLinksAfterSwitch = new ArrayList<String>();
		for (String getUrl : allLinks) {
			driver.get(getUrl);
			if (!getPageUrl(driver).contains("?noCache")){
				overrideGlobalTimeOut(driver, 1);
				int switchLangSize = getElementsSize(driver, BasePageUI.LANGUAGE_SELECTOR);
				overrideGlobalTimeOut(driver, 60);
				if (switchLangSize == 1){
					waitForElementVisible(driver, BasePageUI.LANGUAGE_SELECTOR);
					clickOnElement(driver, BasePageUI.LANGUAGE_SELECTOR);
					waitForElementVisible(driver, BasePageUI.LANGUAGE_INACTIVE);
					clickOnElement(driver, BasePageUI.LANGUAGE_INACTIVE);
					String urlAfterSwitch = getPageUrl(driver);
					if (urlAfterSwitch.contains("?noCache")){
						brokenLinksAfterSwitch.add(getUrl);
					}else {
						waitForElementVisible(driver, BasePageUI.LANGUAGE_SELECTOR);
						clickOnElement(driver, BasePageUI.LANGUAGE_SELECTOR);
						waitForElementVisible(driver, BasePageUI.LANGUAGE_INACTIVE);
						clickOnElement(driver, BasePageUI.LANGUAGE_INACTIVE);
						if (getPageUrl(driver).contains("?noCache")){
							brokenLinksAfterSwitch.add(urlAfterSwitch);
						}
					}
				}
			}
		}
		return brokenLinksAfterSwitch;
	}

	public boolean isCategoryEmpty() {
		overrideGlobalTimeOut(driver, 1);
		int nullItemSize = getElementsSize(driver, ProductListPageUI.NULL_PRODUCT_LIST);
		overrideGlobalTimeOut(driver, 60);
		return (nullItemSize == 1);
	}

	public boolean isLogoutFieldOnHeaderDisplayed() {
		waitForElementVisible(driver, BasePageUI.LOGOUT_FIELD);
		return isElementDisplayed(driver, BasePageUI.LOGOUT_FIELD);
	}
	
	public boolean isLogoutFieldOnHeaderUndisplayed() {
		return isElementUndisplayed(driver, BasePageUI.LOGOUT_FIELD);
	}


	public void inputSearchKeyword(String keyWord) {
		waitForElementVisible(driver, HomePageUI.SEARCH_FIELD);
		sendKeyToElement(driver,HomePageUI.SEARCH_FIELD, keyWord);
	}

	public ProductListPageObject clickOnSearchButton() {
		waitForElementVisible(driver, HomePageUI.SEARCH_ICON);
		clickOnElement(driver, HomePageUI.SEARCH_ICON);
		return PageGeneratorManager.getProductListPage(driver);
	}
}
