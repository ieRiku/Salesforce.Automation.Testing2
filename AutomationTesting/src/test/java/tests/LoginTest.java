package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.ConfigLoader;
import base.DriverSetup;
import pages.LoginPage;

public class LoginTest extends DriverSetup{
	static LoginPage lp;
	
	@Parameters("browser")
	@BeforeClass
	public static void setDriver(String browser) {
		ConfigLoader.loadConfig();
		lp = new LoginPage(DriverSetup.getDriver(browser));
	}

	@Test (groups = {"smoke", "regression", "login"}, priority=3)
	public static void validLoginTest(){
		lp.clickLoginButton();
		lp.enterEmail(ConfigLoader.getProperty("username"));
		lp.clickContinue();
		lp.enterPassword(ConfigLoader.getProperty("password"));
		lp.clickSubmitLogin();
		
		String actualName = lp.returnUserName();
	    String expectedName = "Alex";
	    
	    Assert.assertTrue(actualName.contains(expectedName), "Unexpected Name");
	}

	@Test (groups = {"smoke", "regression", "login"}, priority=1)
	public static void invalidLoginTest(){
		lp.clickSubmitButton();	// modified
		lp.clickLoginButton();
		lp.enterEmail("someone@gmail.com");
		lp.clickContinue();
		lp.enterPassword("pass1234");
		lp.clickSubmitLogin();
		lp.navigateGetUrl(ConfigLoader.getProperty("url"));
		
		String actualName = lp.returnUserName();
	    String expectedName = "Alex";
	    
	    Assert.assertFalse(actualName.contains(expectedName), "Unexpected Name");
	}

	@Test (groups = {"regression", "login"}, priority=2)
	public static void verifyLoginTest() {
		lp.navigateToUrl(ConfigLoader.getProperty("url"));
		System.out.println(lp.loginButtonEnabled());
		System.out.println(lp.loginButtonDisplayed());
		
		String actualUrl = lp.returnPageUrl();
	    String expectedUrlPart = "https://www.amazon.in";
	    
		Assert.assertTrue(actualUrl.contains(expectedUrlPart), "Unexpected URL ");
	}
}
