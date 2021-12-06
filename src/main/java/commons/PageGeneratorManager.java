package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.CheckOutPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.MyAccountPageObject;
import pageObjects.ProductDetailPageObject;
import pageObjects.ProductListPageObject;
import pageObjects.RegisterPageObject;

public class PageGeneratorManager {
	private static HomePageObject homePage;
	private static LoginPageObject loginPage;
	private static MyAccountPageObject myAccountPage;
	private static RegisterPageObject registerPage;
	private static ProductListPageObject productListPage;
	private static ProductDetailPageObject productDetailPage;
	private static CheckOutPageObject checkOutPage;
	
	private PageGeneratorManager() {
	}
	
	public static HomePageObject getHomePage(WebDriver driver) {
		if (homePage==null) {
			return new HomePageObject(driver);
		}
		return homePage;
	}
	public static LoginPageObject getLoginPage(WebDriver driver) {
		if (loginPage==null) {
			return new LoginPageObject(driver);
		}
		return loginPage;
	}
	public static MyAccountPageObject getMyAccountPage(WebDriver driver) {
		if (myAccountPage==null) {
			return new MyAccountPageObject(driver);
		}
		return myAccountPage;
	}
	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		if (registerPage==null) {
			return new RegisterPageObject(driver);
		}
		return registerPage;
	}
	public static ProductListPageObject getProductListPage(WebDriver driver) {
		if (productListPage==null) {
			return new ProductListPageObject(driver);
		}
		return productListPage;
	}
	
	public static ProductDetailPageObject getProductDetailPage(WebDriver driver) {
		if (productDetailPage==null) {
			return new ProductDetailPageObject(driver);
		}
		return productDetailPage;
	}

	public static CheckOutPageObject getCheckOutPage(WebDriver driver) {
		if (checkOutPage==null) {
			return new CheckOutPageObject(driver);
		}
		return checkOutPage;
	}
}
