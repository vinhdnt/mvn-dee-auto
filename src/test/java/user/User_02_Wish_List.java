package user;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.*;

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

        log.info("TC_01_No_Login_Add_To_Wish_List_At_PDL - Step 7: Verify 'Wish-list icon 1st-product' selected");
        verifyTrue(productListPage.isWishListIconSelected("1"));

        log.info("TC_01_No_Login_Add_To_Wish_List_At_PDL - Step 8: Verify 'Wish-list icon count' = 1");
        verifyTrue(productListPage.getWishListItemCount() == 1);

        log.info("TC_01_No_Login_Add_To_Wish_List_At_PDL - Step 9: Go to 'wishlist-page' by click on wishlist icon on header");
        myAccountPage = productListPage.clickOnWishListIconOnHeader();

        log.info("TC_01_No_Login_Add_To_Wish_List_At_PDL - Step 10: Get '1st-product added wishlist's name'");
        productAddedName = myAccountPage.getProductAddedWishListNameByIndex("1");

        log.info("TC_01_No_Login_Add_To_Wish_List_At_PDL - Step 11: Verify added-wishlist's name'");
        verifyTrue(productAddedName.contains(productName));

        log.info("TC_01_No_Login_Add_To_Wish_List_At_PDL - Step 12: Click on '2nd-category' on 'Top Category'");
        productListPage = homePage.clickOnCategoryHeaderByIndex("2");

        //productListPage.refreshCurrentPage(driver);

        /*log.info("TC_01_No_Login_Add_To_Wish_List_At_PDL - Step 13: Verify 'Wish-list icon' selected after reload page");
        verifyTrue(productListPage.isWishListIconSelected("1"));*/
    }

    @Test
    public void TC_02_No_Login_Add_To_Wish_List_At_PDP() {
        log.info("TC_02_No_Login_Add_To_Wish_List_At_PDP - Step 1: Click on '2nd product' to go PDP");
        productDetailPage = productListPage.clickOnProductName("2");

        log.info("TC_02_No_Login_Add_To_Wish_List_At_PDP - Step 2: Click on 'add2wishlist'");
        productDetailPage.clickOnAdd2WishList();

        log.info("TC_02_No_Login_Add_To_Wish_List_At_PDP - Step 3: Verify 'Successful msg add2wishlist' is displayed");
        verifyTrue(productDetailPage.isAdd2WishListMsgPDLDisplayed());

        log.info("TC_02_No_Login_Add_To_Wish_List_At_PDL - Step 4: Verify 'Wish-list icon count' = 2");
        verifyTrue(productDetailPage.getWishListItemCount() == 2);

        log.info("TC_02_No_Login_Add_To_Wish_List_At_PDL - Step 5: Click on '2nd-category' on 'Top Category'");
        productListPage = homePage.clickOnCategoryHeaderByIndex("2");

        //productListPage.refreshCurrentPage(driver);

        /*log.info("TC_02_No_Login_Add_To_Wish_List_At_PDL - Step 7: Verify 'Wish-list icon 2nd product' selected");
        verifyTrue(productListPage.isWishListIconSelected("2"));*/

    }

    @Test
    public void TC_03_No_Login_Remove_Wish_List_Added() {
        log.info("TC_03_Remove_Wish_List_Added_Item_At_PDL - Step 1:  Click on 'Wish-list icon' on '1st-product' to remove wishlist item ");
        productListPage.clickOnWishListSelectedIconByIndex("1");

        log.info("TC_03_Remove_Wish_List_Added_Item_At_PDL - Step 2: Verify 'Wish-list icon' is unselected");
        verifyTrue(productListPage.isWishListIconUnselected("1"));

        log.info("TC_03_Remove_Wish_List_Added_Item_At_PDL - Step 3: Verify 'Wish-list icon count' = 1");
        verifyTrue(productListPage.getWishListItemCount() == 1);

        log.info("TC_03_Remove_Wish_List_Added_Item_At_PDL - Step 4: Go to 'wishlist-page' by click on wishlist icon on header");
        myAccountPage = productListPage.clickOnWishListIconOnHeader();

        sleepInSecond(3);

        log.info("TC_03_Remove_Wish_List_Added_Item_At_PDL - Step 5: Remove 'first Wish-list item'");
        myAccountPage.removeWishListItemByIndex("1");

        log.info("TC_03_Remove_Wish_List_Added_Item_At_PDL - Step 6: Verify 'Wish-list icon count' = 0");
        verifyTrue(productListPage.getWishListItemCount() == 0);

        log.info("TC_03_Remove_Wish_List_Added_Item_At_PDL - Step 7: Verify 'Wish-list' page is empty");
        verifyTrue(myAccountPage.isNoItemWishListMsgDisplayed());

    }

    @Test
    public void TC_04_Logged_In_Add_To_Wish_List_At_PDL() {
        log.info("TC_04_Logged_In_Add_To_Wish_List_At_PDL - Step 1: Click on My account header");
        loginPage = productListPage.clickOnMyAccountOnHeader(driver);

        log.info("TC_04_Logged_In_Add_To_Wish_List_At_PDL - Step 2: Login with valid email password: " + User_01_Register_Login.EMAIL +"/" + User_01_Register_Login.PASSWORD );
        loginPage.loginWithUserNamePassword(User_01_Register_Login.EMAIL, User_01_Register_Login.PASSWORD);

        log.info("TC_04_Logged_In_Add_To_Wish_List_At_PDL - Step 4: Click on '2nd-category' on 'Top Category'");
        productListPage = homePage.clickOnCategoryHeaderByIndex("2");

        log.info("TC_04_Logged_In_Add_To_Wish_List_At_PDL - Step 5: Click on 'Wish-list icon' on '1st-product'");
        productListPage.clickOnWishListIconByIndex("1");

        log.info("TC_04_Logged_In_Add_To_Wish_List_At_PDL - Step 5: Verify 'Wish-list icon 1st-product' selected");
        verifyTrue(productListPage.isWishListIconSelected("1"));
    }

    @Test
    public void TC_05_Logged_In_Add_To_Wish_List_At_PDP() {
        log.info("TC_05_Logged_In_Add_To_Wish_List_At_PDP - Step 1: Click on '2nd product' to go PDP");
        productDetailPage = productListPage.clickOnProductName("2");

        log.info("TC_05_Logged_In_Add_To_Wish_List_At_PDP - Step 2: Click on Add2Wishlist button");
        productDetailPage.clickOnAdd2WishList();

        log.info("TC_05_Logged_In_Add_To_Wish_List_At_PDP - Step 3: Verify 'Successful msg add2wishlist' is displayed");
        verifyTrue(productDetailPage.isAdd2WishListMsgPDPDisplayed());

        log.info("TC_05_Logged_In_Add_To_Wish_List_At_PDP - Step 4: Verify 'Wish-list icon count' = 2");
        verifyTrue(productDetailPage.getWishListItemCount() == 2);

        log.info("TC_05_Logged_In_Add_To_Wish_List_At_PDP - Step 5: Click on '2nd-category' on 'Top Category'");
        productListPage = homePage.clickOnCategoryHeaderByIndex("2");

        //productListPage.refreshCurrentPage(driver);

        /*log.info("TC_05_Logged_In_Add_To_Wish_List_At_PDP - Step 7: Verify 'Wish-list icon 2nd product' selected");
        verifyTrue(productListPage.isWishListIconSelected("2"));*/
    }

    @Test
    public void TC_06_Logged_In_Remove_Wish_List_Added() {
        log.info("TC_06_Logged_In_Remove_Wish_List_Added - Step 1:  Click on 'Wish-list icon' on '1st-product' to remove wishlist item ");
        productListPage.clickOnWishListSelectedIconByIndex("1");

        log.info("TC_06_Logged_In_Remove_Wish_List_Added - Step 2: Verify 'Wish-list icon' is unselected");
        verifyTrue(productListPage.isWishListIconUnselected("1"));

        log.info("TC_06_Logged_In_Remove_Wish_List_Added - Step 3: Verify 'Wish-list icon count' = 1");
        verifyTrue(productListPage.getWishListItemCount() == 1);

        log.info("TC_06_Logged_In_Remove_Wish_List_Added - Step 4: Go to 'wishlist-page' by click on wishlist icon on header");
        myAccountPage = productListPage.clickOnWishListIconOnHeader();

        sleepInSecond(3);

        log.info("TC_06_Logged_In_Remove_Wish_List_Added - Step 5: Remove 'first Wish-list item'");
        myAccountPage.removeWishListItemByIndex("1");

        log.info("TC_06_Logged_In_Remove_Wish_List_Added - Step 6: Verify 'Wish-list icon count' = 0");
        verifyTrue(productListPage.getWishListItemCount() == 0);

        log.info("TC_06_Logged_In_Remove_Wish_List_Added - Step 7: Verify 'Wish-list' page is empty");
        verifyTrue(myAccountPage.isNoItemWishListMsgDisplayed());

    }


    @AfterClass(alwaysRun = true)
    public void afterClass() {
        log.info("Close browser");
        cleanBrowserAndDriver();
    }
    HomePageObject homePage;
    ProductListPageObject productListPage;
    ProductDetailPageObject productDetailPage;
    MyAccountPageObject myAccountPage;
    LoginPageObject loginPage;
}


