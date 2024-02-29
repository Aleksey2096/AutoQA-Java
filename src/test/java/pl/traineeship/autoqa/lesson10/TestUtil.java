package pl.traineeship.autoqa.lesson10;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestUtil {
	public static final String FIRST_NAME = "Aliaksei";
	public static final String LAST_NAME = "Maksimenka";
	public static final String DATE_OF_BIRTH = "01/01/1996";
	public static final String EMAIL = "test_email@gmail.com";
	public static final String PASSWORD = "strongpassword";
	public static final String WRONG_PASSWORD = "wrongpassword";
	public static final String LONG_PASSWORD = "superstrongpassword21";
	public static final String BASE_URL = "https://qa-course-01.andersenlab.com/";
	public static final String LOGIN_URL = "https://qa-course-01.andersenlab.com/login";
	public static final String REGISTRATION_URL = "https://qa-course-01.andersenlab.com/registration";
	public static final Duration DEFAULT_WAIT_TIME = Duration.ofSeconds(5);

	public static final By FIRST_NAME_INPUT_LOCATOR = By.xpath("//input[@name='firstName']");
	public static final By LAST_NAME_INPUT_LOCATOR = By.xpath("//input[@name='lastName']");
	public static final By DATE_OF_BIRTH_INPUT_LOCATOR = By.xpath("//input[@name='dateOfBirth']");
	public static final By EMAIL_INPUT_LOCATOR = By.xpath("//input[@name='email']");
	public static final By PASSWORD_INPUT_LOCATOR = By.xpath("//input[@name='password']");
	public static final By PASSWORD_CONFIRMATION_INPUT_LOCATOR = By.xpath("//input[@name='passwordConfirmation']");
	public static final By SUBMIT_BUTTON_LOCATOR = By.xpath("//button[@type='submit']");
	public static final By EDIT_ACCOUNT_LINK_LOCATOR = By.linkText("Edit account");
	public static final By DELETE_ACCOUNT_LOCATOR = By.xpath("//p[text()='Delete account']");
	public static final By CONFIRM_BUTTON_LOCATOR = By.xpath("//button[text()='Yes']");
	public static final By SIGN_IN_BUTTON_LOCATOR = By.xpath("//button[text()='Sign in']");
	public static final By LOGOUT_LOCATOR = By.xpath("//p[text()='Logout']");
	public static final By MY_ACCOUNT_HEADER_LOCATOR = By.xpath("//h1[text()='My account']");
	public static final By ERROR_SPAN_LOCATOR = By.xpath("//span[text()='Email or password is not valid']");
	public static final By REQUIRED_SPAN_LOCATOR = By.xpath("//span[text()='Required']");
	public static final By TOO_LONG_PASSWORD_LOCATOR = By.xpath("//span[text()='Maximum 20 characters']");
	public static final By DIFFERENT_PASSWORDS_LOCATOR = By.xpath("//span[text()='Passwords must match']");

	private WebDriver driver;
	private WebDriverWait wait;

	public TestUtil(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
	}

	public WebDriverWait getWait() {
		return wait;
	}

	public void createAccount() {
		driver.get(REGISTRATION_URL);
		driver.findElement(FIRST_NAME_INPUT_LOCATOR).sendKeys(FIRST_NAME);
		driver.findElement(LAST_NAME_INPUT_LOCATOR).sendKeys(LAST_NAME);
		WebElement dateInput = driver.findElement(DATE_OF_BIRTH_INPUT_LOCATOR);
		dateInput.sendKeys(DATE_OF_BIRTH);
		dateInput.sendKeys(Keys.RETURN);
		driver.findElement(EMAIL_INPUT_LOCATOR).sendKeys(EMAIL);
		driver.findElement(PASSWORD_INPUT_LOCATOR).sendKeys(PASSWORD);
		driver.findElement(PASSWORD_CONFIRMATION_INPUT_LOCATOR).sendKeys(PASSWORD);
		driver.findElement(SUBMIT_BUTTON_LOCATOR).click();
		wait.until(ExpectedConditions.urlToBe(BASE_URL));
	}

	public void deleteAccount() {
		driver.get(BASE_URL);
		WebElement editAccountElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(EDIT_ACCOUNT_LINK_LOCATOR));
		editAccountElement.click();
		driver.findElement(DELETE_ACCOUNT_LOCATOR).click();
		driver.findElement(CONFIRM_BUTTON_LOCATOR).click();
		wait.until(ExpectedConditions.urlToBe(LOGIN_URL));
	}

	public void logIn() {
		driver.get(LOGIN_URL);
		driver.findElement(EMAIL_INPUT_LOCATOR).sendKeys(EMAIL);
		driver.findElement(PASSWORD_INPUT_LOCATOR).sendKeys(PASSWORD);
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
}
