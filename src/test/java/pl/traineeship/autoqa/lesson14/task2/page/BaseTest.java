package pl.traineeship.autoqa.lesson14.task2.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import pl.traineeship.autoqa.lesson14.task2.util.DriverSetUp;

// Добавьте браузерные логи, если это потребуется.
public class BaseTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);
	public static final String FIRST_NAME = "Aliaksei";
	public static final String EDITED_FIRST_NAME = "Edited First Name";
	public static final String LAST_NAME = "Maksimenka";
	public static final String DATE_OF_BIRTH = "01/01/1996";
	public static final String EMAIL = "test_email@gmail.com";
	public static final String INVALID_EMAIL = "invalid_email";
	public static final String PASSWORD = "strongpassword";
	public static final String WRONG_PASSWORD = "wrongpassword";
	public static final String SHORT_PASSWORD = "passwor";
	public static final String LONG_PASSWORD = "superstrongpassword21";
	public static final String BASE_URL = "https://qa-course-01.andersenlab.com/";
	public static final String EMPTY_STRING = "";
	public static final String GUINNESS_REGISTER_URL = "https://www.guinnessworldrecords.com/account/register?";
	public static final String TUTORIALS_URL = "https://www.hyrtutorials.com/p/alertsdemo.html";
	public static final String DAY_OF_BIRTH = "20";
	public static final String MONTH_OF_BIRTH = "4";
	public static final String YEAR_OF_BIRTH = "1996";
	public static final String COUNTRY_CODE = "PL";
	public static final String CITY = "Krakow";
	public static final String FINAL_STEP_MESSAGE = "Final step of this task";

	protected static WebDriver driver;
	protected static SoftAssert softAssert;

	@BeforeSuite
	public void setUpSuite() {
		driver = DriverSetUp.getInstance();
		softAssert = new SoftAssert();
	}

	@AfterMethod
	public void tearDownMethod() {
		LogEntries browserLogs = driver.manage().logs().get(LogType.BROWSER);
		for (LogEntry entry : browserLogs.getAll()) {
			LOGGER.info(entry.getMessage());
		}
	}

	@AfterSuite
	public void tearDownSuite() {
		driver.quit();
	}
}
