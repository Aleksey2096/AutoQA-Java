package pl.traineeship.autoqa.lesson13.task2.page;

import static pl.traineeship.autoqa.lesson13.task2.page.LoginPage.DEFAULT_WAIT_TIME;
import static pl.traineeship.autoqa.lesson13.task2.page.LoginPage.LOGIN_URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountPage {
	public static final String BASE_URL = "https://qa-course-01.andersenlab.com/";

	@FindBy(xpath = "//p[text()='Logout']")
	private WebElement logOutElement;
	@FindBy(xpath = "//button[text()='Yes']")
	private WebElement confirmLogOutButton;
	@FindBy(xpath = "//a[text()='Edit account']")
	private WebElement editAccountLink;
	@FindBy(xpath = "//h1[text()='My account']")
	private WebElement myAccountHeader;
	@FindBy(xpath = "//div[@class='mb-4'][1]/p[2]")
	private WebElement firstNameParagraph;

	private WebDriver driver;
	private WebDriverWait wait;

	public AccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
	}

	public AccountPage openAccountPage() {
		driver.get(BASE_URL);
		return this;
	}

	public LoginPage waitUntilLoggedOut() {
		wait.until(ExpectedConditions.urlToBe(LOGIN_URL));
		return new LoginPage(driver);
	}

	public AccountPage logOut() {
		return openAccountPage()
				.clickLogOutButton()
				.clickConfirmLogOutButton();
	}

	public EditAccountPage clickEditAccountButton() {
		wait.until(ExpectedConditions.visibilityOf(editAccountLink)).click();
		return new EditAccountPage(driver);
	}

	public EditAccountPage deleteAccount() {
		return openAccountPage()
				.clickEditAccountButton()
				.clickDeleteAccountButton()
				.clickConfirmAccountDeletionButton();
	}

	public String getTextOfMyAccountHeader() {
		return myAccountHeader.getText();
	}

	public String getFirstName() {
		return firstNameParagraph.getText();
	}

	private AccountPage clickLogOutButton() {
		wait.until(ExpectedConditions.visibilityOf(logOutElement)).click();
		return this;
	}

	private AccountPage clickConfirmLogOutButton() {
		confirmLogOutButton.click();
		return this;
	}
}
