package pl.traineeship.autoqa.lesson13.task2.page;

import static pl.traineeship.autoqa.lesson13.task2.page.AccountPage.BASE_URL;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	public static final String LOGIN_URL = "https://qa-course-01.andersenlab.com/login";
	public static final Duration DEFAULT_WAIT_TIME = Duration.ofSeconds(5);

	@FindBy(xpath = "//input[@name='email']")
	private WebElement emailInput;
	@FindBy(xpath = "//input[@name='password']")
	private WebElement passwordInput;
	@FindBy(xpath = "//button[text()='Sign in']")
	private WebElement signInButton;
	@FindBy(xpath = "//span[text()='Email or password is not valid']")
	private List<WebElement> wrongEmailOrPasswordSpanList;
	@FindBy(xpath = "//span[text()='Invalid email address']")
	private WebElement invalidEmailSpan;
	@FindBy(xpath = "//span[text()='Required']")
	private WebElement requiredFieldSpan;
	@FindBy(xpath = "//a[text()='Registration']")
	private WebElement registrationLink;

	private WebDriver driver;
	private WebDriverWait wait;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
	}

	public LoginPage openLoginPage() {
		driver.get(LOGIN_URL);
		return this;
	}

	public AccountPage waitUntilLoggedIn() {
		wait.until(ExpectedConditions.urlToBe(BASE_URL));
		return new AccountPage(driver);
	}

	public LoginPage logIn(String email, String password) {
		return openLoginPage()
				.enterEmail(email)
				.enterPassword(password)
				.clickSignInButton();
	}

	public List<WebElement> getWrongEmailOrPasswordSpanList() {
		return wait.until(ExpectedConditions.visibilityOfAllElements(wrongEmailOrPasswordSpanList));
	}

	public WebElement getInvalidEmailSpan() {
		return wait.until(ExpectedConditions.visibilityOf(invalidEmailSpan));
	}

	public WebElement getRequiredFieldSpan() {
		return wait.until(ExpectedConditions.visibilityOf(requiredFieldSpan));
	}

	public boolean isSignInButtonEnabled() {
		return signInButton.isEnabled();
	}

	public RegistrationPage clickRegistrationLink() {
		registrationLink.click();
		return new RegistrationPage(driver);
	}

	private LoginPage enterEmail(String email) {
		emailInput.sendKeys(email);
		return this;
	}

	private LoginPage enterPassword(String password) {
		passwordInput.sendKeys(password);
		return this;
	}

	private LoginPage clickSignInButton() {
		signInButton.click();
		return this;
	}
}
