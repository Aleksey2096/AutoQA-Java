package pl.traineeship.autoqa.lesson14.task2.page;

import static pl.traineeship.autoqa.lesson14.task2.page.AccountPage.BASE_URL;
import static pl.traineeship.autoqa.lesson14.task2.page.LoginPage.DEFAULT_WAIT_TIME;
import static pl.traineeship.autoqa.lesson14.task2.page.LoginPage.LOGIN_URL;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Добавьте аллюр-репортинг к нашим тестам: своему проекту.
public class EditAccountPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(EditAccountPage.class);
    private static final String VALUE_ATTRIBUTE = "value";

    @FindBy(xpath = "//p[text()='Delete account']")
    private WebElement deleteAccountElement;
    @FindBy(xpath = "//button[text()='Yes']")
    private WebElement confirmAccountDeletionButton;
    @FindBy(xpath = "//input[@name='firstName']")
    private WebElement firstNameInput;
    @FindBy(xpath = "//input[@name='lastName']")
    private WebElement lastNameInput;
    @FindBy(xpath = "//input[@name='dateOfBirth']")
    private WebElement dateOfBirthInput;
    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailInput;
    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordInput;
    @FindBy(xpath = "//input[@name='passwordConfirmation']")
    private WebElement passwordConfirmationInput;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;
    @FindBy(xpath = "//span[text()='Required']")
    private WebElement requiredFieldSpan;

    private WebDriver driver;
    private WebDriverWait wait;

    public EditAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
    }

    @Step("clicking delete account button")
    public EditAccountPage clickDeleteAccountButton() {
        LOGGER.info("clicking delete account button");
        deleteAccountElement.click();
        return this;
    }

    @Step("clicking confirm account deletion button")
    public EditAccountPage clickConfirmAccountDeletionButton() {
        LOGGER.info("clicking confirm account deletion button");
        confirmAccountDeletionButton.click();
        return this;
    }

    @Step("waiting until account deleted")
    public LoginPage waitUntilAccountDeleted() {
        LOGGER.info("waiting until account deleted");
        wait.until(ExpectedConditions.urlToBe(LOGIN_URL));
        return new LoginPage(driver);
    }

    @Step("checking if email input enabled")
    public boolean isEmailInputEnabled() {
        LOGGER.info("checking if email input enabled");
        return emailInput.isEnabled();
    }

    @Step("waiting until account edited")
    public AccountPage waitUntilAccountEdited() {
        LOGGER.info("waiting until account edited");
        wait.until(ExpectedConditions.urlToBe(BASE_URL));
        return new AccountPage(driver);
    }

    @Step("editing account")
    public EditAccountPage editAccount(String firstName, String lastName, String dateOfBirth,
                                       String password,
                                       String confirmPassword) {
        return editFirstName(firstName)
                .editLastName(lastName)
                .editDateOfBirth(dateOfBirth)
                .enterPassword(password)
                .enterPasswordConfirmation(confirmPassword)
                .clickSubmitButton();
    }

    @Step("returning required field span")
    public WebElement getRequiredFieldSpan() {
        LOGGER.info("returning required field span");
        return wait.until(ExpectedConditions.visibilityOf(requiredFieldSpan));
    }

    @Step("checking if submit button enabled")
    public boolean isSubmitButtonEnabled() {
        LOGGER.info("checking if submit button enabled");
        return submitButton.isEnabled();
    }

    @Step("editing first name")
    private EditAccountPage editFirstName(String firstName) {
        LOGGER.info("replacing first name with: " + firstName);
        clearInput(firstNameInput);
        firstNameInput.sendKeys(firstName);
        return this;
    }

    @Step("editing last name")
    private EditAccountPage editLastName(String lastName) {
        LOGGER.info("replacing last name with: " + lastName);
        clearInput(lastNameInput);
        lastNameInput.sendKeys(lastName);
        return this;
    }

    @Step("editing date of birth")
    private EditAccountPage editDateOfBirth(String dateOfBirth) {
        LOGGER.info("replacing date of birth with: " + dateOfBirth);
        clearInput(dateOfBirthInput);
        dateOfBirthInput.sendKeys(dateOfBirth);
        dateOfBirthInput.sendKeys(Keys.RETURN);
        return this;
    }

    @Step("entering password")
    private EditAccountPage enterPassword(String password) {
        LOGGER.info("entering password: " + password);
        passwordInput.sendKeys(password);
        return this;
    }

    @Step("entering confirm password")
    private EditAccountPage enterPasswordConfirmation(String password) {
        LOGGER.info("entering confirm password: " + password);
        passwordConfirmationInput.sendKeys(password);
        return this;
    }

    @Step("clicking submit button")
    private EditAccountPage clickSubmitButton() {
        LOGGER.info("clicking submit button");
        submitButton.click();
        return this;
    }

    // Instead of '.clear()' method which doesn't work on this page
    private void clearInput(WebElement element) {
        while (!element.getAttribute(VALUE_ATTRIBUTE).isEmpty()) {
            element.sendKeys(Keys.BACK_SPACE);
        }
    }
}
