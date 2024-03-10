package pl.traineeship.autoqa.lesson13.task2.page;

import static pl.traineeship.autoqa.lesson13.task2.page.AccountPage.BASE_URL;
import static pl.traineeship.autoqa.lesson13.task2.page.LoginPage.DEFAULT_WAIT_TIME;
import static pl.traineeship.autoqa.lesson13.task2.page.LoginPage.LOGIN_URL;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditAccountPage {
	private static final String VALUE_ATTRIBUTE = "value";

	@FindBy(xpath = "//p[text()='Delete account']")
	private WebElement deleteAccountElement;
	@FindBy(xpath = "//button[text()='Yes']")
	private WebElement confirmAccountDeletionButton;
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
	@FindBy(xpath = "//span[text()='Required']")
	private WebElement requiredFieldSpan;

	private WebDriver driver;
	private WebDriverWait wait;

	public EditAccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
	}

	public EditAccountPage clickDeleteAccountButton() {
		deleteAccountElement.click();
		return this;
	}

	public EditAccountPage clickConfirmAccountDeletionButton() {
		confirmAccountDeletionButton.click();
		return this;
	}

	public LoginPage waitUntilAccountDeleted() {
		wait.until(ExpectedConditions.urlToBe(LOGIN_URL));
		return new LoginPage(driver);
	}

	public boolean isEmailInputEnabled() {
		return emailInput.isEnabled();
	}

	public AccountPage waitUntilAccountEdited() {
		wait.until(ExpectedConditions.urlToBe(BASE_URL));
		return new AccountPage(driver);
	}

	public EditAccountPage editAccount(String firstName, String lastName, String dateOfBirth, String password,
			String confirmPassword) {
		return editFirstName(firstName)
				.editLastName(lastName)
				.editDateOfBirth(dateOfBirth)
				.enterPassword(password)
				.enterPasswordConfirmation(confirmPassword)
				.clickSubmitButton();
	}

	public WebElement getRequiredFieldSpan() {
		return wait.until(ExpectedConditions.visibilityOf(requiredFieldSpan));
	}

	public boolean isSubmitButtonEnabled() {
		return submitButton.isEnabled();
	}

	private EditAccountPage editFirstName(String firstName) {
		clearInput(firstNameInput);
		firstNameInput.sendKeys(firstName);
		return this;
	}

	private EditAccountPage editLastName(String lastName) {
		clearInput(lastNameInput);
		lastNameInput.sendKeys(lastName);
		return this;
	}

	private EditAccountPage editDateOfBirth(String dateOfBirth) {
		clearInput(dateOfBirthInput);
		dateOfBirthInput.sendKeys(dateOfBirth);
		dateOfBirthInput.sendKeys(Keys.RETURN);
		return this;
	}

	private EditAccountPage enterPassword(String password) {
		passwordInput.sendKeys(password);
		return this;
	}

	private EditAccountPage enterPasswordConfirmation(String password) {
		passwordConfirmationInput.sendKeys(password);
		return this;
	}

	private EditAccountPage clickSubmitButton() {
		submitButton.click();
		return this;
	}

	// Instead of '.clear()' method which doesn't work on this page
	private void clearInput(WebElement element) {
		while (!element.getAttribute(VALUE_ATTRIBUTE).isEmpty()) {
			element.sendKeys(Keys.BACK_SPACE);
		}
	}
}
