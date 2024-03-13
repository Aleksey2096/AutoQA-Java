package pl.traineeship.autoqa.lesson14.task2.page;

import static pl.traineeship.autoqa.lesson14.task2.page.AccountPage.BASE_URL;

import java.time.Duration;
import java.util.List;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Добавьте аллюр-репортинг к нашим тестам: своему проекту.
public class LoginPage {
    public static final String LOGIN_URL = "https://qa-course-01.andersenlab.com/login";
    public static final Duration DEFAULT_WAIT_TIME = Duration.ofSeconds(10);
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginPage.class);

    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailInput;
    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordInput;
    @FindBy(xpath = "//button[text()='Sign in']")
    private WebElement signInButton;
    @FindBy(xpath = "//span[text()='Email or password is not valid']")
    private List<WebElement> wrongEmailOrPasswordSpanList;
    @FindBy(xpath = "//span[text()='Invalid email address']")
    private WebElement invalidEmailSpan;
    @FindBy(xpath = "//span[text()='Required']")
    private WebElement requiredFieldSpan;
    @FindBy(xpath = "//a[text()='Registration']")
    private WebElement registrationLink;

    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
    }

    @Step("opening login page")
    public LoginPage openLoginPage() {
        LOGGER.info("opening login page");
        driver.get(LOGIN_URL);
        return this;
    }

    @Step("waiting until logged in")
    public AccountPage waitUntilLoggedIn() {
        LOGGER.info("waiting until logged in");
        wait.until(ExpectedConditions.urlToBe(BASE_URL));
        return new AccountPage(driver);
    }

    @Step("logging in")
    public LoginPage logIn(String email, String password) {
        return openLoginPage()
                .enterEmail(email)
                .enterPassword(password)
                .clickSignInButton();
    }

    @Step("returning wrong email or password span list")
    public List<WebElement> getWrongEmailOrPasswordSpanList() {
        LOGGER.info("returning wrong email or password span list");
        return wait.until(ExpectedConditions.visibilityOfAllElements(wrongEmailOrPasswordSpanList));
    }

    @Step("returning invalid email span")
    public WebElement getInvalidEmailSpan() {
        LOGGER.info("returning invalid email span");
        return wait.until(ExpectedConditions.visibilityOf(invalidEmailSpan));
    }

    @Step("returning required field span")
    public WebElement getRequiredFieldSpan() {
        LOGGER.info("returning required field span");
        return wait.until(ExpectedConditions.visibilityOf(requiredFieldSpan));
    }

    @Step("checking if sign in button enabled")
    public boolean isSignInButtonEnabled() {
        LOGGER.info("checking if sign in button enabled");
        return signInButton.isEnabled();
    }

    @Step("clicking registration link")
    public RegistrationPage clickRegistrationLink() {
        LOGGER.info("clicking registration link");
        registrationLink.click();
        return new RegistrationPage(driver);
    }

    @Step("entering email")
    private LoginPage enterEmail(String email) {
        LOGGER.info("entering email: " + email);
        emailInput.sendKeys(email);
        return this;
    }

    @Step("entering password")
    private LoginPage enterPassword(String password) {
        LOGGER.info("entering password: " + password);
        passwordInput.sendKeys(password);
        return this;
    }

    @Step("clicking sign in button")
    private LoginPage clickSignInButton() {
        LOGGER.info("clicking sign in button");
        signInButton.click();
        return this;
    }
}
