package pl.traineeship.autoqa.lesson13.task1.page;

import static pl.traineeship.autoqa.lesson13.task2.page.LoginPage.DEFAULT_WAIT_TIME;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertTutorialPage {
	@FindBy(xpath = "//button[@aria-label='Consent']")
	private WebElement consentButton;
	@FindBy(xpath = "//div[@id='output']")
	private WebElement outputDiv;
	@FindBy(xpath = "//button[@id='alertBox']")
	private WebElement clickMe1Button;
	@FindBy(xpath = "//button[@id='confirmBox']")
	private WebElement clickMe2Button;
	@FindBy(xpath = "//button[@id='promptBox']")
	private WebElement clickMe3Button;

	private WebDriver driver;
	private WebDriverWait wait;

	public AlertTutorialPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
	}

	public AlertTutorialPage consentToDataUse() {
		wait.until(ExpectedConditions.visibilityOf(consentButton)).click();
		return this;
	}

	public AlertTutorialPage clickFirstButton() {
		clickMe1Button.click();
		return this;
	}

	public AlertTutorialPage clickSecondButton() {
		clickMe2Button.click();
		return this;
	}

	public AlertTutorialPage clickThirdButton() {
		clickMe3Button.click();
		return this;
	}

	public AlertTutorialPage acceptAlert() {
		driver.switchTo().alert().accept();
		return this;
	}

	public AlertTutorialPage acceptAlert(String message) {
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(message);
		alert.accept();
		return this;
	}

	public AlertTutorialPage dismissAlert() {
		driver.switchTo().alert().dismiss();
		return this;
	}

	public String getOutputText() {
		return outputDiv.getText();
	}
}
