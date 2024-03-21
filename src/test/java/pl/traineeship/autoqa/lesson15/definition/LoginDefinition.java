package pl.traineeship.autoqa.lesson15.definition;

import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.WebDriverRunner;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pl.traineeship.autoqa.lesson15.page.AccountPage;
import pl.traineeship.autoqa.lesson15.page.LoginPage;
import pl.traineeship.autoqa.lesson15.util.DriverUtil;

public class LoginDefinition {

    private static final String FIRST_NAME = "Aliaksei";
    private static final String LAST_NAME = "Maksimenka";
    private static final String DATE_OF_BIRTH = "01/01/1996";
    private static final String EMAIL = "test_email@gmail.com";
    private static final String PASSWORD = "strongpassword";

    private static SoftAssert softAssert;
    private static LoginPage loginPage;
    private static AccountPage accountPage;

    @BeforeAll
    public static void setUp() {
        DriverUtil.configureDriver();

        softAssert = new SoftAssert();

        loginPage = new LoginPage();
        loginPage
                .openLoginPage()
                .clickRegistrationLink()
                .createAccount(FIRST_NAME, LAST_NAME, DATE_OF_BIRTH, EMAIL, PASSWORD, PASSWORD)
                .waitUntilAccountCreated()
                .logOut()
                .waitUntilLoggedOut();
    }

    @Given("User is on Login page")
    public void goToLoginPage() {
        loginPage.openLoginPage();
    }

    @When("User enters email as {string} and password as {string} and clicks sign in button")
    public void enterCredentials(String email, String password) {
        loginPage
                .enterEmail(email)
                .enterPassword(password)
                .clickSignInButton();
    }

    @Then("Account page should be opened with url {string} and header text {string}")
    public void verifyLogin(String expectedUrl, String expectedHeaderText) {
        accountPage = loginPage.waitUntilLoggedIn();

        String actualUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        String actualHeaderText = accountPage.getTextOfMyAccountHeader();

        softAssert.assertEquals(actualUrl, expectedUrl,
                "URL after login is not the same as expected!");
        softAssert.assertEquals(actualHeaderText, expectedHeaderText,
                "Personal account header is not the same as expected!");
        softAssert.assertAll();
    }

    @Then("User should be logged out")
    public void logOut() {
        accountPage
                .logOut()
                .waitUntilLoggedOut();
    }

    @Then("User should be able to see {int} common error messages {string}")
    public void verifyCommonErrorMessage(int expectedNumberOfErrorMessages,
                                         String expectedErrorMessage) {
        ElementsCollection errorSpanCollection = loginPage.getWrongEmailOrPasswordSpanCollection();

        int actualNumberOfErrorMessages = errorSpanCollection.size();
        String actualErrorMessage = errorSpanCollection.get(0).getText();
        boolean isSignInButtonEnabled = loginPage.isSignInButtonEnabled();

        softAssert.assertEquals(actualErrorMessage, expectedErrorMessage,
                "Error message is not the same as expected.");
        softAssert.assertEquals(actualNumberOfErrorMessages, expectedNumberOfErrorMessages,
                "Number of error messages is not the same as expected.");
        softAssert.assertFalse(isSignInButtonEnabled, "Sign in button is enabled.");
        softAssert.assertAll();
    }

    @Then("User should be able to see specific error message {string}")
    public void verifyEmailErrorMessage(String expectedErrorMessage) {
        WebElement invalidEmailSpan = loginPage.getInvalidEmailSpan();

        String actualErrorMessage = invalidEmailSpan.getText();
        boolean isSignInButtonEnabled = loginPage.isSignInButtonEnabled();

        softAssert.assertEquals(actualErrorMessage, expectedErrorMessage,
                "Error message is not the same as expected.");
        softAssert.assertFalse(isSignInButtonEnabled, "Sign in button is enabled.");
        softAssert.assertAll();
    }

    @AfterAll
    public static void tearDown() {
        loginPage
                .logIn(EMAIL, PASSWORD)
                .waitUntilLoggedIn()
                .deleteAccount()
                .waitUntilAccountDeleted();
    }
}
