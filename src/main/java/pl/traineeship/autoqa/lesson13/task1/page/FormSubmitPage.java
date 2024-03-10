package pl.traineeship.autoqa.lesson13.task1.page;

import static pl.traineeship.autoqa.lesson13.task2.page.LoginPage.DEFAULT_WAIT_TIME;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FormSubmitPage {
	@FindBy(xpath = "//div[@id='accept-choices']")
	private WebElement acceptCookiesButton;
	@FindBy(xpath = "//input[@id='fname']")
	private WebElement firstNameInput;
	@FindBy(xpath = "//input[@id='lname']")
	private WebElement lastNameInput;
	@FindBy(xpath = "//input[@type='submit']")
	private WebElement submitButton;
	@FindBy(xpath = "//body[@class='w3-container']/div[2]")
	private WebElement noteDiv;

	private WebDriver driver;
	private WebDriverWait wait;

	public FormSubmitPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
	}

	public FormSubmitPage acceptCookies() {
		wait.until(ExpectedConditions.visibilityOf(acceptCookiesButton)).click();
		return this;
	}

	public FormSubmitPage switchToInnerFrame() {
		driver.switchTo().frame(0);
		return this;
	}

	public FormSubmitPage enterFirstName(String firstName) {
		firstNameInput.clear();
		firstNameInput.sendKeys(firstName);
		return this;
	}

	public FormSubmitPage enterLastName(String lastName) {
		lastNameInput.clear();
		lastNameInput.sendKeys(lastName);
		return this;
	}

	public FormSubmitPage clickSubmitButton() {
		submitButton.click();
		return this;
	}

	public String getNoteText() {
		return wait.until(ExpectedConditions.visibilityOf(noteDiv)).getText();
	}

	public GuinnessRegisterPage openGuinnessRegisterPage() {
		driver.switchTo().window(GoogleSearchPage.getGuinnessWindowHandle());
		return new GuinnessRegisterPage(driver);
	}
}
