package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.DriverSetup;

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

	@FindBy(xpath = "//div[@class='a-section a-spacing-none a-padding-none']//input[@id='add-to-cart-button']")
	private WebElement addCartButton;
	
	@FindBy(xpath = "//span[@id='productTitle']")
	private WebElement productTitle;
	
	@FindBy(xpath = "//span[@class='a-size-medium a-text-bold'][normalize-space()='Added to cart']")
	private WebElement successMessage;
	
	@FindBy(xpath = "//span[@class='a-button a-button-primary attach-button-large attach-primary-cart-button']//input[@type='submit']")
	private WebElement goToCart;
	
//	@FindBy(xpath = "(//span[@class='a-truncate-cut'][contains(text(),\"HP\")])[1]")
//	private WebElement cartProductTitleElement;
	
	@FindBy(xpath = "(//div[@class='a-row sc-list-item sc-java-remote-feature'])[1]//span[@class='a-truncate-cut']")
	private WebElement cartProductTitleElement;
	
	
	@FindBy(xpath = "(//span[@class='a-icon a-icon-small-add'])[1]")
	private WebElement increaseProductCount;
	
	@FindBy(xpath = "(//span[@class='a-icon a-icon-small-remove'])[1]")
	private WebElement decreaseProductCount;
	
	@FindBy(xpath = "(//span[@class='a-icon a-icon-small-trash'])[1]")
	private WebElement deleteFromCart;

	@FindBy(xpath = "//input[@name='proceedToRetailCheckout']")
	private WebElement proceedToBuy;
	
	@FindBy(xpath = "//span[@id='deliver-to-address-text']")
	private WebElement address;

	@FindBy(xpath = "//span[@id='checkout-primary-continue-button-id-announce']")
	private WebElement orderPlace;
	
	
	
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

	public void clickGoToCart() {
		wait.until(ExpectedConditions.elementToBeClickable(goToCart));
		actions.moveToElement(goToCart).click().perform();
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
	
	
	public void cartAdd() throws InterruptedException {
		clickAddToCart();
		String title = getProductTitle();
		String message = getSuccessMessage();
		clickGoToCart();
		String cartTitle = getCartProductTitle();
//		Thread.sleep(2000);
//		increaseProductCount();
//		Thread.sleep(2000);
//		decreaseProductCount();
//		Thread.sleep(2000);

		System.out.println("Product Title: " + title);
		System.out.println("Success Message: " + message);
		System.out.println("Cart Product Title: " + cartTitle);
	}
	
	public void quantityModify() throws InterruptedException {
		increaseProductCount();
		Thread.sleep(2000);
		decreaseProductCount();
		Thread.sleep(2000);
	}
	
	
	public void proceedToBuyFromCart() {
		wait.until(ExpectedConditions.elementToBeClickable(proceedToBuy));
		proceedToBuy.click();
	}
	
	public String getAddress() {
		wait.until(ExpectedConditions.visibilityOf(address));
		return address.getText();
	}
	
	public boolean buyingIsEnabled() {
		wait.until(ExpectedConditions.visibilityOf(orderPlace));
		return orderPlace.isEnabled();
	}
	
	public static void main(String[] args) {
		CartPage cp = new CartPage(DriverSetup.getDriver("chrome"));
		;
	}
}
