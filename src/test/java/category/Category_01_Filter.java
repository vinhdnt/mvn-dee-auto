package category;

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
        log.info("TC_01_Add_Filter - Check and close 'promo-layer'");

        log.info("TC_01_Add_Filter - Step 02: Click on '2nd-category' on 'Top Category'");

        log.info("TC_01_Add_Filter - Step 03: Click on 'color' filter");

        log.info("TC_01_Add_Filter - Step 04: Click on 'blue' variant");

        log.info("TC_01_Add_Filter - Step 05: Click on 'submit filter' button");

        log.info("TC_01_Add_Filter - Step 06: Verify url have filter parameter");

        log.info("TC_01_Add_Filter - Step 07: Verify 'blue' filter be checked");
    }

    @Test
    public void TC_02_Remove_Filter() {

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
