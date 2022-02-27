package aspireapp.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePageObject {

	private By discussButton = By.xpath("//a[@data-menu-xmlid='mail.menu_root_discuss']");
	private By inventoryButton = By.xpath("//a[@data-menu-xmlid='stock.menu_stock_root']");
	private By manufacturingButton = By.xpath("//a[@data-menu-xmlid='mrp.menu_mrp_root']");
	private By applicationButton = By.xpath("//a[@title='Home menu']");

	public HomePage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	public void navigateToDiscuss() {
		click(discussButton);
	}

	public void navigateToInventory() {
		click(inventoryButton);
	}

	public void navigateToManufacturing() {
		click(manufacturingButton);
	}

	public void navigateToHome() {
		click(applicationButton);
	}
}
