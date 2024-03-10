package pl.traineeship.autoqa.lesson13.task1.page;

import static pl.traineeship.autoqa.lesson13.task2.page.LoginPage.DEFAULT_WAIT_TIME;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AndersenHomePage {
	private static final String ANDERSEN_URL = "https://andersenlab.com/";

	@FindBy(xpath = "//div[@class='CookiesPolicy-module--contentWrapper--c9ed1']/button[2]")
	private WebElement acceptCookiesButton;
	@FindBy(xpath = "//section/div/a[@href='/services/web-development']")
	private WebElement webDevelopmentLink;
	@FindBy(xpath = "//a/*[@aria-label='Instagram link']")
	private WebElement instagramIcon;

	private WebDriver driver;
	private WebDriverWait wait;

	public AndersenHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
	}

	public AndersenHomePage openHomePage() {
		driver.get(ANDERSEN_URL);
		return this;
	}

	public AndersenHomePage acceptCookies() {
		wait.until(ExpectedConditions.visibilityOf(acceptCookiesButton)).click();
		return this;
	}

	public boolean isInstagramIconDisplayed() {
		return instagramIcon.isDisplayed();
	}

	public void goToWebDevelopmentPage() {
		webDevelopmentLink.click();
		wait.until(ExpectedConditions.urlMatches(ANDERSEN_URL + ".+"));
	}
}
