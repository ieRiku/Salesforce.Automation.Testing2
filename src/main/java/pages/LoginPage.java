package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage {
	WebDriver driver;
	WebDriverWait wait;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}
	
	public LoginPage() {
		this.driver = null;
	}
	
    @FindBy(xpath = "//button[@class='a-button-text']")
    private WebElement submitButton;
    
    @FindBy(id = "nav-link-accountList-nav-line-1")
    private WebElement loginButton;
    
    @FindBy(id = "ap_email_login")
    private WebElement emailField;
    
    @FindBy(xpath = "//input[@class='a-button-input']")
    private WebElement continueButton;
    
    @FindBy(id = "ap_password")
    private WebElement passwordField;
    
    @FindBy(xpath = "//input[@id='signInSubmit' and @class='a-button-input']")
    private WebElement submitLoginButton;
	
    @FindBy(css = ".a-alert-content")
    private WebElement loginErrorBox;
    
    @FindBy(xpath = "//*[@id=\"auth-error-message-box\"]/div/h4")
    private WebElement errorBox;
    
    //..............................................//
    public void clickSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField)).clear();
        emailField.sendKeys(email);
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField)).clear();
        passwordField.sendKeys(password);
    }

    public void clickSubmitLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(submitLoginButton)).click();
    }

    public boolean isLoginErrorDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(loginErrorBox)).isDisplayed();
    }
    
    public void navigateToUrl(String url) {
    	driver.navigate().to(url);
    }
    
    public boolean loginButtonDisplayed() {
    	return wait.until(ExpectedConditions.visibilityOf(loginButton)).isDisplayed();
    }
    
    public boolean loginButtonEnabled() {
    	return wait.until(ExpectedConditions.visibilityOf(loginButton)).isEnabled();
    }
}
