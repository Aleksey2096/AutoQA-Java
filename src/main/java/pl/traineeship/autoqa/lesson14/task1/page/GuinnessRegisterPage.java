package pl.traineeship.autoqa.lesson14.task1.page;

import static pl.traineeship.autoqa.lesson14.task2.page.LoginPage.DEFAULT_WAIT_TIME;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Добавьте аллюр-репортинг к нашим тестам: своему проекту.
public class GuinnessRegisterPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(GuinnessRegisterPage.class);

    @FindBy(xpath = "//input[@id='FirstName']")
    private WebElement firstNameInput;
    @FindBy(xpath = "//input[@id='LastName']")
    private WebElement lastNameInput;
    @FindBy(xpath = "//input[@id='DateOfBirthDay']")
    private WebElement dayOfBirth;
    @FindBy(xpath = "//input[@id='DateOfBirthMonth']")
    private WebElement monthOfBirth;
    @FindBy(xpath = "//input[@id='DateOfBirthYear']")
    private WebElement yearOfBirth;
    @FindBy(xpath = "//select[@id='Country']")
    private WebElement countrySelect;
    @FindBy(xpath = "//input[@id='State']")
    private WebElement cityInput;
    @FindBy(xpath = "//input[@id='EmailAddress']")
    private WebElement emailInput;
    @FindBy(xpath = "//input[@id='ConfirmEmailAddress']")
    private WebElement confirmEmailInput;
    @FindBy(xpath = "//input[@id='Password']")
    private WebElement passwordInput;
    @FindBy(xpath = "//input[@id='ConfirmPassword']")
    private WebElement confirmPasswordInput;
    @FindBy(xpath = "//span[@class='field-validation-error']")
    private WebElement errorSpan;

    private WebDriver driver;
    private WebDriverWait wait;

    public GuinnessRegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
    }

    @Step("entering first name")
    public GuinnessRegisterPage enterFirstName(String firstName) {
        LOGGER.info("entering first name: " + firstName);
        firstNameInput.sendKeys(firstName);
        return this;
    }

    @Step("entering last name")
    public GuinnessRegisterPage enterLastName(String lastName) {
        LOGGER.info("entering last name: " + lastName);
        lastNameInput.sendKeys(lastName);
        return this;
    }

    @Step("entering date of birth")
    public GuinnessRegisterPage enterDateOfBirth(String day, String month, String year) {
        LOGGER.info("entering date of birth: day=" + day + ", month=" + month + ", year=" + year);
        dayOfBirth.sendKeys(day);
        monthOfBirth.sendKeys(month);
        yearOfBirth.sendKeys(year);
        return this;
    }

    @Step("selecting country")
    public GuinnessRegisterPage selectCountry(String countryCode) {
        LOGGER.info("selecting country with a country code = " + countryCode);
        new Select(countrySelect).selectByValue(countryCode);
        return this;
    }

    @Step("entering city")
    public GuinnessRegisterPage enterCity(String city) {
        LOGGER.info("entering city: " + city);
        wait.until(ExpectedConditions.visibilityOf(cityInput)).sendKeys(city);
        return this;
    }

    @Step("entering email")
    public GuinnessRegisterPage enterEmail(String email) {
        LOGGER.info("entering email: " + email);
        emailInput.sendKeys(email);
        return this;
    }

    @Step("entering confirm email")
    public GuinnessRegisterPage enterConfirmEmail(String email) {
        LOGGER.info("entering confirm email: " + email);
        confirmEmailInput.sendKeys(email);
        return this;
    }

    @Step("entering password")
    public GuinnessRegisterPage enterPassword(String password) {
        LOGGER.info("entering password: " + password);
        passwordInput.sendKeys(password);
        return this;
    }

    @Step("entering confirm password")
    public GuinnessRegisterPage enterConfirmPassword(String password) {
        LOGGER.info("entering password email: " + password);
        confirmPasswordInput.sendKeys(password, Keys.ENTER);
        return this;
    }

    @Step("returning error text")
    public String getErrorText() {
        LOGGER.info("returning error text");
        return wait.until(ExpectedConditions.visibilityOf(errorSpan)).getText();
    }

    @Step("opening alert tutorial page")
    public AlertTutorialPage openAlertTutorialPage() {
        LOGGER.info("opening alert tutorial page");
        driver.switchTo().window(GoogleSearchPage.getTutorialWindowHandle());
        return new AlertTutorialPage(driver);
    }
}
