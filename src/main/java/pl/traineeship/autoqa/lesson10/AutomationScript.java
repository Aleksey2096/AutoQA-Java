package pl.traineeship.autoqa.lesson10;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
	4)Написать программу, которая повторит действия на видео "Сценарий для автоматизации Лекция 10.mp4".
 */
public class AutomationScript {

	private static final String AUT_URL = "http://www.automationpractice.pl/index.php";
	private static final String PRODUCTS_COMPARISON_URL = "controller=products-comparison";
	private static final String SEARCH_VALUE_1 = "Printed chiffon dress";
	private static final String SEARCH_VALUE_2 = "Faded Short";
	private static final String SCROLL_SCRIPT = "window.scrollBy(0,200)";
	private static final String EMPTY_STRING = "";
	private static final By SEARCH_INPUT_LOCATOR = By.id("search_query_top");
	private static final By SUBMIT_BUTTON_LOCATOR = By.xpath("//button[@name='submit_search']");
	private static final By ADD_TO_COMPARE_LINK_LOCATOR = By
			.xpath("//ul[contains(@class,'product_list row list')]//a[@class='add_to_compare']");
	private static final By LIST_VIEW_LOCATOR = By.cssSelector("i.icon-th-list");
	private static final By WOMEN_LINK_LOCATOR = By.xpath("//a[@title='Women']");
	private static final By COMPARE_LINK_LOCATOR = By
			.xpath("//form[@class='compare-form']//button[@type='submit']//span");
	private static final By DYNAMIC_COMPARE_LOCATOR_1 = By.xpath("//strong[@class='total-compare-val' and text()='1']");
	private static final By DYNAMIC_COMPARE_LOCATOR_2 = By.xpath("//strong[@class='total-compare-val' and text()='2']");

	public static void main(String[] args) {
		WebDriver driver = DriverManager.create();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.get(AUT_URL);

		driver.findElement(SEARCH_INPUT_LOCATOR).sendKeys(SEARCH_VALUE_1);

		driver.findElement(SUBMIT_BUTTON_LOCATOR).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(LIST_VIEW_LOCATOR)).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_TO_COMPARE_LINK_LOCATOR)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(DYNAMIC_COMPARE_LOCATOR_1));

		driver.findElement(WOMEN_LINK_LOCATOR).click();

		driver.findElement(SEARCH_INPUT_LOCATOR).sendKeys(SEARCH_VALUE_2);

		driver.findElement(SUBMIT_BUTTON_LOCATOR).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_TO_COMPARE_LINK_LOCATOR)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(DYNAMIC_COMPARE_LOCATOR_2));

		driver.findElement(COMPARE_LINK_LOCATOR).click();
		wait.until(ExpectedConditions.urlContains(PRODUCTS_COMPARISON_URL));

		((JavascriptExecutor) driver).executeScript(SCROLL_SCRIPT, EMPTY_STRING);

		driver.quit();
	}
}
