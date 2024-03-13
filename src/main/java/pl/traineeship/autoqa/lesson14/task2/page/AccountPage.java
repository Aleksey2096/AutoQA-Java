package pl.traineeship.autoqa.lesson14.task2.page;

import static pl.traineeship.autoqa.lesson14.task2.page.LoginPage.DEFAULT_WAIT_TIME;
import static pl.traineeship.autoqa.lesson14.task2.page.LoginPage.LOGIN_URL;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Добавьте аллюр-репортинг к нашим тестам: своему проекту.
public class AccountPage {
	public static final String BASE_URL = "https://qa-course-01.andersenlab.com/";
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountPage.class);

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

	@Step("opening account page")
	public AccountPage openAccountPage() {
		LOGGER.info("opening account page");
		driver.get(BASE_URL);
		return this;
	}

	@Step("waiting until logged out")
	public LoginPage waitUntilLoggedOut() {
		LOGGER.info("waiting until logged out");
		wait.until(ExpectedConditions.urlToBe(LOGIN_URL));
		return new LoginPage(driver);
	}

	@Step("logging out")
	public AccountPage logOut() {
		LOGGER.info("logging out");
		return openAccountPage()
				.clickLogOutButton()
				.clickConfirmLogOutButton();
	}

	@Step("clicking edit account button")
	public EditAccountPage clickEditAccountButton() {
		LOGGER.info("clicking edit account button");
		wait.until(ExpectedConditions.visibilityOf(editAccountLink)).click();
		return new EditAccountPage(driver);
	}

	@Step("deleting account")
	public EditAccountPage deleteAccount() {
		LOGGER.info("deleting account");
		return openAccountPage()
				.clickEditAccountButton()
				.clickDeleteAccountButton()
				.clickConfirmAccountDeletionButton();
	}

	@Step("returning text of 'My account' header")
	public String getTextOfMyAccountHeader() {
		LOGGER.info("returning text of 'My account' header");
		return myAccountHeader.getText();
	}

	@Step("returning first name")
	public String getFirstName() {
		LOGGER.info("returning first name");
		return firstNameParagraph.getText();
	}

	@Step("clicking log out button")
	private AccountPage clickLogOutButton() {
		LOGGER.info("clicking log out button");
		wait.until(ExpectedConditions.visibilityOf(logOutElement)).click();
		return this;
	}

	@Step("clicking confirm log out button")
	private AccountPage clickConfirmLogOutButton() {
		LOGGER.info("clicking confirm log out button");
		confirmLogOutButton.click();
		return this;
	}
}
