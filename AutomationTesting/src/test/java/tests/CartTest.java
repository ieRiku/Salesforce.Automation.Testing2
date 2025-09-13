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
	public static void setDriver(String browser){
		cp = new CartPage(DriverSetup.getDriver(browser));
	}
	
	@Test (groups = {"regression", "cart"}, dependsOnGroups = "search", priority = 1)
	public static void cartAddTest() {
		cp.cartAdd();
	}
	
	@Test (groups = {"regression", "cart"}, dependsOnGroups = "search", priority = 2)
	public static void modifyQuantity() {
		cp.quantityModify();
	}
	
	@Test (groups = {"regression", "cart"}, dependsOnGroups = "search", priority = 3)
	public static void deleteFromCart() {
		cp.deleteProductFromCart();;
	}
	
	@Test (groups = {"regression", "cart"}, dependsOnGroups = "search", priority = 4)
	public static void buyProducts(){
		cp.proceedToBuyFromCart();
		String address = cp.getAddress();
		if(address.contains("Goai")) {
			System.out.println("Address verified");
		}
	}
	
	@Test (groups = {"regression", "cart"}, dependsOnGroups = "search", priority = 5)
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
