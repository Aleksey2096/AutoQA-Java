package pl.traineeship.autoqa.lesson14.task2.page;

import static pl.traineeship.autoqa.lesson14.task2.page.AccountPage.BASE_URL;
import static pl.traineeship.autoqa.lesson14.task2.page.LoginPage.DEFAULT_WAIT_TIME;

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
public class RegistrationPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationPage.class);
    private static final String REGISTRATION_URL
            = "https://qa-course-01.andersenlab.com/registration";

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
    @FindBy(xpath = "//span[text()='Maximum 20 characters']")
    private WebElement tooLongPasswordSpan;
    @FindBy(xpath = "//span[text()='Minimum 8 characters']")
    private WebElement tooShortPasswordSpan;
    @FindBy(xpath = "//span[text()='Passwords must match']")
    private WebElement differentPasswordsSpan;
    @FindBy(xpath = "//span[text()='Required']")
    private WebElement requiredFieldSpan;

    private WebDriver driver;
    private WebDriverWait wait;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
    }

    @Step("opening registration page")
    public RegistrationPage openRegistrationPage() {
        LOGGER.info("opening registration page");
        driver.get(REGISTRATION_URL);
        return this;
    }

    @Step("waiting until account created")
    public AccountPage waitUntilAccountCreated() {
        LOGGER.info("waiting until account created");
        wait.until(ExpectedConditions.urlToBe(BASE_URL));
        return new AccountPage(driver);
    }

    @Step("creating account")
    public RegistrationPage createAccount(String firstName, String lastName, String dateOfBirth,
                                          String email,
                                          String password, String confirmPassword) {
        return openRegistrationPage()
                .enterFirstName(firstName)
                .enterLastName(lastName)
                .enterDateOfBirth(dateOfBirth)
                .enterEmail(email)
                .enterPassword(password)
                .enterPasswordConfirmation(confirmPassword)
                .clickSubmitButton();
    }

    @Step("returning too long password span")
    public WebElement getTooLongPasswordSpan() {
        LOGGER.info("returning too long password span");
        return wait.until(ExpectedConditions.visibilityOf(tooLongPasswordSpan));
    }

    @Step("returning too short password span")
    public WebElement getTooShortPasswordSpan() {
        LOGGER.info("returning too short password span");
        return wait.until(ExpectedConditions.visibilityOf(tooShortPasswordSpan));
    }

    @Step("returning different passwords span")
    public WebElement getDifferentPasswordsSpan() {
        LOGGER.info("returning different passwords span");
        return wait.until(ExpectedConditions.visibilityOf(differentPasswordsSpan));
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

    @Step("entering first name")
    public RegistrationPage enterFirstName(String firstName) {
        LOGGER.info("entering first name: " + firstName);
        firstNameInput.sendKeys(firstName);
        return this;
    }

    @Step("entering last name")
    public RegistrationPage enterLastName(String lastName) {
        LOGGER.info("entering last name: " + lastName);
        lastNameInput.sendKeys(lastName);
        return this;
    }

    @Step("entering date of birth")
    public RegistrationPage enterDateOfBirth(String dateOfBirth) {
        LOGGER.info("entering date of birth: " + dateOfBirth);
        dateOfBirthInput.sendKeys(dateOfBirth);
        dateOfBirthInput.sendKeys(Keys.RETURN);
        return this;
    }

    @Step("entering email")
    public RegistrationPage enterEmail(String email) {
        LOGGER.info("entering email: " + email);
        emailInput.sendKeys(email);
        return this;
    }

    @Step("entering password")
    public RegistrationPage enterPassword(String password) {
        LOGGER.info("entering password: " + password);
        passwordInput.sendKeys(password);
        return this;
    }

    @Step("entering confirm password")
    public RegistrationPage enterPasswordConfirmation(String password) {
        LOGGER.info("entering confirm password: " + password);
        passwordConfirmationInput.sendKeys(password);
        return this;
    }

    @Step("clicking submit button")
    private RegistrationPage clickSubmitButton() {
        LOGGER.info("clicking submit button");
        submitButton.click();
        return this;
    }
}
