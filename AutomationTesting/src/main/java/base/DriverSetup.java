package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSetup {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    
    public static WebDriver getDriver(String browser) {
        if (driver.get() == null) {
            if (browser.equalsIgnoreCase("chrome")) {
                driver.set(new ChromeDriver());
            } else if (browser.equalsIgnoreCase("firefox")) {
                driver.set(new FirefoxDriver());
            }
            driver.get().manage().window().maximize();
            driver.get().get(ConfigLoader.getProperty("url"));
        }
        return driver.get();
    }
    
    public static void closeDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
    
    public static void switchTab() {
        WebDriver currentDriver = driver.get();
        for (String handle : currentDriver.getWindowHandles()) {
            if (!handle.equals(currentDriver.getWindowHandle())) {
                currentDriver.switchTo().window(handle);
                break;
            }
        }
    }
}
