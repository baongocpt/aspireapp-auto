package aspireapp.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePageObject {

	private By usernameLocator = By.id("login");
	private By passwordLocator = By.id("password");
	private By logInButtonLocator = By.xpath("//button[text()='Log in']");

	public LoginPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	/** Execute log in */
	public void logIn(String url, String account, String password) {
		openUrl(url);
		type(account, usernameLocator);
		type(password, passwordLocator);
		click(logInButtonLocator);
	}
}
