package pl.traineeship.autoqa.lesson12;

import static pl.traineeship.autoqa.lesson12.TestUtil.ACCEPT_BUTTON_LOCATOR;
import static pl.traineeship.autoqa.lesson12.TestUtil.CITY;
import static pl.traineeship.autoqa.lesson12.TestUtil.CITY_INPUT_LOCATOR;
import static pl.traineeship.autoqa.lesson12.TestUtil.CLEAR_SEARCH_LOCATOR;
import static pl.traineeship.autoqa.lesson12.TestUtil.CLICK_ME_1_BUTTON_LOCATOR;
import static pl.traineeship.autoqa.lesson12.TestUtil.CLICK_ME_2_BUTTON_LOCATOR;
import static pl.traineeship.autoqa.lesson12.TestUtil.CLICK_ME_3_BUTTON_LOCATOR;
import static pl.traineeship.autoqa.lesson12.TestUtil.CONFIRM_EMAIL_INPUT_LOCATOR;
import static pl.traineeship.autoqa.lesson12.TestUtil.CONFIRM_PASSWORD_INPUT_LOCATOR;
import static pl.traineeship.autoqa.lesson12.TestUtil.CONSENT_BUTTON_LOCATOR;
import static pl.traineeship.autoqa.lesson12.TestUtil.COUNTRY_CODE;
import static pl.traineeship.autoqa.lesson12.TestUtil.COUNTRY_SELECT_LOCATOR;
import static pl.traineeship.autoqa.lesson12.TestUtil.DAY_OF_BIRTH;
import static pl.traineeship.autoqa.lesson12.TestUtil.DAY_OF_BIRTH_LOCATOR;
import static pl.traineeship.autoqa.lesson12.TestUtil.DENY_COOKIES_BUTTON_LOCATOR;
import static pl.traineeship.autoqa.lesson12.TestUtil.EMAIL;
import static pl.traineeship.autoqa.lesson12.TestUtil.EMAIL_INPUT_LOCATOR_2;
import static pl.traineeship.autoqa.lesson12.TestUtil.ERROR_SPAN_LOCATOR;
import static pl.traineeship.autoqa.lesson12.TestUtil.FINAL_STEP_MESSAGE;
import static pl.traineeship.autoqa.lesson12.TestUtil.FIRST_NAME;
import static pl.traineeship.autoqa.lesson12.TestUtil.FIRST_NAME_INPUT_LOCATOR_2;
import static pl.traineeship.autoqa.lesson12.TestUtil.FIRST_NAME_INPUT_LOCATOR_3;
import static pl.traineeship.autoqa.lesson12.TestUtil.FORM_SUBMIT_URL;
import static pl.traineeship.autoqa.lesson12.TestUtil.GOOGLE_SEARCH_URL;
import static pl.traineeship.autoqa.lesson12.TestUtil.GUINNESS_REGISTER_LINK_LOCATOR;
import static pl.traineeship.autoqa.lesson12.TestUtil.GUINNESS_REGISTER_URL;
import static pl.traineeship.autoqa.lesson12.TestUtil.LAST_NAME;
import static pl.traineeship.autoqa.lesson12.TestUtil.LAST_NAME_INPUT_LOCATOR_2;
import static pl.traineeship.autoqa.lesson12.TestUtil.LAST_NAME_INPUT_LOCATOR_3;
import static pl.traineeship.autoqa.lesson12.TestUtil.MONTH_OF_BIRTH;
import static pl.traineeship.autoqa.lesson12.TestUtil.MONTH_OF_BIRTH_LOCATOR;
import static pl.traineeship.autoqa.lesson12.TestUtil.NOTE_DIV_LOCATOR;
import static pl.traineeship.autoqa.lesson12.TestUtil.OUTPUT_DIV_LOCATOR;
import static pl.traineeship.autoqa.lesson12.TestUtil.PASSWORD;
import static pl.traineeship.autoqa.lesson12.TestUtil.PASSWORD_INPUT_LOCATOR_2;
import static pl.traineeship.autoqa.lesson12.TestUtil.SUBMIT_BUTTON_LOCATOR_2;
import static pl.traineeship.autoqa.lesson12.TestUtil.TEXT_AREA_LOCATOR;
import static pl.traineeship.autoqa.lesson12.TestUtil.TUTORIALS_LINK_LOCATOR;
import static pl.traineeship.autoqa.lesson12.TestUtil.TUTORIALS_URL;
import static pl.traineeship.autoqa.lesson12.TestUtil.YEAR;
import static pl.traineeship.autoqa.lesson12.TestUtil.YEAR_OF_BIRTH_LOCATOR;

import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/*
	2. Необходимо автоматизировать сценарий, который показан 
	на видео “Сценарий для автоматизации Лекция 12.mp4”.
 */
public class AutomationScriptTest {
	private TestUtil testUtil;

	@BeforeClass
	public void setUp() {
		testUtil = new TestUtil();
	}

	@Test
	public void testAutomationScript() {

		// 1. open google search page
		testUtil.getDriver().get(GOOGLE_SEARCH_URL);
		String originalWindowHandle = testUtil.getDriver().getWindowHandle();

		// deny cookies
		WebElement denyCookiesButton = testUtil.getWait()
				.until(ExpectedConditions.visibilityOfElementLocated(DENY_COOKIES_BUTTON_LOCATOR));
		denyCookiesButton.click();

		/*
		 * 2. Then enter the following link into the search bar:
		 * https://www.guinnessworldrecords.com/account/register?
		 */
		WebElement textarea = testUtil.getDriver().findElement(TEXT_AREA_LOCATOR);
		textarea.sendKeys(GUINNESS_REGISTER_URL);
		textarea.sendKeys(Keys.ENTER);

		// Open a suitable link in a new tab, which will be displayed in the results.
		WebElement registerLink = testUtil.getDriver().findElement(GUINNESS_REGISTER_LINK_LOCATOR);
		registerLink.sendKeys(Keys.chord(Keys.CONTROL, Keys.RETURN));

		Set<String> windowHandles = testUtil.getDriver().getWindowHandles();
		windowHandles.remove(originalWindowHandle);
		String guinnessWindowHandle = windowHandles.iterator().next();

		/*
		 * 3. Then enter the following link into the search bar:
		 * https://www.hyrtutorials.com/p/alertsdemo.html
		 */
		WebElement clearSearchButton = testUtil.getDriver().findElement(CLEAR_SEARCH_LOCATOR);
		clearSearchButton.click();
		textarea = testUtil.getDriver().findElement(TEXT_AREA_LOCATOR);
		textarea.sendKeys(TUTORIALS_URL);
		textarea.sendKeys(Keys.ENTER);

		// Open a suitable link in a new tab, which will be displayed in the results.
		WebElement alertsTutorialLink = testUtil.getDriver().findElement(TUTORIALS_LINK_LOCATOR);
		alertsTutorialLink.sendKeys(Keys.chord(Keys.CONTROL, Keys.RETURN));

		windowHandles = testUtil.getDriver().getWindowHandles();
		windowHandles.removeAll(Set.of(originalWindowHandle, guinnessWindowHandle));
		String tutorialWindowHandle = windowHandles.iterator().next();

		/*
		 * 4. Open the following link in the active tab:
		 * https://www.w3schools.com/html/tryit.asp?filename=tryhtml_form_submit
		 */
		testUtil.getDriver().get(FORM_SUBMIT_URL);

		// accept cookies
		WebElement acceptCookiesButton = testUtil.getWait()
				.until(ExpectedConditions.visibilityOfElementLocated(ACCEPT_BUTTON_LOCATOR));
		acceptCookiesButton.click();

		/*
		 * In the open tab, fill in the fields with your first and last name and click
		 * the ‘Submit’ button.
		 */
		testUtil.getDriver().switchTo().frame(0);
		WebElement firstNameInput = testUtil.getDriver().findElement(FIRST_NAME_INPUT_LOCATOR_2);
		firstNameInput.clear();
		firstNameInput.sendKeys(FIRST_NAME);
		WebElement lastNameInput = testUtil.getDriver().findElement(LAST_NAME_INPUT_LOCATOR_2);
		lastNameInput.clear();
		lastNameInput.sendKeys(LAST_NAME);
		testUtil.getDriver().findElement(SUBMIT_BUTTON_LOCATOR_2).click();

		// Then output the text of the 'Note' element to the console:
		WebElement noteDiv = testUtil.getWait()
				.until(ExpectedConditions.visibilityOfElementLocated(NOTE_DIV_LOCATOR));
		System.out.println(noteDiv.getText());
		/*
		 * Note: This tutorial will not teach you how servers are processing input.
		 * Processing input is explained in our PHP tutorial.
		 */

		/*
		 * 5. Switch to a tab with the following link open:
		 * https://www.guinnessworldrecords.com/account/register? In this window, fill
		 * in all fields with the appropriate information. In the Password and Confirm
		 * Password fields, enter various random passwords. In order for a notification
		 * to appear that these passwords do not match.
		 */
		testUtil.getDriver().switchTo().window(guinnessWindowHandle);

		testUtil.getDriver().findElement(LAST_NAME_INPUT_LOCATOR_3).sendKeys(LAST_NAME);
		testUtil.getDriver().findElement(FIRST_NAME_INPUT_LOCATOR_3).sendKeys(FIRST_NAME);
		testUtil.getDriver().findElement(DAY_OF_BIRTH_LOCATOR).sendKeys(DAY_OF_BIRTH);
		testUtil.getDriver().findElement(MONTH_OF_BIRTH_LOCATOR).sendKeys(MONTH_OF_BIRTH);
		testUtil.getDriver().findElement(YEAR_OF_BIRTH_LOCATOR).sendKeys(YEAR);
		new Select(testUtil.getDriver().findElement(COUNTRY_SELECT_LOCATOR)).selectByValue(COUNTRY_CODE);
		WebElement stateInput = testUtil.getWait()
				.until(ExpectedConditions.visibilityOfElementLocated(CITY_INPUT_LOCATOR));
		stateInput.sendKeys(CITY);
		testUtil.getDriver().findElement(EMAIL_INPUT_LOCATOR_2).sendKeys(EMAIL);
		testUtil.getDriver().findElement(CONFIRM_EMAIL_INPUT_LOCATOR).sendKeys(EMAIL);
		testUtil.getDriver().findElement(PASSWORD_INPUT_LOCATOR_2).sendKeys(PASSWORD);
		testUtil.getDriver().findElement(CONFIRM_PASSWORD_INPUT_LOCATOR).sendKeys(PASSWORD + PASSWORD, Keys.ENTER);
		WebElement errorSpan = testUtil.getWait()
				.until(ExpectedConditions.visibilityOfElementLocated(ERROR_SPAN_LOCATOR));

		// Print this notification to the console.
		System.out.println(errorSpan.getText()); // 'Confirm password' and 'Password' do not match.

		/*
		 * 6. Switch to a tab with the following link open:
		 * https://www.hyrtutorials.com/p/alertsdemo.html
		 */
		testUtil.getDriver().switchTo().window(tutorialWindowHandle);

		// consent to data use
		WebElement dataUseConsentButton = testUtil.getWait()
				.until(ExpectedConditions.visibilityOfElementLocated(CONSENT_BUTTON_LOCATOR));
		dataUseConsentButton.click();

		/*
		 * Press 'Click me' buttons in turn. After clicking each button, modal windows
		 * will appear on the screen.
		 * 
		 * After clicking on the first button, click “Ok” on the modal window and
		 * display a message in the console in the “Popup box output” module.
		 */
		WebElement outputDiv = testUtil.getDriver().findElement(OUTPUT_DIV_LOCATOR);
		testUtil.getDriver().findElement(CLICK_ME_1_BUTTON_LOCATOR).click();
		testUtil.getDriver().switchTo().alert().accept();

		System.out.println(outputDiv.getText()); // You selected alert popup

		/*
		 * After clicking the second button, click “Cancel” on the modal window and
		 * display a message in the console in the “Popup box output” module.
		 */
		testUtil.getDriver().findElement(CLICK_ME_2_BUTTON_LOCATOR).click();
		testUtil.getDriver().switchTo().alert().dismiss();

		System.out.println(outputDiv.getText()); // You pressed Cancel in confirmation popup

		/*
		 * After clicking on the third button, enter the text “Final step of this task”
		 * in the modal window and click “Ok”. Print a message to the console in the
		 * “Popup box output” module.
		 */
		testUtil.getDriver().findElement(CLICK_ME_3_BUTTON_LOCATOR).click();
		Alert alert = testUtil.getDriver().switchTo().alert();
		alert.sendKeys(FINAL_STEP_MESSAGE);
		alert.accept();

		System.out.println(outputDiv.getText()); // You entered text Final step of this task in propmt popup
	}

	@AfterClass
	public void tearDown() {
		testUtil.getDriver().quit();
	}
}
