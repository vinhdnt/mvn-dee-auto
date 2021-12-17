package pageUIs.dee;

public class BasePageUI {
	
	public static final String PROMO_LAYER = "//div[@data-wps-popup-content]";
	public static final String PROMO_LAYER_CLOSE_BUTTON = "//div[@data-wps-popup-content]//div[@aria-label='close']";
	public static final String MY_ACCOUNT_LINK_HEADER = "//a[@id='checkInNav']";
	public static final String WISH_LIST_ICON_HEADER = "//a[@id='wishlist-icon']";
	public static final String LOGOUT_LINK_HEADER = "//span[starts-with(@id,'logoutLink')]";
	public static final String DYNAMIC_MAIN_CATEGORY_HEADER = "//li[contains(@class,'main-cat')][%s]//a[@class='main_catg_link']";
	public static final String TOP_NAVIGATION = "//div[@id='topNavigation']//ul[@id='nav-md']";

	public static final String CART_ICON_HEADER = "//li[@id='cart-icon']//div[contains(@class,'icon-image')]";
	public static final String CART_ITEM_COUNT_HEADER = "//span[@id='bask-item-count']";
	public static final String CART_TOTAL_PRICE_HEADER = "//span[@id='basket-item-totalAmount']";
	public static final String DYNAMIC_REMOVE_ICON_CART_SLIDE  = "//div[@id='basketItem_%s']//img[@class='trashbin_icon']";
	public static final String LOGOUT_FIELD  = "//a[contains(@href,'/user/logout')]";

	public static final String MINI_CART_SLIDE = "//div[@id='shop-cart-slide-layer']";
	public static final String CLOSE_CART_SLIDE_BUTTON = "//a[contains(@class,'icon-close-cart')]";
	public static final String GO_TO_CART_BUTTON = "//a[contains(@class,'btn purchase')]";
	public static final String PRODUCT_ADDED_NAME = "//div[@class='added-product-container']//div[contains(@class,'product-name')]";
	public static final String PRODUCT_ADDED_PANEL = "//div[contains(@id,'basketItem')]";
	
	public static final String PRODUCT_ADDED_SIZE = "//span[@class='added-product-size']";
	public static final String PRODUCT_ADDED_PRICE = "//span[contains(@class, 'price price__value')]";
	
	public static final String ALL_URL = "//*[@href]";

}
