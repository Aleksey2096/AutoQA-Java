package pl.traineeship.autoqa.lesson10;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static pl.traineeship.autoqa.lesson10.TestUtil.BASE_URL;
import static pl.traineeship.autoqa.lesson10.TestUtil.EDIT_ACCOUNT_LINK_LOCATOR;
import static pl.traineeship.autoqa.lesson10.TestUtil.EMAIL_INPUT_LOCATOR;
import static pl.traineeship.autoqa.lesson10.TestUtil.FIRST_NAME;
import static pl.traineeship.autoqa.lesson10.TestUtil.FIRST_NAME_INPUT_LOCATOR;
import static pl.traineeship.autoqa.lesson10.TestUtil.REQUIRED_SPAN_LOCATOR;
import static pl.traineeship.autoqa.lesson10.TestUtil.SUBMIT_BUTTON_LOCATOR;

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
public class EditAccountTest {
	private static WebDriver driver;
	private static TestUtil testUtil;

	@BeforeAll
	public static void setUp() {
		driver = DriverManager.create();
		testUtil = new TestUtil(driver);
		testUtil.createAccount();
	}

	@Test
	@DisplayName("Verifies that user can't change email address while editing personal account")
	public void testChangeEmail() {
		driver.get(BASE_URL);
		WebElement editAccountElement = testUtil.getWait()
				.until(ExpectedConditions.visibilityOfElementLocated(EDIT_ACCOUNT_LINK_LOCATOR));
		editAccountElement.click();

		boolean isEmailInputEnabled = driver.findElement(EMAIL_INPUT_LOCATOR).isEnabled();

		assertFalse(isEmailInputEnabled);
	}

	@Test
	@DisplayName("Verifies that user can't update account with mandatory field left empty")
	public void testUpdateAccountWithEmptyField() {
		driver.get(BASE_URL);
		WebElement editAccountElement = testUtil.getWait()
				.until(ExpectedConditions.visibilityOfElementLocated(EDIT_ACCOUNT_LINK_LOCATOR));
		editAccountElement.click();
		driver.findElement(FIRST_NAME_INPUT_LOCATOR).sendKeys(Keys.CONTROL, FIRST_NAME, Keys.DELETE);
		driver.findElement(SUBMIT_BUTTON_LOCATOR).click();

		WebElement errorSpan = testUtil.getWait()
				.until(ExpectedConditions.visibilityOfElementLocated(REQUIRED_SPAN_LOCATOR));
		String expectedErrorMessage = "Required";
		String actualErrorMessage = errorSpan.getText();
		boolean isSubmitButtonEnabled = driver.findElement(SUBMIT_BUTTON_LOCATOR).isEnabled();

		assertAll(
				() -> assertEquals(expectedErrorMessage, actualErrorMessage),
				() -> assertFalse(isSubmitButtonEnabled));
	}

	@AfterAll
	public static void tearDown() {
		testUtil.deleteAccount();
		driver.quit();
	}
}
