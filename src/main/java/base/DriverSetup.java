package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSetup {
	static WebDriver driver = null;	
	static ConfigLoader cl;
	
	public static WebDriver getDriver(String browser) {
		if(driver == null) {
			if(browser.equalsIgnoreCase("chrome")) {
				driver = new ChromeDriver();
			}
			else if(browser.equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver();
			}
			driver.manage().window().maximize();
			driver.get(ConfigLoader.getProperty("url"));
		}
		return driver;
	}
	
	public static void closeDriver() {
		driver.quit();
		driver=null;
	}

	public static void switchTab() {
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(driver.getWindowHandle())) {
				driver.switchTo().window(handle);
				break;
			}
		}
	}
}
