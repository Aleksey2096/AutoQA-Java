package pl.traineeship.autoqa.lesson10;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static pl.traineeship.autoqa.lesson10.TestUtil.DATE_OF_BIRTH;
import static pl.traineeship.autoqa.lesson10.TestUtil.DATE_OF_BIRTH_INPUT_LOCATOR;
import static pl.traineeship.autoqa.lesson10.TestUtil.DIFFERENT_PASSWORDS_LOCATOR;
import static pl.traineeship.autoqa.lesson10.TestUtil.EMAIL;
import static pl.traineeship.autoqa.lesson10.TestUtil.EMAIL_INPUT_LOCATOR;
import static pl.traineeship.autoqa.lesson10.TestUtil.FIRST_NAME;
import static pl.traineeship.autoqa.lesson10.TestUtil.FIRST_NAME_INPUT_LOCATOR;
import static pl.traineeship.autoqa.lesson10.TestUtil.LAST_NAME;
import static pl.traineeship.autoqa.lesson10.TestUtil.LAST_NAME_INPUT_LOCATOR;
import static pl.traineeship.autoqa.lesson10.TestUtil.LONG_PASSWORD;
import static pl.traineeship.autoqa.lesson10.TestUtil.PASSWORD;
import static pl.traineeship.autoqa.lesson10.TestUtil.PASSWORD_CONFIRMATION_INPUT_LOCATOR;
import static pl.traineeship.autoqa.lesson10.TestUtil.PASSWORD_INPUT_LOCATOR;
import static pl.traineeship.autoqa.lesson10.TestUtil.REGISTRATION_URL;
import static pl.traineeship.autoqa.lesson10.TestUtil.SUBMIT_BUTTON_LOCATOR;
import static pl.traineeship.autoqa.lesson10.TestUtil.TOO_LONG_PASSWORD_LOCATOR;
import static pl.traineeship.autoqa.lesson10.TestUtil.WRONG_PASSWORD;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/*
 * 1)Автоматизируйте по два тест-кейса из каждого модуля, которые вы писали для предыдущего домашнего задания.
 */
public class RegistrationTest {
	private static WebDriver driver;
	private static TestUtil testUtil;

	@BeforeAll
	public static void setUp() {
		driver = DriverManager.create();
		testUtil = new TestUtil(driver);
	}

	@Test
	@DisplayName("Verifies that user can't create new account using password with more than 20 characters")
	public void testCreateAccountWithTooLongPassword() {
		fillRegistrationForm();
		driver.findElement(PASSWORD_INPUT_LOCATOR).sendKeys(LONG_PASSWORD);
		driver.findElement(PASSWORD_CONFIRMATION_INPUT_LOCATOR).sendKeys(LONG_PASSWORD);
		driver.findElement(SUBMIT_BUTTON_LOCATOR).click();

		WebElement errorSpan = testUtil.getWait()
				.until(ExpectedConditions.visibilityOfElementLocated(TOO_LONG_PASSWORD_LOCATOR));
		String expectedErrorMessage = "Maximum 20 characters";
		String actualErrorMessage = errorSpan.getText();
		boolean isSubmitButtonEnabled = driver.findElement(SUBMIT_BUTTON_LOCATOR).isEnabled();

		assertAll(
				() -> assertEquals(expectedErrorMessage, actualErrorMessage),
				() -> assertFalse(isSubmitButtonEnabled));
	}

	@Test
	@DisplayName("Verifies that user can't create new account with different values in 'Password' and 'Confirm Password' fields")
	public void testCreateAccountWithDifferentConfirmPassword() {
		fillRegistrationForm();
		driver.findElement(PASSWORD_INPUT_LOCATOR).sendKeys(PASSWORD);
		driver.findElement(PASSWORD_CONFIRMATION_INPUT_LOCATOR).sendKeys(WRONG_PASSWORD);
		driver.findElement(SUBMIT_BUTTON_LOCATOR).click();

		WebElement errorSpan = testUtil.getWait()
				.until(ExpectedConditions.visibilityOfElementLocated(DIFFERENT_PASSWORDS_LOCATOR));
		String expectedErrorMessage = "Passwords must match";
		String actualErrorMessage = errorSpan.getText();
		boolean isSubmitButtonEnabled = driver.findElement(SUBMIT_BUTTON_LOCATOR).isEnabled();

		assertAll(
				() -> assertEquals(expectedErrorMessage, actualErrorMessage),
				() -> assertFalse(isSubmitButtonEnabled));
	}

	@AfterAll
	public static void tearDown() {
		driver.quit();
	}

	private void fillRegistrationForm() {
		driver.get(REGISTRATION_URL);
		driver.findElement(FIRST_NAME_INPUT_LOCATOR).sendKeys(FIRST_NAME);
		driver.findElement(LAST_NAME_INPUT_LOCATOR).sendKeys(LAST_NAME);
		WebElement dateInput = driver.findElement(DATE_OF_BIRTH_INPUT_LOCATOR);
		dateInput.sendKeys(DATE_OF_BIRTH);
		dateInput.sendKeys(Keys.RETURN);
		driver.findElement(EMAIL_INPUT_LOCATOR).sendKeys(EMAIL);
	}
}
