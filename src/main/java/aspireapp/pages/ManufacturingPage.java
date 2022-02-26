package aspireapp.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ManufacturingPage extends BasePageObject {

	private By createButton = By.xpath("//button[@data-original-title='Create record']");
	private By productInput = By.xpath("//div[@name='product_id']//descendant::input");
	private By saveButton = By.xpath("//button[@title='Save record']");
	private By confirmSaveButton = By.xpath("//span[text()='Create']/parent::button");
	private By confirmOrderButton = By.xpath("//button[@name='action_confirm']");
	private By markAsDoneOrderButton = By.xpath("//button[@name='button_mark_done' and @class='btn btn-primary']");
	private By searchOrderInput = By.xpath("//input[@title='Search for records']");
	private By searchByProductItem = By.xpath("//em[text()='Product']/parent::a[contains(text(),'Search')]");
	private By manufactoringPageButton = By.xpath("//a[@data-menu-xmlid='mrp.menu_mrp_root']");
	private By manufactoringOrdersButton = By.xpath("//a[text()='Manufacturing Orders']/parent::li");
	private By okButton = By.xpath("//span[text()='Ok']/parent::button");
	private By applyButton = By.xpath("//span[text()='Apply']/parent::button");
	private By removeSearchFilter = By.xpath("//span[text()='Manufacturing Orders']/parent::div/following-sibling::i[@title='Remove']");
	
	public ManufacturingPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	public void clickCreateButton() {
		click(createButton);
	}

	public void clickSaveButton() {
		click(saveButton);
	}

	public void clickOkButton() {
		click(okButton);
	}

	public void clickApplyButton() {
		click(applyButton);
	}
	
	public void clickConfirmSaveButton() {
		click(confirmSaveButton);
	}

	public void clickConfirmOrderButton() {
		click(confirmOrderButton);
	}

	public void clickMarkAsDoneOrderButton() {
		click(markAsDoneOrderButton);
	}
	
	public void clickRemoveSearchFilter() {
		click(removeSearchFilter);
	}
	
	public void inputProductId(String product_name) {
		type(product_name, productInput);
	}

	public void searchOrderByProduct(String product_name) {
		type(product_name, searchOrderInput);
		click(searchByProductItem);
	}

	public void navigateToManufactoringPage() {
		click(manufactoringPageButton);
	}

	public void clickManufactoringOrdersButton() {
		click(manufactoringOrdersButton);
	}

	public void createNewManufactoringOrder(String product_name) {
		navigateToManufactoringPage();
		clickCreateButton();
		inputProductId(product_name);
		clickSaveButton();
		try {
			clickConfirmSaveButton();
			clickSaveButton();
		} catch (Exception e) {
			//It's fine
		}
		clickManufactoringOrdersButton();
	}

	public void clickOrderByProductName(String product_name) {
		String order_by_product_xpath = String.format("//td[@title='%s']", product_name);
		click(By.xpath(order_by_product_xpath));
	}

	public void updateOrderStatus (String product_name, String new_status) {
		String current_status = getOrderStatus(product_name);
		clickOrderByProductName(product_name);
		if (new_status.equals("Confirmed")) {
			if (current_status.equals("Draft")) {
				clickConfirmOrderButton();
			}
		} else if (new_status.equals("Done")) {
			if (current_status.equals("Confirmed")) {
				clickMarkAsDoneOrderButton();
			} else if (current_status.equals("Draft")) {
				clickConfirmOrderButton();
				clickMarkAsDoneOrderButton();
			}
			clickOkButton();
			clickApplyButton();
		}
		clickManufactoringOrdersButton();
	}

	public String getOrderStatus (String product_name) {
		clickRemoveSearchFilter();
		searchOrderByProduct(product_name);
		String order_status_xpath = String.format(
				"//td[@title='%s']/following-sibling::td/span[@name='state']",
				product_name);
		try {
			return text(By.xpath(order_status_xpath));
		} catch (Exception e) {
			return null;
		}
	}
}
