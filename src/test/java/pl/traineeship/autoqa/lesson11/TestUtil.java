package pl.traineeship.autoqa.lesson11;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestUtil {

	private static final String LOGIN_URL = "https://qa-course-01.andersenlab.com/login";
	private static final String REGISTRATION_URL = "https://qa-course-01.andersenlab.com/registration";
	private static final Duration DEFAULT_WAIT_TIME = Duration.ofSeconds(5);
	private static final By FIRST_NAME_INPUT_LOCATOR = By.xpath("//input[@name='firstName']");
	private static final By LAST_NAME_INPUT_LOCATOR = By.xpath("//input[@name='lastName']");
	private static final By DATE_OF_BIRTH_INPUT_LOCATOR = By.xpath("//input[@name='dateOfBirth']");
	private static final By EMAIL_INPUT_LOCATOR = By.xpath("//input[@name='email']");
	private static final By PASSWORD_INPUT_LOCATOR = By.xpath("//input[@name='password']");
	private static final By PASSWORD_CONFIRMATION_INPUT_LOCATOR = By.xpath("//input[@name='passwordConfirmation']");
	private static final By SUBMIT_BUTTON_LOCATOR = By.xpath("//button[@type='submit']");
	private static final By EDIT_ACCOUNT_LINK_LOCATOR = By.xpath("//a[text()='Edit account']");
	private static final By DELETE_ACCOUNT_LOCATOR = By.xpath("//p[text()='Delete account']");
	private static final By CONFIRM_BUTTON_LOCATOR = By.xpath("//button[text()='Yes']");
	private static final By SIGN_IN_BUTTON_LOCATOR = By.xpath("//button[text()='Sign in']");
	private static final By LOGOUT_LOCATOR = By.xpath("//p[text()='Logout']");

	public static final By MY_ACCOUNT_HEADER_LOCATOR = By.xpath("//h1[text()='My account']");
	public static final String BASE_URL = "https://qa-course-01.andersenlab.com/";
	public static final String TEST_EMAIL_1 = "test_email1@gmail.com";
	public static final String TEST_EMAIL_2 = "test_email2@gmail.com";
	public static final String TEST_EMAIL_3 = "test_email3@gmail.com";
	public static final String TEST_PASSWORD_1 = "test_password1";
	public static final String TEST_PASSWORD_2 = "test_password2";
	public static final String TEST_PASSWORD_3 = "test_password3";

	private static final List<User> TEST_USERS;

	static {
		TEST_USERS = new ArrayList<>();
		TEST_USERS.add(new User("testFirstName1", "testLastName1",
				"01/01/1996", TEST_EMAIL_1, TEST_PASSWORD_1));
		TEST_USERS.add(new User("testFirstName2", "testLastName2",
				"02/02/1996", TEST_EMAIL_2, TEST_PASSWORD_2));
		TEST_USERS.add(new User("testFirstName3", "testLastName3",
				"03/03/1996", TEST_EMAIL_3, TEST_PASSWORD_3));
	}

	private WebDriver driver;
	private WebDriverWait wait;

	public TestUtil() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebDriverWait getWait() {
		return wait;
	}

	public void createTestAccounts() {
		for (User user : TEST_USERS) {
			createAccount(user);
			logOut();
		}
	}

	public void deleteTestAccounts() {
		for (User user : TEST_USERS) {
			logIn(user.email, user.password);
			deleteAccount();
		}
	}

	public void logIn(String email, String password) {
		driver.get(LOGIN_URL);
		driver.findElement(EMAIL_INPUT_LOCATOR).sendKeys(email);
		driver.findElement(PASSWORD_INPUT_LOCATOR).sendKeys(password);
		driver.findElement(SIGN_IN_BUTTON_LOCATOR).click();
		wait.until(ExpectedConditions.urlToBe(BASE_URL));
	}

	public void logOut() {
		driver.get(BASE_URL);
		WebElement logoutElement = wait.until(ExpectedConditions.visibilityOfElementLocated(LOGOUT_LOCATOR));
		logoutElement.click();
		driver.findElement(CONFIRM_BUTTON_LOCATOR).click();
		wait.until(ExpectedConditions.urlToBe(LOGIN_URL));
	}

	private void createAccount(User user) {
		driver.get(REGISTRATION_URL);
		driver.findElement(FIRST_NAME_INPUT_LOCATOR).sendKeys(user.firstName);
		driver.findElement(LAST_NAME_INPUT_LOCATOR).sendKeys(user.lastName);
		WebElement dateInput = driver.findElement(DATE_OF_BIRTH_INPUT_LOCATOR);
		dateInput.sendKeys(user.dateOfBirth);
		dateInput.sendKeys(Keys.RETURN);
		driver.findElement(EMAIL_INPUT_LOCATOR).sendKeys(user.email);
		driver.findElement(PASSWORD_INPUT_LOCATOR).sendKeys(user.password);
		driver.findElement(PASSWORD_CONFIRMATION_INPUT_LOCATOR).sendKeys(user.password);
		driver.findElement(SUBMIT_BUTTON_LOCATOR).click();
		wait.until(ExpectedConditions.urlToBe(BASE_URL));
	}

	private void deleteAccount() {
		driver.get(BASE_URL);
		WebElement editAccountElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(EDIT_ACCOUNT_LINK_LOCATOR));
		editAccountElement.click();
		driver.findElement(DELETE_ACCOUNT_LOCATOR).click();
		driver.findElement(CONFIRM_BUTTON_LOCATOR).click();
		wait.until(ExpectedConditions.urlToBe(LOGIN_URL));
	}

	private record User(String firstName, String lastName, String dateOfBirth, String email, String password) {
	}
}
