package pl.traineeship.autoqa.lesson16;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import io.appium.java_client.AppiumDriver;

public class BaseTest {
	public static final int EXPECTED_NUMBER_OF_MENU_ITEMS = 42;
	public static final int EXPECTED_COUNTER_VALUE = 5;
	public static final String HOURS = "11";
	public static final String MINUTES = "11";
	public static final String TIME_INDICATOR = "PM";
	public static final String EXPECTED_TIME_STRING = "11:11 PM";
	public static final String FIRST_DAY = "1";

	protected static AppiumDriver<WebElement> driver;
	protected static SoftAssert softAssert;

	@BeforeSuite
	public void setUpSuite() {
		driver = DriverSetUp.getInstance();
		softAssert = new SoftAssert();
	}

	@AfterSuite
	public void tearDownSuite() {
		driver.quit();
	}
}
