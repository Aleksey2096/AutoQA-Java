package pl.traineeship.autoqa.lesson13.task1.page;

import static pl.traineeship.autoqa.lesson13.task2.page.LoginPage.DEFAULT_WAIT_TIME;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GuinnessRegisterPage {
	@FindBy(xpath = "//input[@id='FirstName']")
	private WebElement firstNameInput;
	@FindBy(xpath = "//input[@id='LastName']")
	private WebElement lastNameInput;
	@FindBy(xpath = "//input[@id='DateOfBirthDay']")
	private WebElement dayOfBirth;
	@FindBy(xpath = "//input[@id='DateOfBirthMonth']")
	private WebElement monthOfBirth;
	@FindBy(xpath = "//input[@id='DateOfBirthYear']")
	private WebElement yearOfBirth;
	@FindBy(xpath = "//select[@id='Country']")
	private WebElement countrySelect;
	@FindBy(xpath = "//input[@id='State']")
	private WebElement cityInput;
	@FindBy(xpath = "//input[@id='EmailAddress']")
	private WebElement emailInput;
	@FindBy(xpath = "//input[@id='ConfirmEmailAddress']")
	private WebElement confirmEmailInput;
	@FindBy(xpath = "//input[@id='Password']")
	private WebElement passwordInput;
	@FindBy(xpath = "//input[@id='ConfirmPassword']")
	private WebElement confirmPasswordInput;
	@FindBy(xpath = "//span[@class='field-validation-error']")
	private WebElement errorSpan;

	private WebDriver driver;
	private WebDriverWait wait;

	public GuinnessRegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
	}

	public GuinnessRegisterPage enterFirstName(String firstName) {
		firstNameInput.sendKeys(firstName);
		return this;
	}

	public GuinnessRegisterPage enterLastName(String lastName) {
		lastNameInput.sendKeys(lastName);
		return this;
	}

	public GuinnessRegisterPage enterDateOfBirth(String day, String month, String year) {
		dayOfBirth.sendKeys(day);
		monthOfBirth.sendKeys(month);
		yearOfBirth.sendKeys(year);
		return this;
	}

	public GuinnessRegisterPage selectCountry(String countryCode) {
		new Select(countrySelect).selectByValue(countryCode);
		return this;
	}

	public GuinnessRegisterPage enterCity(String city) {
		wait.until(ExpectedConditions.visibilityOf(cityInput)).sendKeys(city);
		return this;
	}

	public GuinnessRegisterPage enterEmail(String email) {
		emailInput.sendKeys(email);
		return this;
	}

	public GuinnessRegisterPage enterConfirmEmail(String email) {
		confirmEmailInput.sendKeys(email);
		return this;
	}

	public GuinnessRegisterPage enterPassword(String password) {
		passwordInput.sendKeys(password);
		return this;
	}

	public GuinnessRegisterPage enterConfirmPassword(String password) {
		confirmPasswordInput.sendKeys(password, Keys.ENTER);
		return this;
	}

	public String getErrorText() {
		return wait.until(ExpectedConditions.visibilityOf(errorSpan)).getText();
	}

	public AlertTutorialPage openAlertTutorialPage() {
		driver.switchTo().window(GoogleSearchPage.getTutorialWindowHandle());
		return new AlertTutorialPage(driver);
	}
}
