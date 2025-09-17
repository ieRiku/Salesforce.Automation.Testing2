package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class SearchPage {

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;
    JavascriptExecutor js;
    
    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Locators
    @FindBy(id = "twotabsearchtextbox")
    private WebElement searchBox;

    @FindBy(id = "nav-search-submit-text")
    private WebElement searchBtn;
    
    @FindBy(xpath = "//*[starts-with(@id, 'a-autoid-') and contains(@id, '-announce')]/span[2]")
    private WebElement sortDropdown;
    
    @FindBy(xpath = "//*[@id=\"s-result-sort-select_1\"]")
    private WebElement lowToHighOption;
    
    @FindBy(xpath = "//*[contains(text(),'Results')]/following::div[@role='listitem'][1]//h2//span")
    private WebElement firstProduct;
    
    @FindBy(xpath = "//*[@id=\"titleSection\"]//*[@id=\"productTitle\"]")
    private WebElement productTitle;
  
    @FindBy(xpath = "//*[@id=\"corePriceDisplay_desktop_feature_div\"]/div[1]/span[3]/span[2]/span[2]")
    private WebElement productPrice;
    
    @FindBy(xpath = "\"//div[@data-component-type='s-search-result']\"")
    private WebElement allElementsLocated;
    
    @FindBy(xpath = "//div[@id='priceRefinements']//a[span[starts-with(text(),'₹') and contains(text(),'-')]]")
    private List<WebElement> rangeElement;
    
    By rangeLocator = By.xpath("//div[@id='priceRefinements']//a[span[starts-with(text(),'₹') and contains(text(),'-')]]");


    @FindBy(xpath = "//li[@id='p_72/1318476031']/span/div/a/i")
    private WebElement ratingButton;
    
    
    // Actions
    public void searchProduct(String productName) {
        wait.until(ExpectedConditions.visibilityOf(searchBox)).clear();
        searchBox.sendKeys(productName);
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
    }

    public String searchBlank() {
        wait.until(ExpectedConditions.visibilityOf(searchBox)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
        return driver.getTitle();
    }
   
    public void sortLowToHigh() {
		wait.until(ExpectedConditions.elementToBeClickable(sortDropdown)).click();
		wait.until(ExpectedConditions.elementToBeClickable(lowToHighOption)).click();
    }
    
	
	public void selectBrand(String brand) {
	    By brandLocator = By.xpath("//div[contains(@class, 'a-checkbox-fancy')][following-sibling::span[contains(text(), '" + brand + "')]]");
	    wait.until(ExpectedConditions.elementToBeClickable(brandLocator)).click();
	}    
	

    public void clickFirstProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(firstProduct)).click();
    }

    public boolean verifyResultsDisplayed() {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@data-component-type='s-search-result']"))).size() > 0;
    }
    
    public String getSelectedProductTitle() {
    	try {
    		return wait.until(ExpectedConditions.visibilityOf(productTitle)).getText();
    	}
    	catch(Exception e) {
    		return "Product title not avaliable";
    	}
    }

    public String getSelectedProductPrice() {
        try{
            return wait.until(ExpectedConditions.visibilityOf(productPrice)).getText();
        } 
        catch (Exception e) {
            return "Price not available";
        }
    }
    
    // Apply price range using drag and drop on sliders
    public void applyPriceRange() {
    	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(rangeLocator));
    	js = (JavascriptExecutor) driver;
    	WebElement priceFilter = null;
    	for(WebElement e: rangeElement) {
    		priceFilter = e;
    		break;
    	}
    	js.executeScript("window.scrollBy(0, 100);");
    	wait.until(ExpectedConditions.elementToBeClickable(priceFilter)).click();
    }

    // Apply customer ratings using the ratingButton WebElement
    public void applyCustomerRatings() {
    	wait.until(ExpectedConditions.visibilityOf(ratingButton));
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 300);");
        wait.until(ExpectedConditions.elementToBeClickable(ratingButton)).click();
    }
}