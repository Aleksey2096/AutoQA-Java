package pl.traineeship.autoqa.lesson14.task2.page;

import static org.testng.Assert.assertEquals;

import java.util.List;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
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
public class LoginPageTest extends BaseTest {

	private static LoginPage loginPage;

	@BeforeClass
	public void setUpClass() {
		loginPage = new LoginPage(driver);
		loginPage
				.openLoginPage()
				.clickRegistrationLink()
				.createAccount(FIRST_NAME, LAST_NAME, DATE_OF_BIRTH, EMAIL, PASSWORD, PASSWORD)
				.waitUntilAccountCreated()
				.logOut()
				.waitUntilLoggedOut();
	}

	@Feature("login positive testing")
	@Severity(SeverityLevel.BLOCKER)
	@Description("Verifies that user can login with valid credentials")
	@Test(description = "Verifies that user can login with valid credentials")
	public void testLogin() {
		AccountPage accountPage = loginPage
				.logIn(EMAIL, PASSWORD)
				.waitUntilLoggedIn();

		String actualHeaderText = accountPage.getTextOfMyAccountHeader();
		String expectedHeaderText = "My account";
		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = BASE_URL;
		softAssert.assertEquals(actualHeaderText, expectedHeaderText,
				"Personal account header is not the same as expected!");
		softAssert.assertEquals(actualUrl, expectedUrl, "URL after login is not the same as expected!");
		softAssert.assertAll();

		accountPage
				.logOut()
				.waitUntilLoggedOut();
	}

	@Feature("login negative testing")
	@Severity(SeverityLevel.BLOCKER)
	@Description("Verifies that user can't login with wrong password")
	@Test(description = "Verifies that user can't login with wrong password")
	public void testLoginWithWrongPassword() {
		List<WebElement> errorSpanList = loginPage
				.logIn(EMAIL, WRONG_PASSWORD)
				.getWrongEmailOrPasswordSpanList();

		String actualErrorMessage = errorSpanList.get(0).getText();
		String expectedErrorMessage = "Email or password is not valid";
		int actualNumberOfErrorMessages = errorSpanList.size();
		int expectedNumberOfErrorMessages = 2;
		boolean isSignInButtonEnabled = loginPage.isSignInButtonEnabled();

		softAssert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message is not the same as expected.");
		softAssert.assertEquals(actualNumberOfErrorMessages, expectedNumberOfErrorMessages,
				"Number of error messages is not the same as expected.");
		softAssert.assertFalse(isSignInButtonEnabled, "Sign in button is enabled.");
		softAssert.assertAll();
	}

	@Feature("login negative testing")
	@Severity(SeverityLevel.BLOCKER)
	@Description("Verifies that user can't login with invalid email")
	@Test(description = "Verifies that user can't login with invalid email")
	public void testLoginWithInvalidEmail() {
		WebElement invalidEmailSpan = loginPage
				.logIn(INVALID_EMAIL, PASSWORD)
				.getInvalidEmailSpan();

		String actualErrorMessage = invalidEmailSpan.getText();
		String expectedErrorMessage = "Invalid email address";
		boolean isSignInButtonEnabled = loginPage.isSignInButtonEnabled();

		softAssert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message is not the same as expected.");
		softAssert.assertFalse(isSignInButtonEnabled, "Sign in button is enabled.");
		softAssert.assertAll();
	}

	@Feature("login negative testing")
	@Severity(SeverityLevel.BLOCKER)
	@Description("Verifies that user can't login with empty email")
	@Test(description = "Verifies that user can't login with empty email")
	public void testLoginWithEmptyEmail() {
		WebElement requiredFieldSpan = loginPage
				.logIn(EMPTY_STRING, PASSWORD)
				.getRequiredFieldSpan();

		String actualErrorMessage = requiredFieldSpan.getText();
		String expectedErrorMessage = "Required";
		boolean isSignInButtonEnabled = loginPage.isSignInButtonEnabled();

		softAssert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message is not the same as expected.");
		softAssert.assertFalse(isSignInButtonEnabled, "Sign in button is enabled.");
		softAssert.assertAll();
	}

	@Feature("login positive testing")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Verifies that user stays logged in after clicking back button")
	@Test(description = "Verifies that user stays logged in after clicking back button")
	public void testClickingBackButtonAfterLogin() {
		AccountPage accountPage = loginPage
				.logIn(EMAIL, PASSWORD)
				.waitUntilLoggedIn();

		driver.navigate().back();

		String actualHeaderText = accountPage.getTextOfMyAccountHeader();
		String expectedHeaderText = "My account";
		assertEquals(actualHeaderText, expectedHeaderText, "Personal account header is not the same as expected!");

		accountPage
				.logOut()
				.waitUntilLoggedOut();
	}

	@AfterClass
	public void tearDownClass() {
		loginPage
				.logIn(EMAIL, PASSWORD)
				.waitUntilLoggedIn()
				.deleteAccount()
				.waitUntilAccountDeleted();
	}
}
