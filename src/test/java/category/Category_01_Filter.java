package category;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.HomePageObject;
import pageObjects.ProductDetailPageObject;
import pageObjects.ProductListPageObject;

public class Category_01_Filter extends BaseTest {
    WebDriver driver;
    BasePage basepage;

    @Parameters({ "browser", "url" })
    @BeforeClass
    public void initBrowser(String browserName, String url) {
        log.info("Pre-condition - Step 01:Init browser and go to Homepage to: " + url);
        driver = getBrowserDriver(browserName, url);
        log.info("Browser name and version is: " + getBrowserInitName());
        homePage = PageGeneratorManager.getHomePage(driver);
    }

    @Test
    public void TC_01_Add_1_Filter() {
        log.info("TC_01_Add_Filter  - Step 1: Click on accept 'Cookie Consent'");
        homePage.clickOnAcceptCookieConsentButton();

        log.info("TC_01_Add_Filter - Check and close 'promo-layer'");
        homePage.checkAndClosePromoLayer(driver);

        log.info("TC_01_Add_Filter - Step 02: Click on '2nd-category' on 'Top Category'");
        productListPage = homePage.clickOnCategoryHeaderByIndex("2");

        log.info("TC_01_Add_Filter - Check and close 'promo-layer'");
        productListPage.checkAndClosePromoLayer(driver);

        log.info("TC_01_Add_Filter - Step 03: Click on 'Brand' filter");
        productListPage.clickOnFilterBoxByID("md_brand_f_");

        log.info("TC_01_Add_Filter - Step 04: Click on 'Deerberg' option");
        productListPage.clickOnFilterOptionByID("brand_f__Deerberg");

        log.info("TC_01_Add_Filter - Step 05: Click on 'submit filter' button");
        productListPage.clickOnSubmitFilterButtonByID("subMenu_brand_f_");

        log.info("TC_01_Add_Filter - Step 06: Verify url contain filter's parameter");
        verifyTrue(driver.getCurrentUrl().contains("query?manufacturer=Deerberg"));

        log.info("TC_01_Add_Filter - Step 07: Verify 'product list' are displayed");
        verifyTrue(productListPage.isProductsListDisplayed());

        log.info("TC_01_Add_Filter - Step 08: Verify 'Deerberg' brand is filtered");
        verifyTrue(productListPage.isFilterSelectDisplayed("Deerberg"));
    }

    @Test
    public void TC_02_Remove_Filters() {
        log.info("TC_02_Remove_Filters  - Step 1: Click on remove filter icon");
        productListPage.clickOnRemoveFilterSelectedIcon("Deerberg");

        log.info("TC_02_Remove_Filters  - Step 2: Verify url is not contain filter's parameter");
        verifyFalse(driver.getCurrentUrl().contains("manufacturer=Deerberg"));

        log.info("TC_02_Remove_Filters  - Step 3: Verify filter was removed");
        verifyTrue(productListPage.isFilterSelectUndisplayed("Deerberg"));


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
