package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class CartPage {
	WebDriver driver = null;
	WebDriverWait wait;
	Actions actions;
	
	public CartPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		this.actions = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	public CartPage() {
		this.driver = null;
	}

//	@FindBy(xpath = "//div[@class='a-section a-spacing-none a-padding-none']//input[@id='add-to-cart-button']")
//	private WebElement addCartButton;
	
	@FindBy(xpath = "//*[@id='a-accordion-auto-6']//*[@id='add-to-cart-button']")
	private WebElement addCartButton;
	
	@FindBy(xpath = "//span[@id='productTitle']")
	private WebElement productTitle;
	
	@FindBy(xpath = "//span[@class='a-size-medium a-text-bold'][normalize-space()='Added to cart']")
	private WebElement successMessage;
	
	@FindBy(xpath = "//span[@class='a-button a-button-primary attach-button-large attach-primary-cart-button']//input[@type='submit']")
	private WebElement goToCart;
	
	@FindBy(xpath = "(//div[@class='a-row sc-list-item sc-java-remote-feature'])[1]//span[@class='a-truncate-cut']")
	private WebElement cartProductTitleElement;
	
	
	@FindBy(xpath = "(//span[@class='a-icon a-icon-small-add'])[1]")
	private WebElement increaseProductCount;
	
	@FindBy(xpath = "(//span[@class='a-icon a-icon-small-remove'])[1]")
	private WebElement decreaseProductCount;
	
//	@FindBy(xpath = "(//span[@class='a-icon a-icon-small-trash'])[1]")
//	private WebElement deleteFromCart;
	
	@FindBy(xpath = "(//div[@class='a-row sc-list-item sc-java-remote-feature'])[1]/div[4]/div/div[3]/div[1]/span[2]/span")
	private WebElement deleteFromCart;

	@FindBy(xpath = "//input[@name='proceedToRetailCheckout']")
	private WebElement proceedToBuy;
	
//	@FindBy(xpath = "//span[@id='sc-buy-box-ptc-button-announce']")
//	private WebElement proceedToBuy;
	
	@FindBy(xpath = "//span[@id='deliver-to-address-text']")
	private WebElement address;

	@FindBy(xpath = "//span[@id='checkout-primary-continue-button-id-announce']")
	private WebElement orderPlace;
	
	@FindBy(xpath = "//div[@id='nav-cart-text-container']/span[2]")
	private WebElement goToCartButton;

	
	
	
	// Methods to interact with each WebElement using Actions
	public void clickAddToCart() {
		wait.until(ExpectedConditions.elementToBeClickable(addCartButton));
		actions.moveToElement(addCartButton).click().perform();
	}

	public String getProductTitle() {
		wait.until(ExpectedConditions.visibilityOf(productTitle));
		return productTitle.getText();
	}

	public String getSuccessMessage() {
		wait.until(ExpectedConditions.visibilityOf(successMessage));
		return successMessage.getText();
	}

	public void clickGoToCart(String browser) {
		if(browser.equalsIgnoreCase("firefox")) {
			wait.until(ExpectedConditions.elementToBeClickable(goToCartButton));
			actions.moveToElement(goToCartButton).click().perform();
		}
		else if (browser.equalsIgnoreCase("chrome")) {
			wait.until(ExpectedConditions.elementToBeClickable(goToCart));
			actions.moveToElement(goToCart).click().perform();
		}
	}

	public String getCartProductTitle() {
		wait.until(ExpectedConditions.visibilityOf(cartProductTitleElement));
		return cartProductTitleElement.getText();
	}

	public void increaseProductCount() {
		wait.until(ExpectedConditions.elementToBeClickable(increaseProductCount));
		actions.moveToElement(increaseProductCount).click().perform();
	}

	public void decreaseProductCount() {
		wait.until(ExpectedConditions.elementToBeClickable(decreaseProductCount));
		actions.moveToElement(decreaseProductCount).click().perform();
	}
	
	public void deleteProductFromCart() {
		wait.until(ExpectedConditions.elementToBeClickable(deleteFromCart));
		actions.moveToElement(deleteFromCart).click().perform();
	}
	
	
	public void cartAdd(String browser){
		clickAddToCart();
		String title = getProductTitle();
		String message = null;
		if(browser.equalsIgnoreCase("chrome")) {
			message = getSuccessMessage();
		}
		clickGoToCart(browser);
		String cartTitle = null;
		cartTitle = getCartProductTitle();

		System.out.println("Product Title: " + title);
		System.out.println("Success Message: " + message);
		System.out.println("Cart Product Title: " + cartTitle);
	}
	
	public void quantityModify(){
		increaseProductCount();
		decreaseProductCount();
	}
	
	
//	public void proceedToBuyFromCart() {
//		wait.until(ExpectedConditions.elementToBeClickable(proceedToBuy));
//		proceedToBuy.click();
//	}
	
	public void proceedToBuyFromCart() {
		driver.navigate().refresh();
	    wait.until(ExpectedConditions.elementToBeClickable(proceedToBuy));
	    Actions actions = new Actions(driver);
	    actions.moveToElement(proceedToBuy).click().perform();
	}
	
	public String getAddress() {
		wait.until(ExpectedConditions.visibilityOf(address));
		return address.getText();
	}
	
	public boolean buyingIsEnabled() {
		wait.until(ExpectedConditions.visibilityOf(orderPlace));
		return orderPlace.isEnabled();
	}
	
}
