package aspireapp.base;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserDriverFactory {

	private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	private String browser;
	private Logger log;
	private String resources_path;
	private String chromedriver_name;
	

	public BrowserDriverFactory(String browser, Logger log) {
		this.browser = browser.toLowerCase();
		this.log = log;
		this.resources_path = "src/main/resources/";
		this.chromedriver_name = "chromedriver";
	}

	public WebDriver createDriver() {
		// Create driver
		log.info("Create driver: " + browser);
		
		String os = System.getProperty("os.name");
		if (os.toLowerCase().contains("windows")) {
			this.chromedriver_name = this.chromedriver_name + ".exe";
		}

		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", resources_path + chromedriver_name);
			driver.set(new ChromeDriver());
			break;

		default:
			System.out.println("Do not know how to start: " + browser + ", starting chrome.");
			System.setProperty("webdriver.chrome.driver", resources_path + chromedriver_name);
			driver.set(new ChromeDriver());
			break;
		}

		return driver.get();
	}
}
