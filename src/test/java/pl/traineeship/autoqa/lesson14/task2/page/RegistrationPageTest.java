package pl.traineeship.autoqa.lesson14.task2.page;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pl.traineeship.autoqa.lesson14.task2.util.AllureScreenshotListener;
import pl.traineeship.autoqa.lesson14.task2.util.ScreenshotListener;

/*
	Добавьте аллюр-репортинг к нашим тестам: своему проекту.

	2. Доделать по три автотеста из каждого модуля, на которые писали тест-кейсы в лекции 9.
 */
@Listeners({ScreenshotListener.class, AllureScreenshotListener.class})
public class RegistrationPageTest extends BaseTest {

	private static RegistrationPage registrationPage;

	@BeforeClass
	public void setUpClass() {
		registrationPage = new RegistrationPage(driver);
	}

	@Feature("creating account positive testing")
	@Severity(SeverityLevel.BLOCKER)
	@Description("Verifies that user can create new account with valid credentials")
	@Test(description = "Verifies that user can create new account with valid credentials")
	public void testCreateAccount() {
		AccountPage accountPage = registrationPage
				.createAccount(FIRST_NAME, LAST_NAME, DATE_OF_BIRTH, EMAIL, PASSWORD, PASSWORD)
				.waitUntilAccountCreated();

		String actualHeaderText = accountPage.getTextOfMyAccountHeader();
		String expectedHeaderText = "My account";
		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = BASE_URL;
		softAssert.assertEquals(actualHeaderText, expectedHeaderText,
				"Personal account header is not the same as expected!");
		softAssert.assertEquals(actualUrl, expectedUrl, "URL after creating new account is not the same as expected!");
		softAssert.assertAll();

		accountPage
				.deleteAccount()
				.waitUntilAccountDeleted();
	}

	@Feature("creating account negative testing")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verifies that user can't create new account using password with more than 20 characters")
	@Test(description = "Verifies that user can't create new account using password with more than 20 characters")
	public void testCreateAccountWithTooLongPassword() {
		WebElement tooLongPasswordSpan = registrationPage
				.createAccount(FIRST_NAME, LAST_NAME, DATE_OF_BIRTH, EMAIL, LONG_PASSWORD, LONG_PASSWORD)
				.getTooLongPasswordSpan();

		String expectedErrorMessage = "Maximum 20 characters";
		String actualErrorMessage = tooLongPasswordSpan.getText();
		boolean isSubmitButtonEnabled = registrationPage.isSubmitButtonEnabled();

		softAssert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message is not the same as expected.");
		softAssert.assertFalse(isSubmitButtonEnabled, "Submit button is enabled.");
		softAssert.assertAll();
	}

	@Feature("creating account negative testing")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verifies that user can't create new account using password with less than 8 characters")
	@Test(description = "Verifies that user can't create new account using password with less than 8 characters")
	public void testCreateAccountWithTooShortPassword() {
		WebElement tooShortPasswordSpan = registrationPage
				.createAccount(FIRST_NAME, LAST_NAME, DATE_OF_BIRTH, EMAIL, SHORT_PASSWORD, SHORT_PASSWORD)
				.getTooShortPasswordSpan();

		String expectedErrorMessage = "Minimum 8 characters";
		String actualErrorMessage = tooShortPasswordSpan.getText();
		boolean isSubmitButtonEnabled = registrationPage.isSubmitButtonEnabled();

		softAssert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message is not the same as expected.");
		softAssert.assertFalse(isSubmitButtonEnabled, "Submit button is enabled.");
		softAssert.assertAll();
	}

	@Feature("creating account negative testing")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Verifies that user can't create new account with different values in 'Password' and 'Confirm Password' fields")
	@Test(description = "Verifies that user can't create new account with different values in 'Password' and 'Confirm Password' fields")
	public void testCreateAccountWithDifferentConfirmPassword() {
		WebElement differentPasswordsSpan = registrationPage
				.createAccount(FIRST_NAME, LAST_NAME, DATE_OF_BIRTH, EMAIL, PASSWORD, WRONG_PASSWORD)
				.getDifferentPasswordsSpan();

		String expectedErrorMessage = "Passwords must match";
		String actualErrorMessage = differentPasswordsSpan.getText();
		boolean isSubmitButtonEnabled = registrationPage.isSubmitButtonEnabled();

		softAssert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message is not the same as expected.");
		softAssert.assertFalse(isSubmitButtonEnabled, "Submit button is enabled.");
		softAssert.assertAll();
	}

	@Feature("creating account negative testing")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verifies that user can't create new account with empty first name")
	@Test(description = "Verifies that user can't create new account with empty first name")
	public void testCreateAccountWithEmptyFirstName() {
		WebElement requiredFieldSpan = registrationPage
				.createAccount(EMPTY_STRING, LAST_NAME, DATE_OF_BIRTH, EMAIL, PASSWORD, PASSWORD)
				.getRequiredFieldSpan();

		String expectedErrorMessage = "Required";
		String actualErrorMessage = requiredFieldSpan.getText();
		boolean isSubmitButtonEnabled = registrationPage.isSubmitButtonEnabled();

		softAssert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message is not the same as expected.");
		softAssert.assertFalse(isSubmitButtonEnabled, "Submit button is enabled.");
		softAssert.assertAll();
	}
}
