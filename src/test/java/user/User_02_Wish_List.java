package user;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.HomePageObject;
import pageObjects.MyAccountPageObject;
import pageObjects.ProductDetailPageObject;
import pageObjects.ProductListPageObject;

public class User_02_Wish_List  extends BaseTest {
    WebDriver driver;
    private String productName, productSize, productPrice, productAddedPrice, productAddedName, productAddedSize;

    @Parameters({ "browser", "url" })
    @BeforeClass
    public void initBrowser(String browserName, String url) {
        log.info("Pre-condition - Step 01:Init browser and go to Homepage to: " + url);
        driver = getBrowserDriver(browserName, url);
        log.info("Browser name and version is: " + getBrowserInitName());
        homePage = PageGeneratorManager.getHomePage(driver);
    }

    @Test
    public void TC_01_No_Login_Add_To_Wish_List_At_PDL() {
        log.info("TC_01_No_Login_Add_To_Wish_List_At_PDL - Step 2: Click on accept 'Cookie Consent'");
        homePage.clickOnAcceptCookieConsentButton();

        log.info("TC_01_No_Login_Add_To_Wish_List_At_PDL - Check and close 'promo-layer'");
        homePage.checkAndClosePromoLayer(driver);

        log.info("TC_01_No_Login_Add_To_Wish_List_At_PDL - Step 3: Click on '2nd-category' on 'Top Category'");
        productListPage = homePage.clickOnCategoryHeaderByIndex("2");

        log.info("TC_01_No_Login_Add_To_Wish_List_At_PDL - Check and close 'promo-layer'");
        productListPage.checkAndClosePromoLayer(driver);

        log.info("TC_01_No_Login_Add_To_Wish_List_At_PDL - Step 4: Get '1st-product's name'");
        productName = productListPage.getProductNameByIndex("1");

        log.info("TC_01_No_Login_Add_To_Wish_List_At_PDL - Step 5: Click on 'Wish-list icon' on '1st-product'");
        productListPage.clickOnWishListIconByIndex("1");

        log.info("TC_01_No_Login_Add_To_Wish_List_At_PDL - Step 6: Verify 'Successful msg add2wishlist' is displayed");
        verifyTrue(productListPage.isAdd2WishListMsgDisplayed());

        log.info("TC_01_No_Login_Add_To_Wish_List_At_PDL - Step 7: Verify 'Wish-list icon' selected");
        verifyTrue(productListPage.isWishListIconSelected("1"));

        log.info("TC_01_No_Login_Add_To_Wish_List_At_PDL - Step 8: Go to wishlist page by click on wishlist icon on header");
        myAccountPageObject = productListPage.clickOnWishListIconOnHeader();

        log.info("TC_01_No_Login_Add_To_Wish_List_At_PDL - Step 9: Get '1st-product added wishlist's name'");
        productAddedName = myAccountPageObject.getProductAddedWishListNameByIndex("1");

        log.info("TC_01_No_Login_Add_To_Wish_List_At_PDL - Step 10: Verify added-wishlist's name'");
        verifyTrue(productAddedName.contains(productName));

    }





    @AfterClass(alwaysRun = true)
    public void afterClass() {
        log.info("Close browser");
        cleanBrowserAndDriver();
    }
    HomePageObject homePage;
    ProductListPageObject productListPage;
    MyAccountPageObject myAccountPageObject;
}


