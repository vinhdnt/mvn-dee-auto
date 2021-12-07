package checkout;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.HomePageObject;
import pageObjects.ProductDetailPageObject;
import pageObjects.ProductListPageObject;

public class CO_01_Add_To_Cart extends BaseTest {
	WebDriver driver;
	BasePage basepage;
	private String productName, productSize, productPrice, productAddedPrice, productAddedName, productAddedSize;
	

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void initBrowser(String browserName, String url) {
		log.info("Pre-condition - Step 01:Init browser '" + browserName +"' And go to Homepage to: " + url);
		driver = getBrowserDriver(browserName, url);
		homePage = PageGeneratorManager.getHomePage(driver);
	}
	@Test
	public void TC_01_Add_To_Cart_At_PDL() {
		log.info("TC_01_Add_To_Cart_At_PDL - Step 2: Verify 'wordpress' is displayed");
		verifyTrue(homePage.isWordPressDisplayed());
		
		log.info("TC_01_Add_To_Cart_At_PDL - Step 3: Verify 'Cookie Consent' is displayed");
		verifyTrue(homePage.isCookieConsentDisplayed()); 
		
		log.info("TC_01_Add_To_Cart_At_PDL - Step 4: Click on accept 'Cookie Consent'");
		homePage.clickOnAcceptCookieConsentButton();

		log.info("TC_01_Add_To_Cart_At_PDL - Step 5: Click on '3rd-category' on 'Top Category'"); 
		productListPage = homePage.clickOnCategoryHeaderByIndex("3");

		log.info("TC_01_Add_To_Cart_At_PDL - Step 6: Check and close 'promo-layer'"); 
		productListPage.checkAndClosePromoLayer(driver);
		
		log.info("TC_01_Add_To_Cart_At_PDL - Step 7: Verify 'product list' are displayed"); 
		verifyTrue(productListPage.isProductsListDisplayed());
		
		log.info("TC_01_Add_To_Cart_At_PDL - Step 8: Get '1st-product's name'");
		productName = productListPage.getProductNameByIndex("1");
		
		log.info("TC_01_Add_To_Cart_At_PDL - Step 9: Get '1st-product' price");
		productPrice = productListPage.getProductPriceByIndex("1");		
		
		log.info("TC_01_Add_To_Cart_At_PDL - Step 10: Click on '1st-product add2cart expand'");
		productListPage.clickOnShowPopupAddToCartProductByIndex("1");
		
		log.info("TC_01_Add_To_Cart_At_PDL - Step 11: Select '1st-available-size' of '1st-product'");
		productListPage.selectAvailableSize(); 
		
		log.info("TC_01_Add_To_Cart_At_PDL - Step 12: Get '1st-product' size-selected");
		productSize = productListPage.getProductSize();
		
		log.info("TC_01_Add_To_Cart_At_PDL - Step 13: Click on 'Add2cart' button");
		productListPage.clickOnAddToCartButton();
		
		log.info("TC_01_Add_To_Cart_At_PDL - Step 14: Verify 'cart slide layer' is displayed");
		verifyTrue(productListPage.isCartSlideLayerDisplayed()); 
		
		log.info("TC_01_Add_To_Cart_At_PDL - Step 15: Get 'product-added's name'");
		productAddedName = productListPage.getProductAddedName();
		
		log.info("TC_01_Add_To_Cart_At_PDL - Step 16: Get 'product-added's size'");
		productAddedSize = productListPage.getProductAddedSize();
		
		log.info("TC_01_Add_To_Cart_At_PDL - Step 17: Get 'product-added' price");
		productAddedPrice = productListPage.getProductAddedPrice();
		
		log.info("TC_01_Add_To_Cart_At_PDL - Step 18: Verify 'product-added's name' ");
		verifyTrue(productAddedName.contains(productName));

		log.info("TC_01_Add_To_Cart_At_PDL - Step 19: Verify 'product-added's size' ");
		verifyEquals(productSize, productSize);
		
		log.info("TC_01_Add_To_Cart_At_PDL - Step 20: Verify 'product-added's price' ");
		verifyEquals(productPrice, productAddedPrice);
		
		sleepInSecond(5);
		
		log.info("TC_01_Add_To_Cart_At_PDL - Step 21: Verify 'cart slide layer' is undisplayed ");
		verifyTrue(productListPage.isCartSlideLayerUndisplayed());	
		
		log.info("TC_01_Add_To_Cart_At_PDL - Step 22: Verify 'product price' on icon basket header ");
		verifyEquals(productPrice, productListPage.getProductPriceOnIconBasket());
		
		log.info("TC_01_Add_To_Cart_At_PDL - Step 22: Verify 'product count' on icon basket header is 1 ");
		verifyEquals(productListPage.getCartItemCount(), "1");
	}
	
	@Test
	public void TC_02_Remove_Product_From_Cart() {
		
		log.info("TC_02_Remove_Product_From_Cart - Step 1: Click on 'icon basket header' ");
		productListPage.clickOnCartIconHeader();
		
		log.info("TC_02_Remove_Product_From_Cart - Step 2: Verify 'cart slide layer' is displayed ");
		verifyTrue(productListPage.isCartSlideLayerDisplayed());
		
		log.info("TC_02_Remove_Product_From_Cart - Step 3: Click on 'remove icon product' ");
		productListPage.clickOnRemoveIconByIndex("0");
		
		sleepInSecond(2);

		log.info("TC_02_Remove_Product_From_Cart - Step 4: Verify 'cart slide layer' is undisplayed ");
		verifyTrue(productListPage.isCartSlideLayerUndisplayed());
		
		log.info("TC_02_Remove_Product_From_Cart - Step 5: Verify 'product price' on icon basket header = 0.00 ");
		verifyTrue(productListPage.getProductPriceOnIconBasket().contains("0,00"));
		
		log.info("TC_02_Remove_Product_From_Cart - Step 6: Verify 'product count 'on icon basket header is blank ");
		verifyTrue(productListPage.isCartItemCountBlank());
	}
	
	@Test
	public void TC_03_Add_To_Cart_At_PDP() {
		log.info("TC_03_Add_To_Cart_At_PDP - Step 1: Click '1st-product name' ");
		productDetailPage = productListPage.clickOnProductName("1");
		
		log.info("TC_03_Add_To_Cart_At_PDP - Step 2: Select '1st-available-size' of 1st-product ");
		productDetailPage.selectAvailableSizeDropdownByIndex(0);
		
		log.info("TC_03_Add_To_Cart_At_PDP - Step 3: Get 'pdp-product's name' ");
		productName = productDetailPage.getProductName();
		
		log.info("TC_03_Add_To_Cart_At_PDP - Step 4: Get 'pdp-product's price'");
		productPrice = productDetailPage.getProductPrice();
		
		log.info("TC_03_Add_To_Cart_At_PDP - Step 5: Get 'pdp-product's size'");
		productSize = productDetailPage.getProductSize();
		
		log.info("TC_03_Add_To_Cart_At_PDP - Step 6: Click on 'Add2cart button'");
		productDetailPage.clickOnAddToCartButton();
		
		log.info("TC_03_Add_To_Cart_At_PDP - Step 7: Verify 'cart slide layer' is displayed");
		verifyTrue(productDetailPage.isCartSlideLayerDisplayed());
		
		log.info("TC_03_Add_To_Cart_At_PDP - Step 8: Verify 'product-added's name'");
		productAddedName = productDetailPage.getProductAddedName();
		
		log.info("TC_03_Add_To_Cart_At_PDP - Step 9: Verify 'product-added's size'");
		productAddedSize = productDetailPage.getProductAddedSize();
		
		log.info("TC_03_Add_To_Cart_At_PDP - Step 10: Verify 'product-added's price'");
		productAddedPrice = productDetailPage.getProductAddedPrice();		
		
		log.info("TC_03_Add_To_Cart_At_PDP - Step 11: Verify 'product-added's name'");
		verifyTrue(productAddedName.equals(productName)); 
				
		log.info("TC_03_Add_To_Cart_At_PDP - Step 12: Verify 'product-added's size'");
		verifyEquals(productSize, productAddedSize);
		
		log.info("TC_03_Add_To_Cart_At_PDP - Step 13: Verify 'product-added's price'");
		verifyEquals(productPrice, productAddedPrice);		
		
		sleepInSecond(2);
		
		log.info("TC_03_Add_To_Cart_At_PDP - Step 14: Click on close 'icon cart slide layer'");
		productDetailPage.closeCartSlideLayer();		
		
		log.info("TC_03_Add_To_Cart_At_PDP - Step 15: Verify 'cart slide layer' is undisplayed");
		verifyTrue(productDetailPage.isCartSlideLayerUndisplayed());		
		
		log.info("TC_03_Add_To_Cart_At_PDP - Step 16: Verify 'product price' on icon basket header");
		verifyEquals(productPrice, productDetailPage.getProductPriceOnIconBasket());
		
		log.info("TC_03_Add_To_Cart_At_PDP - Step 17: Verify 'product count' on icon basket header is 1");
		verifyEquals(productDetailPage.getCartItemCount(), "1");
		
		
	}
	
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		log.info("Close browser");
		cleanBrowserAndDriver();
	}
	HomePageObject homePage;
	ProductListPageObject productListPage;
	ProductDetailPageObject productDetailPage;

}
