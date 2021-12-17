package pageUIs.dee;

public class ProductDetailPageUI {
	public static final String PRODUCT_NAME = "//h1[@id='prodName']";
	public static final String PRODUCT_PRICE = "//span[@id='priceId']";
	
	public static final String PRODUCT_SIZE_PARENT = "//span[@id='sizeValue']/parent::div[@onclick]";
	public static final String SIZE_AVAILABLE_LIST = "//li[@class='orderable']//span";
	public static final String SELECTED_SIZE_DROPDOWN = "//div[@id='selected-size']";
	public static final String PRODUCT_ONE_SIZE = "//div[contains(@class,'select-size')]//div[contains(@class,'oneSize-container')]";
	
	public static final String ADD2CART_BUTTON = "//input[@id='addToBasket']";

}
