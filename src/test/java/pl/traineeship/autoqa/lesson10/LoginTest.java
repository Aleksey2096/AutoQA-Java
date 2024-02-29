package pl.traineeship.autoqa.lesson10;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static pl.traineeship.autoqa.lesson10.TestUtil.EMAIL;
import static pl.traineeship.autoqa.lesson10.TestUtil.EMAIL_INPUT_LOCATOR;
import static pl.traineeship.autoqa.lesson10.TestUtil.ERROR_SPAN_LOCATOR;
import static pl.traineeship.autoqa.lesson10.TestUtil.LOGIN_URL;
import static pl.traineeship.autoqa.lesson10.TestUtil.MY_ACCOUNT_HEADER_LOCATOR;
import static pl.traineeship.autoqa.lesson10.TestUtil.PASSWORD_INPUT_LOCATOR;
import static pl.traineeship.autoqa.lesson10.TestUtil.SIGN_IN_BUTTON_LOCATOR;
import static pl.traineeship.autoqa.lesson10.TestUtil.WRONG_PASSWORD;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/*
 * 1)Автоматизируйте по два тест-кейса из каждого модуля, которые вы писали для предыдущего домашнего задания.
 */
public class LoginTest {
	private static WebDriver driver;
	private static TestUtil testUtil;

	@BeforeAll
	public static void setUp() {
		driver = DriverManager.create();
		testUtil = new TestUtil(driver);
		testUtil.createAccount();
		testUtil.logOut();
	}

	@Test
	@DisplayName("Verifies that user can login with valid credentials")
	public void testLogin() {
		testUtil.logIn();
		String expectedHeaderText = "My account";
		String actualHeaderText = driver.findElement(MY_ACCOUNT_HEADER_LOCATOR).getText();
		assertEquals(expectedHeaderText, actualHeaderText);
		testUtil.logOut();
	}

	@Test
	@DisplayName("Verifies that user can't login with wrong password")
	public void testLoginWithWrongPassword() {
		driver.get(LOGIN_URL);
		driver.findElement(EMAIL_INPUT_LOCATOR).sendKeys(EMAIL);
		driver.findElement(PASSWORD_INPUT_LOCATOR).sendKeys(WRONG_PASSWORD);
		driver.findElement(SIGN_IN_BUTTON_LOCATOR).click();

		List<WebElement> errorSpanList = testUtil.getWait()
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ERROR_SPAN_LOCATOR));
		String expectedErrorMessage = "Email or password is not valid";
		String actualErrorMessage = errorSpanList.get(0).getText();
		int expectedNumberOfErrorMessages = 2;
		int actualNumberOfErrorMessages = errorSpanList.size();
		boolean isSignInButtonEnabled = driver.findElement(SIGN_IN_BUTTON_LOCATOR).isEnabled();

		assertAll(
				() -> assertEquals(expectedErrorMessage, actualErrorMessage),
				() -> assertEquals(expectedNumberOfErrorMessages, actualNumberOfErrorMessages),
				() -> assertFalse(isSignInButtonEnabled));
	}

	@AfterAll
	public static void tearDown() {
		testUtil.logIn();
		testUtil.deleteAccount();
		driver.quit();
	}
}
