package pl.traineeship.autoqa.lesson14.task1.page;

import static pl.traineeship.autoqa.lesson14.task2.page.LoginPage.DEFAULT_WAIT_TIME;

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
public class FormSubmitPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(FormSubmitPage.class);

    @FindBy(xpath = "//div[@id='accept-choices']")
    private WebElement acceptCookiesButton;
    @FindBy(xpath = "//input[@id='fname']")
    private WebElement firstNameInput;
    @FindBy(xpath = "//input[@id='lname']")
    private WebElement lastNameInput;
    @FindBy(xpath = "//input[@type='submit']")
    private WebElement submitButton;
    @FindBy(xpath = "//body[@class='w3-container']/div[2]")
    private WebElement noteDiv;

    private WebDriver driver;
    private WebDriverWait wait;

    public FormSubmitPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
    }

    @Step("accepting cookies")
    public FormSubmitPage acceptCookies() {
        LOGGER.info("accepting cookies");
        wait.until(ExpectedConditions.visibilityOf(acceptCookiesButton)).click();
        return this;
    }

    @Step("switching to inner frame")
    public FormSubmitPage switchToInnerFrame() {
        LOGGER.info("switching to inner frame");
        driver.switchTo().frame(0);
        return this;
    }

    @Step("entering first name")
    public FormSubmitPage enterFirstName(String firstName) {
        LOGGER.info("entering first name: " + firstName);
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
        return this;
    }

    @Step("entering last name")
    public FormSubmitPage enterLastName(String lastName) {
        LOGGER.info("entering last name: " + lastName);
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
        return this;
    }

    @Step("clicking submit button")
    public FormSubmitPage clickSubmitButton() {
        LOGGER.info("clicking submit button");
        submitButton.click();
        return this;
    }

    @Step("returning note text")
    public String getNoteText() {
        LOGGER.info("returning note text");
        return wait.until(ExpectedConditions.visibilityOf(noteDiv)).getText();
    }

    @Step("opening guinness register page")
    public GuinnessRegisterPage openGuinnessRegisterPage() {
        LOGGER.info("opening guinness register page");
        driver.switchTo().window(GoogleSearchPage.getGuinnessWindowHandle());
        return new GuinnessRegisterPage(driver);
    }
}
