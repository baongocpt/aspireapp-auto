package aspireapp.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePageObject {

	private By overviewButton = By.xpath("//button[@title='Overview']");
	private By operationsButton = By.xpath("//button[@title='Operations']");
	private By productsButton = By.xpath("//button[@title='Products']");
	private By reportingButton = By.xpath("//button[@title='Reporting']");
	private By configurationButton = By.xpath("//button[@title='Configuration']");
	private By productsItem = By.xpath("//a[text()='Products']");
	private By createProductButton = By.xpath("//button[@title='Create record']");
	private By productNameInput = By.xpath("//input[@placeholder='e.g. Cheese Burger']");
	private By saveProductButton = By.xpath("//button[@title='Save record']");
	private By discardProductButton = By.xpath("//button[@title='Discard record']");
	private By actionButton = By.xpath("//button[@data-original-title='Additional actions']");
	private By deleteButton = By.xpath("//a[text()='Delete']");
	private By okButton = By.xpath("//span[text()='Ok']/parent::button");
	private By searchProductInput = By.xpath("//input[@title='Search for records']");
	private By searchByProductItem = By.xpath("//em[text()='Product']/parent::a[contains(text(),'Search')]");
	private By selectAllProductsCheckbox = By.xpath("//th[@class='o_list_record_selector']");
	private By listViewProductButton = By.xpath("//button[@aria-label='View list']");
	private By kanbanViewProductButton = By.xpath("//button[@aria-label='View kanban']");
	private By updateQuantityButton = By.xpath("//button[@name='action_update_quantity_on_hand']/span[text()='Update Quantity']");
	private By quantityValueInput = By.xpath("//input[@name='inventory_quantity']");
	private By applyQuatityUpdateButton = By.xpath("//input[@name='action_apply_inventory']");
	private By createQuatityButton = By.xpath("//button[@data-original-title='Create record']");

	public InventoryPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	public void clickOverviewButton() {
		click(overviewButton);
	}

	public void clickOperationsButton() {
		click(operationsButton);
	}

	public void clickProductsButton() {
		click(productsButton);
	}

	public void clickReportingButton() {
		click(reportingButton);
	}

	public void clickConfigurationButton() {
		click(configurationButton);
	}

	// In Products Dropdown List
	public void clickProductsItem() {
		click(productsItem);		
	}

	// In Products Item
	public void clickCreateButton() {
		click(createProductButton);
	}

	public void inputProductName(String product_name) {
		type(product_name, productNameInput);
	}

	public void clickSaveButton() {
		click(saveProductButton);
	}

	public void discardProduct() {
		click(discardProductButton);
	}

	public void listViewProduct() {
		click(listViewProductButton);
	}

	public void kanbanViewProduct() {
		click(kanbanViewProductButton);
	}

	public void selectAllProducts() {
		click(selectAllProductsCheckbox);
	}
	
	public void clickActionButton() {
		click(actionButton);
	}

	public void clickDeleteButton() {
		click(deleteButton);
	}

	public void clickOkButton() {
		click(okButton);
	}

	public void clickCreateQuantityButton() {
		click(createQuatityButton);
	}
	
	public void clickUpdateQuantityButton() {
		click(updateQuantityButton);
	}

	public void applyQuatityUpdate() {
		click(applyQuatityUpdateButton);
	}
	
	public void searchProduct(String product_name) {
		type(product_name, searchProductInput);
		click(searchByProductItem);
	}

	public void clickProductName(String product_name) {
		String product_name_xpath = String.format("//td[@title='%s']", product_name);
		click(By.xpath(product_name_xpath));
	}

	public void updateQuantityValue(String quantity_value) {
		click(quantityValueInput);
		type(quantity_value, quantityValueInput);
	}

	public void navigateToProductItems() {
		clickProductsButton();
		clickProductsItem();
	}

	public void createNewProduct(String product_name) {
		navigateToProductItems();
		clickCreateButton();
		inputProductName(product_name);
		clickSaveButton();
	}

	public void deleteProduct(String product_name) {
		navigateToProductItems();
		searchProduct(product_name);
		listViewProduct();
		selectAllProducts();
		clickActionButton();
		clickDeleteButton();
		clickOkButton();
		kanbanViewProduct();
	}

	public void updateQuantityOfProduct(String product_name, String quantity_value) {
		navigateToProductItems();
		searchProduct(product_name);
		listViewProduct();
		clickProductName(product_name);
		clickUpdateQuantityButton();
		try {
			updateQuantityValue(quantity_value);
			applyQuatityUpdate();
		} catch (Exception e) {
			clickCreateQuantityButton();
			updateQuantityValue(quantity_value);
			clickSaveButton();
		}
	}
}
