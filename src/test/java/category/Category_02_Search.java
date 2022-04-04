package category;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.HomePageObject;
import pageObjects.ProductListPageObject;

public class Category_02_Search extends BaseTest {
    WebDriver driver;

    @Parameters({ "browser", "url" })
    @BeforeClass
    public void initBrowser(String browserName, String url) {
        log.info("Pre-condition - Step 01:Init browser and go to Homepage to: " + url);
        driver = getBrowserDriver(browserName, url);
        log.info("Browser name and version is: " + getBrowserInitName());
        homePage = PageGeneratorManager.getHomePage(driver);
    }

    @Test
    public void TC_01_Search_With_Correct_Keyword() {
        log.info("TC_01_Search_With_Correct_Keyword  - Step 1: Click on accept 'Cookie Consent'");
        homePage.clickOnAcceptCookieConsentButton();

        log.info("TC_01_Search_With_Correct_Keyword  - Step 2: Click on 'Search' button");
        homePage.clickOnSearchButton();

        log.info("TC_01_Search_With_Correct_Keyword  - Step 3: Input search keyword: 'deerberg'");
        homePage.inputSearchKeyword("deerberg");

        log.info("TC_01_Search_With_Correct_Keyword  - Step 4: Click on 'Search' button");
        productListPage = homePage.clickOnSearchButton();

        log.info("TC_01_Search_With_Correct_Keyword - Check and close 'promo-layer'");
        productListPage.checkAndClosePromoLayer(driver);

        log.info("TC_01_Search_With_Correct_Keyword  - Step 5:  Verify url contain filter's parameter");
        verifyTrue(driver.getCurrentUrl().contains("fullTextSearch?queryString=deerberg"));

        log.info("TC_01_Search_With_Correct_Keyword  - Step 6:  Verify search result title is displayed");
        verifyTrue(productListPage.isSearchResultDisplayed());

        log.info("TC_01_Search_With_Correct_Keyword  - Step 7:  Verify 'product list' are displayed");
        verifyTrue(productListPage.isProductsListDisplayed());

    }




    @AfterClass(alwaysRun = true)
    public void afterClass() {
        log.info("Close browser");
        cleanBrowserAndDriver();
    }

    HomePageObject homePage;
    ProductListPageObject productListPage;
}
