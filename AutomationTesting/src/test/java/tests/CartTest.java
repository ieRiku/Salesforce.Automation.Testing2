package tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.DriverSetup;
import pages.CartPage;

public class CartTest extends DriverSetup {
	CartPage cp;
	
	@Parameters("browser")
	@BeforeClass
	public void setDriver(String browser){
		cp = new CartPage(DriverSetup.getDriver(browser));
	}
	
	@Parameters("browser")
	@Test (groups = {"regression", "cart"}, priority = 9)
	public void cartAddTest(String browser) {
		cp.cartAdd(browser);
	}
	
	@Test (groups = {"regression", "cart"}, priority = 10)
	public void modifyQuantity() {
		cp.quantityModify();
	}
	
	@Test (groups = {"regression", "cart"}, priority = 11)
	public void deleteFromCart() {
		cp.deleteProductFromCart();;
	}
	
	@Test (groups = {"regression", "cart"}, priority = 12)
	public void buyProducts(){
		cp.proceedToBuyFromCart();
		String address = cp.getAddress();
		if(address.contains("Goai")) {
			System.out.println("Address verified");
		}
	}
	
	@Test (groups = {"regression", "cart"}, priority = 13)
	public void placeOrder() {
		boolean canMoveToOrder = cp.buyingIsEnabled();
		if(!canMoveToOrder) {
			System.out.println("cannot order");
		}
	}
		
	@AfterClass
	public void quitBrowser() {
		closeDriver();
	}
}
