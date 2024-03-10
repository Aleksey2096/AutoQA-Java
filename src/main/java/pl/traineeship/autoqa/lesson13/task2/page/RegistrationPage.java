package pl.traineeship.autoqa.lesson13.task2.page;

import static pl.traineeship.autoqa.lesson13.task2.page.AccountPage.BASE_URL;
import static pl.traineeship.autoqa.lesson13.task2.page.LoginPage.DEFAULT_WAIT_TIME;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
	private static final String REGISTRATION_URL = "https://qa-course-01.andersenlab.com/registration";

	@FindBy(xpath = "//input[@name='firstName']")
	private WebElement firstNameInput;
	@FindBy(xpath = "//input[@name='lastName']")
	private WebElement lastNameInput;
	@FindBy(xpath = "//input[@name='dateOfBirth']")
	private WebElement dateOfBirthInput;
	@FindBy(xpath = "//input[@name='email']")
	private WebElement emailInput;
	@FindBy(xpath = "//input[@name='password']")
	private WebElement passwordInput;
	@FindBy(xpath = "//input[@name='passwordConfirmation']")
	private WebElement passwordConfirmationInput;
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement submitButton;
	@FindBy(xpath = "//span[text()='Maximum 20 characters']")
	private WebElement tooLongPasswordSpan;
	@FindBy(xpath = "//span[text()='Minimum 8 characters']")
	private WebElement tooShortPasswordSpan;
	@FindBy(xpath = "//span[text()='Passwords must match']")
	private WebElement differentPasswordsSpan;
	@FindBy(xpath = "//span[text()='Required']")
	private WebElement requiredFieldSpan;

	private WebDriver driver;
	private WebDriverWait wait;

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
	}

	public RegistrationPage openRegistrationPage() {
		driver.get(REGISTRATION_URL);
		return this;
	}

	public AccountPage waitUntilAccountCreated() {
		wait.until(ExpectedConditions.urlToBe(BASE_URL));
		return new AccountPage(driver);
	}

	public RegistrationPage createAccount(String firstName, String lastName, String dateOfBirth, String email,
			String password, String confirmPassword) {
		return openRegistrationPage()
				.enterFirstName(firstName)
				.enterLastName(lastName)
				.enterDateOfBirth(dateOfBirth)
				.enterEmail(email)
				.enterPassword(password)
				.enterPasswordConfirmation(confirmPassword)
				.clickSubmitButton();
	}

	public WebElement getTooLongPasswordSpan() {
		return wait.until(ExpectedConditions.visibilityOf(tooLongPasswordSpan));
	}

	public WebElement getTooShortPasswordSpan() {
		return wait.until(ExpectedConditions.visibilityOf(tooShortPasswordSpan));
	}

	public WebElement getDifferentPasswordsSpan() {
		return wait.until(ExpectedConditions.visibilityOf(differentPasswordsSpan));
	}

	public WebElement getRequiredFieldSpan() {
		return wait.until(ExpectedConditions.visibilityOf(requiredFieldSpan));
	}

	public boolean isSubmitButtonEnabled() {
		return submitButton.isEnabled();
	}

	public RegistrationPage enterFirstName(String firstName) {
		firstNameInput.sendKeys(firstName);
		return this;
	}

	public RegistrationPage enterLastName(String lastName) {
		lastNameInput.sendKeys(lastName);
		return this;
	}

	public RegistrationPage enterDateOfBirth(String dateOfBirth) {
		dateOfBirthInput.sendKeys(dateOfBirth);
		dateOfBirthInput.sendKeys(Keys.RETURN);
		return this;
	}

	public RegistrationPage enterEmail(String email) {
		emailInput.sendKeys(email);
		return this;
	}

	public RegistrationPage enterPassword(String password) {
		passwordInput.sendKeys(password);
		return this;
	}

	public RegistrationPage enterPasswordConfirmation(String password) {
		passwordConfirmationInput.sendKeys(password);
		return this;
	}

	private RegistrationPage clickSubmitButton() {
		submitButton.click();
		return this;
	}
}
