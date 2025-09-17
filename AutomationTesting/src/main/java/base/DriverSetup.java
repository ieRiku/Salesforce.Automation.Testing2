package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverSetup {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    
    public static WebDriver getDriver(String browser) {
        if (driver.get() == null) {
        	if (browser.equalsIgnoreCase("chrome")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                options.addArguments("--window-size=1280,720");
                driver.set(new ChromeDriver(options));
                
            } else if (browser.equalsIgnoreCase("firefox")) {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless");
                options.addArguments("--window-size=1280,720");
                driver.set(new FirefoxDriver(options));
                
            } else if (browser.equalsIgnoreCase("edge")) {
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--headless");
                options.addArguments("--window-size=1920,1080");
                driver.set(new EdgeDriver(options));
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
