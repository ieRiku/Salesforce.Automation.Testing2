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

    
    //Implemented dynamic xpath
//    @FindBy(xpath = "/html/body/div[1]/div[1]/span/div/h1/div/div[4]/div/div/form/span/span/span/span")
//    private WebElement sortDropdown;
    
    @FindBy(xpath = "//*[starts-with(@id, 'a-autoid-') and contains(@id, '-announce')]/span[2]")
    private WebElement sortDropdown;

    //Low to high option
//    @FindBy(xpath = "/html/body/div[4]/div/div/ul/li[2]/a")
//    private WebElement lowToHighOption;
    
    @FindBy(xpath = "//*[@id=\"s-result-sort-select_1\"]")
    private WebElement lowToHighOption;
    
//    @FindBy(xpath = "/html/body/div[1]/div[1]/div[1]/div[1]/div/span[1]/div[1]/div[3]/div/div/div/div/span/div/div/div/div[2]/div/div/div[1]/a/h2")
//    private WebElement firstProduct;
    
    @FindBy(xpath = "//*[contains(text(),'Results')]/following::div[@role='listitem'][1]//h2//span")
    private WebElement firstProduct;
    
    @FindBy(xpath = "//*[@id=\"titleSection\"]//*[@id=\"productTitle\"]")
    private WebElement productTitle;

//    @FindBy(className = "a-price-whole")
//    private WebElement productPrice;
  
    @FindBy(xpath = "//*[@id=\"corePriceDisplay_desktop_feature_div\"]/div[1]/span[3]/span[2]/span[2]")
    private WebElement productPrice;
    
    @FindBy(xpath = "\"//div[@data-component-type='s-search-result']\"")
    private WebElement allElementsLocated;
    
//    @FindBy(xpath = "//*[@id='p_36/dynamic-picker-2']/span/a/span")
//    private WebElement rangeElement;
    
    @FindBy(xpath = "//div[@id='priceRefinements']/ul[1]/span/span[2]/li/span/a")
    private WebElement rangeElement;

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
        By brandLocator = By.xpath("//span[text()='" + brand + "']/preceding-sibling::div");
        wait.until(ExpectedConditions.elementToBeClickable(brandLocator)).click();
    }

    public void clickFirstProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(firstProduct)).click();
    }

    public boolean verifyResultsDisplayed() {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@data-component-type='s-search-result']"))).size() > 0;
    }
    
    public String getSelectedProductTitle() {
        return wait.until(ExpectedConditions.visibilityOf(productTitle)).getText();
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
    	js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", rangeElement);
    	wait.until(ExpectedConditions.elementToBeClickable(rangeElement)).click();
    }

    // Apply customer ratings using the ratingButton WebElement
    public void applyCustomerRatings() {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", ratingButton);
        wait.until(ExpectedConditions.elementToBeClickable(ratingButton)).click();
    }
}