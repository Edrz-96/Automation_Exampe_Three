package test;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.HomePage;

public class IDrawTest {
	private static RemoteWebDriver driver;
	WebDriverWait wait = new WebDriverWait(driver, 3);
	Actions action = new Actions(driver);
	HomePage hp = PageFactory.initElements(driver, HomePage.class);

	@BeforeClass
	public static void init() {
		System.setProperty("webdriver.gecko.driver",
				"src/test/resources/resource/geckodriver-v0.28.0-win64/geckodriver.exe");
		FirefoxOptions fOptions = new FirefoxOptions();
		fOptions.setHeadless(false);
		driver = new FirefoxDriver(fOptions);
		fOptions.addPreference("profile.default_content_setting_values.cookies", 2);
		fOptions.addPreference("network.cookie.cookieBehavior", 2);
		fOptions.addPreference("profile.block_third_party_cookies", true);
		driver.manage().window().setSize(new Dimension(1366, 768));

	}

	@Before
	public void setup() {
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		try {
			driver.get(HomePage.URL);
		} catch (TimeoutException e) {
			System.out.println("Page: " + HomePage.URL + " did not load within 30 seconds!");
		}

	}

	@AfterClass
	public static void tearDown() {
		driver.quit();

	}

	@Test
	public void drawInitals() throws InterruptedException {
		hp.chooseBrush();
		WebElement canvas = driver.findElement(By.cssSelector("#catch"));
		
		// E
		action.moveToElement(canvas).clickAndHold().moveByOffset(0, -200).moveByOffset(100, 0).click()
				.moveToElement(canvas, 0, 0).clickAndHold().moveByOffset(100, 0).click()
				.moveToElement(canvas, 0, 0).clickAndHold().moveByOffset(0, -100).moveByOffset(100, 0).click().moveToElement(canvas, 0, 0).build().perform();
		Thread.sleep(2000);
		// R
		action.moveByOffset(200, 0).clickAndHold().moveByOffset(0, -200).moveByOffset(100, 0).moveByOffset(0,100).moveByOffset(-100, 0).moveByOffset(100, 100).build().perform();
		Thread.sleep(2000);
	}
}