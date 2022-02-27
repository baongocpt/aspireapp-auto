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
		log.info("Step 1: Login to web application");
		LoginPage loginPage = new LoginPage(driver, log);
		loginPage.logIn(url, account, password);
		log.info("Step 2: Navigate to 'Inventory' feature");
		HomePage homePage = new HomePage(driver, log);
		homePage.navigateToInventory();
		InventoryPage inventoryPage = new InventoryPage(driver,log);
		log.info("Step 3: From the top-menu bar, select 'Products -> Products' item, then create a new product");
		String current_time = getCurrentTime();
		String new_product_name = product_name + current_time;
		inventoryPage.createNewProduct(new_product_name);
		log.info("Step 4: Update the quantity of new product is more than 10");
		inventoryPage.updateQuantityOfProduct(new_product_name, quantity_value);
		log.info("Step 5: From top-left page, click on 'Application' icon");
		homePage.navigateToHome();
		log.info("Step 6: Navigate to 'Manufacturing' feature, then create a new " +
		         "Manufacturing Order item for the created Product on step #3");
		homePage.navigateToManufacturing();
		ManufacturingPage manufacturingPage = new ManufacturingPage(driver,log);
		manufacturingPage.createNewManufactoringOrder(new_product_name);
		log.info("Step 7: Update the status of new Orders to 'Done' successfully");
		manufacturingPage.updateOrderStatus(new_product_name, "Done");
		log.info("Step 8: Validate the new Manufacturing Order is created with corrected information");
		homePage.navigateToHome();
		homePage.navigateToManufacturing();
		String current_order_status = manufacturingPage.getOrderStatus(new_product_name);
		Assert.assertEquals(current_order_status, "Done");
	}
}
