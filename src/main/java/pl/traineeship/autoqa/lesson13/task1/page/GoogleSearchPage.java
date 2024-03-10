package pl.traineeship.autoqa.lesson13.task1.page;

import static pl.traineeship.autoqa.lesson13.task2.page.LoginPage.DEFAULT_WAIT_TIME;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSearchPage {
	private static final String GOOGLE_SEARCH_URL = "https://www.google.com/search";
	private static final String FORM_SUBMIT_URL = "https://www.w3schools.com/html/tryit.asp?filename=tryhtml_form_submit";

	private static String originalWindowHandle;
	private static String guinnessWindowHandle;
	private static String tutorialWindowHandle;

	@FindBy(xpath = "//div[3]/div[1]/button[1]")
	private WebElement denyCookiesButton;
	@FindBy(xpath = "//div[3]/div[1]/div/span")
	private WebElement clearSearchSpan;
	private By textAreaLocator = By.xpath("//textarea[@role='combobox']");
	private By firstFoundLinkLocator = By.xpath("//div[@id='search']//a");

	private WebDriver driver;
	private WebDriverWait wait;

	public GoogleSearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
	}

	public static String getGuinnessWindowHandle() {
		return guinnessWindowHandle;
	}

	public static String getTutorialWindowHandle() {
		return tutorialWindowHandle;
	}

	public GoogleSearchPage openSearchPage() {
		driver.get(GOOGLE_SEARCH_URL);
		return this;
	}

	public GoogleSearchPage setOriginalWindowHandle() {
		originalWindowHandle = driver.getWindowHandle();
		return this;
	}

	public GoogleSearchPage denyCookies() {
		wait.until(ExpectedConditions.visibilityOf(denyCookiesButton)).click();
		return this;
	}

	public GoogleSearchPage enterIntoSearchBar(String url) {
		WebElement textArea = driver.findElement(textAreaLocator);
		textArea.sendKeys(url);
		textArea.sendKeys(Keys.ENTER);
		return this;
	}

	public GoogleSearchPage openFirstLinkInNewTab() {
		WebElement firstLink = driver.findElement(firstFoundLinkLocator);
		if (System.getProperty("os.name").contains("Windows")) {
			firstLink.sendKeys(Keys.chord(Keys.CONTROL, Keys.RETURN));
		} else {
			firstLink.sendKeys(Keys.chord(Keys.COMMAND, Keys.RETURN));
		}
		return this;
	}

	public GoogleSearchPage setGuinnessWindowHandle() {
		Set<String> windowHandles = driver.getWindowHandles();
		windowHandles.remove(originalWindowHandle);
		guinnessWindowHandle = windowHandles.iterator().next();
		return this;
	}

	public GoogleSearchPage clearSearchBar() {
		clearSearchSpan.click();
		return this;
	}

	public GoogleSearchPage setTutorialWindowHandle() {
		Set<String> windowHandles = driver.getWindowHandles();
		windowHandles.removeAll(Set.of(originalWindowHandle, guinnessWindowHandle));
		tutorialWindowHandle = windowHandles.iterator().next();
		return this;
	}

	public FormSubmitPage openFormSubmitPage() {
		driver.get(FORM_SUBMIT_URL);
		return new FormSubmitPage(driver);
	}
}
