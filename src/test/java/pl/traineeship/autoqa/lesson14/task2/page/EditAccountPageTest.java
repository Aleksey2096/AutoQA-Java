package pl.traineeship.autoqa.lesson14.task2.page;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pl.traineeship.autoqa.lesson14.task2.util.AllureScreenshotListener;
import pl.traineeship.autoqa.lesson14.task2.util.ScreenshotListener;
/*
	Добавьте аллюр-репортинг к нашим тестам: своему проекту.

	2. Доделать по три автотеста из каждого модуля, на которые писали тест-кейсы в лекции 9.
 */
@Listeners({ScreenshotListener.class, AllureScreenshotListener.class})
public class EditAccountPageTest extends BaseTest {

	private static EditAccountPage editAccountPage;
	private static AccountPage accountPage;
	private static LoginPage loginPage;
	private static RegistrationPage registrationPage;

	@BeforeClass
	public void setUpClass() {
		editAccountPage = new EditAccountPage(driver);
		accountPage = new AccountPage(driver);
		loginPage = new LoginPage(driver);
		registrationPage = new RegistrationPage(driver);
		registrationPage
				.createAccount(FIRST_NAME, LAST_NAME, DATE_OF_BIRTH, EMAIL, PASSWORD, PASSWORD)
				.waitUntilAccountCreated();
	}

	@BeforeMethod
	public void setUpMethod() {
		accountPage
				.openAccountPage()
				.clickEditAccountButton();
	}

	@Feature("edit account positive testing")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verifies that user can edit account using valid credentials")
	@Test(description = "Verifies that user can edit account using valid credentials")
	public void testEditAccount() {
		String actualFirstName = editAccountPage
				.editAccount(EDITED_FIRST_NAME, LAST_NAME, DATE_OF_BIRTH, PASSWORD, PASSWORD)
				.waitUntilAccountEdited()
				.getFirstName();

		assertEquals(actualFirstName, EDITED_FIRST_NAME, "Edited first name is not the same as expected.");
	}

	@Feature("edit account negative testing")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Verifies that user can't change email address while editing personal account")
	@Test(description = "Verifies that user can't change email address while editing personal account")
	public void testChangeEmail() {
		boolean isEmailInputEnabled = editAccountPage.isEmailInputEnabled();

		assertFalse(isEmailInputEnabled, "Email input field is enabled.");
	}

	@Feature("edit account positive testing")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verifies that user doesn't have to enter 'Password' and 'Confirm Password' values while editing account")
	@Test(description = "Verifies that user doesn't have to enter 'Password' and 'Confirm Password' values while editing account")
	public void testEditAccountWithEmptyPasswordFields() {
		editAccountPage
				.editAccount(FIRST_NAME, LAST_NAME, DATE_OF_BIRTH, EMPTY_STRING, EMPTY_STRING)
				.waitUntilAccountEdited();

		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = BASE_URL;
		assertEquals(actualUrl, expectedUrl, "URL after editing account is not the same as expected!");
	}

	@Feature("edit account negative testing")
	@Severity(SeverityLevel.BLOCKER)
	@Description("Verifies that user can't edit account with mandatory field left empty")
	@Test(description = "Verifies that user can't edit account with mandatory field left empty")
	public void testEditAccountWithEmptyLastNameField() {
		WebElement requiredFieldSpan = editAccountPage
				.editAccount(FIRST_NAME, EMPTY_STRING, DATE_OF_BIRTH, PASSWORD, PASSWORD)
				.getRequiredFieldSpan();

		String expectedErrorMessage = "Required";
		String actualErrorMessage = requiredFieldSpan.getText();
		boolean isSubmitButtonEnabled = editAccountPage.isSubmitButtonEnabled();

		softAssert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message is not the same as expected.");
		softAssert.assertFalse(isSubmitButtonEnabled, "Submit button is enabled.");
		softAssert.assertAll();
	}

	@Feature("edit account positive testing")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verifies that user can delete account")
	@Test(description = "Verifies that user can delete account")
	public void testDeleteAccount() {
		accountPage
				.deleteAccount()
				.waitUntilAccountDeleted();

		String actualErrorMessage = loginPage
				.logIn(EMAIL, PASSWORD)
				.getWrongEmailOrPasswordSpanList().get(0).getText();
		String expectedErrorMessage = "Email or password is not valid";

		assertEquals(actualErrorMessage, expectedErrorMessage, "Error message is not the same as expected.");

		registrationPage
				.createAccount(FIRST_NAME, LAST_NAME, DATE_OF_BIRTH, EMAIL, PASSWORD, PASSWORD)
				.waitUntilAccountCreated();
	}

	@AfterClass
	public void tearDownClass() {
		accountPage
				.deleteAccount()
				.waitUntilAccountDeleted();
	}
}
