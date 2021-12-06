package commons;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageUIs.dee.BasePageUI;

public class BasePage {

	public static BasePage getBasePage() {
		return new BasePage();
	}

	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public Set<Cookie> getAllCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}
	
	public void setAllCookies(WebDriver driver, Set<Cookie> allCookies ) {
		for (Cookie cookie : allCookies) {
			driver.manage().addCookie(cookie);
		}

	}
	
	public Alert waitForAlertPresence(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, longTimeOut);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		alert = waitForAlertPresence(driver);
		alert.accept();
	}

	public void cancelAlert(WebDriver driver) {
		alert = waitForAlertPresence(driver);
		alert.dismiss();
	}

	public void sendkeyToAlert(WebDriver driver, String value) {
		alert = waitForAlertPresence(driver);
		alert.sendKeys(value);
	}

	public String getAlertText(WebDriver driver) {
		alert = waitForAlertPresence(driver);
		return alert.getText();
	}

	public void switchToWindowByID(WebDriver driver, String windowID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (!id.equals(windowID)) {
				driver.switchTo().window(id);
				break;
			}

		}
	}

	public void switchToWindowByTitle(WebDriver driver, String pageTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if (actualTitle.contains(pageTitle)) {
				break;
			}

		}

	}

	public void closeAllWindowsExceptParent(WebDriver driver, String parentID) {
		Set<String> allWindowsID = driver.getWindowHandles();
		for (String id : allWindowsID) {
			if (!id.contains(parentID)) {
				driver.switchTo().window(id);
				driver.close();
				sleepInSecond(1);
			}
			if (driver.getWindowHandles().size() == 1) {
				driver.switchTo().window(parentID);
				break;
			}
		}
	}

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public WebElement getElement(WebDriver driver, String locator) {
		return driver.findElement(getByXpath(locator));
	}

	public List<WebElement> getElements(WebDriver driver, String locator) {
		return driver.findElements(getByXpath(locator));
	}

	public By getByXpath(String locator) {
		return By.xpath(locator);
	}

	public String getDynamicLocator(String locator, String... params) {
		return String.format(locator, (Object[]) params);
	}

	public void clickOnElement(WebDriver driver, String locator) {
		getElement(driver, locator).click();
	}

	public void clickOnElement(WebDriver driver, String locator, String... params) {
		getElement(driver, getDynamicLocator(locator, params)).click();
	}

	public void sendKeyToElement(WebDriver driver, String locator, String value) {
		getElement(driver, locator).clear();
		getElement(driver, locator).sendKeys(value);
	}

	public int getElementsSize(WebDriver driver, String locator) {
		return getElements(driver, locator).size();
	}

	public void selectDropdownByText(WebDriver driver, String locator, String itemText) {
		select = new Select(getElement(driver, locator));
		select.selectByVisibleText(itemText.trim());
	}

	public void selectDropdownByValue(WebDriver driver, String locator, String itemValue) {
		select = new Select(getElement(driver, locator));
		select.selectByValue(itemValue);
	}

	public List<WebElement> getOptionsDropdown(WebDriver driver, String locator) {
		select = new Select(getElement(driver, locator));
		return select.getOptions();
	}

	public String getSelectedDropdown(WebDriver driver, String locator) {
		select = new Select(getElement(driver, locator));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropdownMultiple(WebDriver driver, String locator) {
		select = new Select(getElement(driver, locator));
		return select.isMultiple();
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
		getElement(driver, parentLocator).click();
		sleepInSecond(1);
		explicitWait = new WebDriverWait(driver, longTimeOut);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childItemLocator)));
		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItem)) {
				jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);
				item.click();
				sleepInSecond(1);
				break;
			}
		}
	}

	public void selectSizeInPDPByIndex(WebDriver driver, String parentLocator, String childItemLocator, int index) {
		getElement(driver, parentLocator).click();
		sleepInSecond(1);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childItemLocator)));
		sleepInSecond(1);
		allItems.get(index).click();
	}

	public String getElementAttributeValue(WebDriver driver, String locator, String attributeName) {
		return getElement(driver, locator).getAttribute(attributeName);
	}

	public String getElementText(WebDriver driver, String locator) {
		return getElement(driver, locator).getText();
	}

	public String getElementText(WebDriver driver, String locator, String... params) {
		return getElement(driver, getDynamicLocator(locator, params)).getText();
	}

	public void checkToCheckboxOrRadio(WebDriver driver, String locator) {
		if (!isElementSelected(driver, locator)) {
			getElement(driver, locator).click();
		}
	}

	public void uncheckToCheckbox(WebDriver driver, String locator) {
		if (isElementSelected(driver, locator)) {
			getElement(driver, locator).click();
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String locator) {
		try {
			return getElement(driver, locator).isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean isElementUndisplayed(WebDriver driver, String locator) {
		overrideGlobalTimeOut(driver, shortTimeOut);
		List<WebElement> elements = getElements(driver, locator);
		overrideGlobalTimeOut(driver, longTimeOut);
		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public void overrideGlobalTimeOut(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	public boolean isElementEnabled(WebDriver driver, String locator) {
		return getElement(driver, locator).isEnabled();
	}

	public boolean isElementSelected(WebDriver driver, String locator) {
		return getElement(driver, locator).isSelected();
	}

	public WebDriver switchToIframeByElement(WebDriver driver, String locator) {
		return driver.switchTo().frame(getElement(driver, locator));
	}

	public WebDriver switchToDefaultContent(WebDriver driver) {
		return driver.switchTo().defaultContent();
	}

	public void hoverToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.moveToElement(getElement(driver, locator)).perform();
	}

	public void doubleClickOnElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.doubleClick(getElement(driver, locator)).perform();
	}

	public void rightClickOnElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.contextClick(getElement(driver, locator)).perform();
	}

	public void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator) {
		action = new Actions(driver);
		action.dragAndDrop(getElement(driver, sourceLocator), getElement(driver, targetLocator)).perform();
	}

	public void pressKeyToElement(WebDriver driver, String locator, Keys key) {
		action = new Actions(driver);
		action.sendKeys(getElement(driver, locator), key).perform();
	}

	public Object executeForBrowser(WebDriver driver, String javaScript) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickOnElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getElement(driver, locator));
	}

	public void checkToCheckboxByJs(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		if (!getElement(driver, locator).isSelected()) {
			jsExecutor.executeScript("arguments[0].click()", getElement(driver, locator));
		}
	}

	public void uncheckToCheckboxByJs(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		if (getElement(driver, locator).isSelected()) {
			jsExecutor.executeScript("arguments[0].click()", getElement(driver, locator));
		}
	}

	public void scrollToElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, locator));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, longTimeOut);
		jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(driver, locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public void waitForElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
	}

	public void waitForElementVisible(WebDriver driver, String locator, String... params) {
		explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(getDynamicLocator(locator, params))));
	}

	public void waitForAllElementsVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(locator)));
	}

	public void waitForAllElementsVisible(WebDriver driver, String locator, String... params) {
		explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(getDynamicLocator(locator, params))));
	}

	public void waitForElementClickable(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
	}

	public void waitForElementClickable(WebDriver driver, String locator, String... params) {
		explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(getDynamicLocator(locator, params))));
	}

	public void waitForElementInvisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, shortTimeOut);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));

	}

	public void waitForElementInvisible(WebDriver driver, String locator, String... params) {
		explicitWait = new WebDriverWait(driver, shortTimeOut);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(getDynamicLocator(locator, params))));
	}

	public void waitForElementPresentInDOM(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(getDynamicLocator(locator))));
	}

	@SuppressWarnings("deprecation")
	public void fluentWaitForElementAndClick(WebDriver driver, String locator) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(120, TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return getElement(driver, locator);
			}
		});
		element.click();
	}

	public void checkAndClosePromoLayer(WebDriver driver) {
		overrideGlobalTimeOut(driver, shortTimeOut);
		List<WebElement> elements = getElements(driver, BasePageUI.PROMO_LAYER);
		overrideGlobalTimeOut(driver, longTimeOut);
		if (elements.size() > 0) {
			fluentWaitForElementAndClick(driver, BasePageUI.PROMO_LAYER_CLOSE_BUTTON);
		}
	}

	public String getCurrentlyTenant(WebDriver driver) {
		if (getPageUrl(driver).contains(".de/de")) {
			return "de";
		} else if (getPageUrl(driver).contains(".at/de")) {
			return "at";
		} else if (getPageUrl(driver).contains(".ch/de")) {
			return "ch";
		} else if (getPageUrl(driver).contains(".ch/fr")) {
			return "fr";
		} else {
			return "nl";
		}
	}

	public static <T> List<T> removeDuplicatesInList(ArrayList<T> list) {

		ArrayList<T> newList = new ArrayList<T>();

		for (T element : list) {

			if (!newList.contains(element)) {

				newList.add(element);
			}
		}

		return newList;
	}

	public LoginPageObject clickOnMyAccountOnHeader(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.MY_ACCOUNT_LINK_HEADER);
		clickOnElement(driver, BasePageUI.MY_ACCOUNT_LINK_HEADER);
		return PageGeneratorManager.getLoginPage(driver);
	}

	public HomePageObject clickOnLogoutHeader(WebDriver driver) {
		waitForElementVisible(driver, BasePageUI.LOGOUT_LINK_HEADER);
		clickOnElement(driver, BasePageUI.LOGOUT_LINK_HEADER);
		return PageGeneratorManager.getHomePage(driver);
	}

	private Alert alert;
	private Select select;
	private Actions action;
	private long longTimeOut = 45;
	private long shortTimeOut = 15;
	private WebDriverWait explicitWait;
	private JavascriptExecutor jsExecutor;
	private FluentWait<WebDriver> fluentDriver;

}
