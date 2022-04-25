package pageUIs.dee;

public class ProductListPageUI {
	public static final String PRODUCTS_LIST = "//div[contains(@class,'product_container')]";
	public static final String NULL_PRODUCT_LIST = "//div[@id='tile_1']";

	public static final String DYNAMIC_PRODUCT_TYPE = "//div[contains(@class,'product_container')][%s]//span[@class='listingProductType']";
	public static final String DYNAMIC_PRODUCT_NAME = "//div[contains(@class,'product_container')][%s]//span[@class='listingProductName']";
	public static final String DYNAMIC_PRODUCT_COLOR = "//div[contains(@class,'product_container')][%s]//div[@class='color-variant-box']";
	public static final String PRODUCT_SIZE_DROPDOWN = "//select[@class='size-select-box']";
	public static final String PRODUCT_ONE_SIZE = "//span[contains(@class,'one-size-product-option')]";
	public static final String DYNAMIC_PRODUCT_PRICE = "//div[contains(@class,'product_container')][%s]//div[@class='product-price-tile']//span[@data-new-price]";
	public static final String DYNAMIC_WISH_LIST_ICON = "//div[contains(@class,'product_container')][%s]//img[contains(@src,'wishlist')]";
	public static final String DYNAMIC_WISH_LIST_SELECTED_ICON = "//div[contains(@class,'product_container')][%s]//img[contains(@src,'select')]";
	public static final String ADD2WISHLIST_MSG = "//div[@id='add-wishlist-success']";

	public static final String DYNAMIC_SHOW_EXPAND_PRODUCT_BUTTON = "//div[contains(@class,'product_container')][%s]//div[@class='show-popup-addToBasket']//span";
	public static final String ADD2CART_BUTTON = "//input[starts-with(@id,'addToBasket')]";

	public static final String DYNAMIC_FILTER_BY_ID = "//div[@id='%s']";
	public static final String DYNAMIC_OPTION_FILTER_BY_ID = "//label[@for='%s']";
	public static final String DYNAMIC_FILTER_SUBMIT_BUTTON = "//ul[@id='%s']//a[@class='btn btnOrange btnFilter width150']";
	public static final String DYNAMIC_FILTER_SELECTED = "//div[contains(.,'%s') and contains(@class,' selected_filter_btn ')]";
	public static final String DYNAMIC_REMOVE_FILTER_SELECTED_ICON = "//div[contains(.,'%s') and contains(@class,' selected_filter_btn ')]/a[contains(@class,'delete-filter icon')]";

	public static final String SEARCH_RESULT_TEXT = "//div[@class='col-xs-12 seo-top']";
}
