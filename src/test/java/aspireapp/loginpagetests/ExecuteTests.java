package aspireapp.loginpagetests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import aspireapp.base.TestUtilities;
import aspireapp.pages.HomePage;
import aspireapp.pages.InventoryPage;
import aspireapp.pages.LoginPage;
import aspireapp.pages.ManufacturingPage;

public class ExecuteTests extends TestUtilities {
	@Parameters({"url", "account", "password", "product_name", "quantity_value"})
	@Test
	public void Test1(String url, String account, String password, String product_name, String quantity_value) {
		log.info("Starting test1");
		//Login to web application
		LoginPage loginPage = new LoginPage(driver, log);
		loginPage.logIn(url, account, password);
		//Navigate to `Inventory` feature
		HomePage homePage = new HomePage(driver, log);
		homePage.navigateToInventory();
		InventoryPage inventoryPage = new InventoryPage(driver,log);
		//From the top-menu bar, select `Products -> Products` item, then create a new product
		inventoryPage.createNewProduct(product_name);
		//Update the quantity of new product is more than 10
		inventoryPage.updateQuantityOfProduct(product_name, quantity_value);
		//From top-left page, click on `Application` icon
		homePage.navigateToHome();
		//Navigate to `Manufacturing` feature, then create a new Manufacturing Order item for the created Product on step #3
		homePage.navigateToManufacturing();
		ManufacturingPage manufacturingPage = new ManufacturingPage(driver,log);
		manufacturingPage.createNewManufactoringOrder(product_name);
		//Update the status of new Orders to “Done” successfully
		manufacturingPage.updateOrderStatus(product_name, "Done");
		//Validate the new Manufacturing Order is created with corrected information.
		homePage.navigateToHome();
		homePage.navigateToManufacturing();
		String current_order_status = manufacturingPage.getOrderStatus(product_name);
		Assert.assertEquals(current_order_status, "Done");
	}
}
