package tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.DriverSetup;
import pages.CartPage;

public class CartTest extends DriverSetup {
	static CartPage cp;
	
	@Parameters("browser")
	@BeforeClass
	public static void setDriver(String browser) throws InterruptedException {
		cp = new CartPage(DriverSetup.getDriver(browser));
	}
	
	@Test (groups = "cart", dependsOnGroups = "search", priority = 1)
	public static void cartAddTest() throws InterruptedException {
		Thread.sleep(500);
		cp.cartAdd();
	}
	
	@Test (groups = "cart", dependsOnGroups = "search", priority = 2)
	public static void modifyQuantity() throws InterruptedException {
		Thread.sleep(500);
		cp.quantityModify();
	}
	
	@Test (groups = "cart", dependsOnGroups = "search", priority = 3)
	public static void deleteFromCart() throws InterruptedException {
		Thread.sleep(1000);
		cp.deleteProductFromCart();
	}
	
	@Test (groups = "cart", dependsOnGroups = "search", priority = 4)
	public static void buyProducts() throws InterruptedException {
		Thread.sleep(2000);
		cp.proceedToBuyFromCart();
		String address = cp.getAddress();
		if(address.contains("Goai")) {
			System.out.println("Address verified");
		}
	}
	
	@Test (groups = "cart", dependsOnGroups = "search", priority = 5)
	public static void placeOrder() {
		boolean canMoveToOrder = cp.buyingIsEnabled();
		if(!canMoveToOrder) {
			System.out.println("cannot order");
		}
	}
		
	@AfterClass
	public static void quitBrowser() {
		closeDriver();
	}
}
