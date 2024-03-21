package pl.traineeship.autoqa.lesson16;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import pl.traineeship.autoqa.lesson16.Swiper.Direction;

public class ApiDemos {

	public static final int DEFAULT_WAIT_TIME = 50;

	private static final By VIEWS = MobileBy.xpath("//android.widget.TextView[@content-desc='Views']");
	private static final By CHANGE_DATE = MobileBy.xpath("//android.widget.Button[@content-desc='change the date']");
	private static final By DIALOG = MobileBy.xpath("//android.widget.TextView[@content-desc='1. Dialog']");
	private static final By DATE_WIDGETS = MobileBy.xpath("//android.widget.TextView[@content-desc='Date Widgets']");
	private static final By WEB_VIEW_3 = MobileBy.xpath("//android.widget.TextView[@content-desc='WebView3']");
	private static final By ANIMATION = MobileBy.xpath("//android.widget.TextView[@content-desc='Animation']");
	private static final By VIEWS_MENU_ITEMS = MobileBy
			.xpath("//android.widget.TextView[@resource-id='android:id/text1']");
	private static final By SELECTED_DAY = MobileBy.xpath("//android.view.View[contains(@content-desc,'selected')]");
	private static final By FOLLOWING_DAY = MobileBy
			.xpath("//android.view.View[contains(@content-desc,'selected')]/following-sibling::android.view.View[1]");
	private static final By PICK_DATE_OK_BUTTON = MobileBy
			.xpath("//android.widget.Button[@resource-id='android:id/button1']");
	private static final By CHANGE_TIME_SPINNER = MobileBy
			.xpath("//android.widget.Button[@content-desc='change the time (spinner)']");
	private static final By HOURS_INPUT = MobileBy
			.xpath("(//android.widget.EditText[@resource-id='android:id/numberpicker_input'])[1]");
	private static final By MINUTES_INPUT = MobileBy
			.xpath("(//android.widget.EditText[@resource-id='android:id/numberpicker_input'])[2]");
	private static final By TIME_INDICATOR_INPUT = MobileBy
			.xpath("(//android.widget.EditText[@resource-id='android:id/numberpicker_input'])[3]");
	private static final By PICK_TIME_OK_BUTTON = MobileBy
			.xpath("//android.widget.Button[@resource-id='android:id/button1']");
	private static final By TEXT_SWITCHER = MobileBy.xpath("//android.widget.TextView[@content-desc='TextSwitcher']");
	private static final By NEXT_BUTTON = MobileBy.xpath("//android.widget.Button[@content-desc='Next']");
	private static final By COUNTER_VALUE = MobileBy.xpath(
			"//android.widget.TextSwitcher[@resource-id='io.appium.android.apis:id/switcher']/android.widget.TextView");

	private AppiumDriver<WebElement> driver;
	private WebDriverWait wait;
	private Swiper swiper;

	public ApiDemos(AppiumDriver<WebElement> driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
		swiper = new Swiper(driver);
	}

	public ApiDemos clickOnViewsButton() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(VIEWS)).click();
		return this;
	}

	public ApiDemos clickOnDateWidgetsButton() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(DATE_WIDGETS)).click();
		return this;
	}

	public ApiDemos clickOnDialogButton() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(DIALOG)).click();
		return this;
	}

	public ApiDemos clickOnChangeDateButton() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(CHANGE_DATE)).click();
		return this;
	}

	public ApiDemos clickOnChangeTimeSpinnerButton() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(CHANGE_TIME_SPINNER)).click();
		return this;
	}

	public ApiDemos pickFollowingDay() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(FOLLOWING_DAY)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(PICK_DATE_OK_BUTTON)).click();
		return this;
	}

	public ApiDemos setTime(String hours, String minutes, String timeIndicator) {
		Actions action = new Actions(driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(HOURS_INPUT));
		longPressToElement(HOURS_INPUT);
		action.sendKeys(hours).perform();
		longPressToElement(MINUTES_INPUT);
		action.sendKeys(minutes).perform();
		longPressToElement(TIME_INDICATOR_INPUT);
		action.sendKeys(timeIndicator).perform();
		driver.findElement(PICK_TIME_OK_BUTTON).click();
		return this;
	}

	public String getSelectedDay() {
		clickOnChangeDateButton();
		String selectedDay = driver.findElement(SELECTED_DAY).getAttribute("content-desc").split(" ")[0];
		driver.findElement(PICK_DATE_OK_BUTTON).click();
		return selectedDay;
	}

	public String getTimeString() {
		clickOnChangeTimeSpinnerButton();
		String hours = driver.findElement(HOURS_INPUT).getText();
		String minutes = driver.findElement(MINUTES_INPUT).getText();
		String timeIndicator = driver.findElement(TIME_INDICATOR_INPUT).getText();
		driver.findElement(PICK_TIME_OK_BUTTON).click();
		return hours + ":" + minutes + " " + timeIndicator;
	}

	public ApiDemos openTextSwitcherPage() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(ANIMATION));
		swiper.swipeUntilElementFound(Direction.UP, TEXT_SWITCHER);
		driver.findElement(TEXT_SWITCHER).click();
		return this;
	}

	public ApiDemos clickOnNextButton(int numberOfTimes) {
		WebElement nextButtonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(NEXT_BUTTON));
		for (int i = 0; i < numberOfTimes; ++i) {
			nextButtonElement.click();
		}
		return this;
	}

	public int getCounterValue() {
		return Integer.parseInt(driver.findElement(COUNTER_VALUE).getText());
	}

	public int countViewsMenuItems() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(ANIMATION));
		Set<String> viewsMenuItems = new HashSet<>();
		addVisibleMenuItemsToSet(viewsMenuItems);
		while (driver.findElements(WEB_VIEW_3).isEmpty()) {
			swiper.swipe(Direction.UP);
			addVisibleMenuItemsToSet(viewsMenuItems);
		}
		return viewsMenuItems.size();
	}

	private void addVisibleMenuItemsToSet(Set<String> viewsMenuItems) {
		for (WebElement menuItem : driver.findElements(VIEWS_MENU_ITEMS)) {
			viewsMenuItems.add(menuItem.getText());
		}
	}

	private void longPressToElement(By locator) {
		TouchAction<?> action = new TouchAction<>(driver);
		LongPressOptions longPressOptions = LongPressOptions.longPressOptions()
				.withElement(ElementOption.element(driver.findElement(locator)))
				.withDuration(Duration.ofSeconds(2));
		action.longPress(longPressOptions).perform();
	}
}
