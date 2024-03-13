package pl.traineeship.autoqa.lesson14.task1.page;

import static pl.traineeship.autoqa.lesson14.task2.page.LoginPage.DEFAULT_WAIT_TIME;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Добавьте аллюр-репортинг к нашим тестам: своему проекту.
public class AlertTutorialPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(AlertTutorialPage.class);

    @FindBy(xpath = "//button[@aria-label='Consent']")
    private WebElement consentButton;
    @FindBy(xpath = "//div[@id='output']")
    private WebElement outputDiv;
    @FindBy(xpath = "//button[@id='alertBox']")
    private WebElement clickMe1Button;
    @FindBy(xpath = "//button[@id='confirmBox']")
    private WebElement clickMe2Button;
    @FindBy(xpath = "//button[@id='promptBox']")
    private WebElement clickMe3Button;

    private WebDriver driver;
    private WebDriverWait wait;

    public AlertTutorialPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
    }

    @Step("consenting to data use")
    public AlertTutorialPage consentToDataUse() {
        LOGGER.info("consenting to data use");
        wait.until(ExpectedConditions.visibilityOf(consentButton)).click();
        return this;
    }

    @Step("clicking first button")
    public AlertTutorialPage clickFirstButton() {
        LOGGER.info("clicking first button");
        clickMe1Button.click();
        return this;
    }

    @Step("clicking second button")
    public AlertTutorialPage clickSecondButton() {
        LOGGER.info("clicking second button");
        clickMe2Button.click();
        return this;
    }

    @Step("clicking third button")
    public AlertTutorialPage clickThirdButton() {
        LOGGER.info("clicking third button");
        clickMe3Button.click();
        return this;
    }

    @Step("accepting alert")
    public AlertTutorialPage acceptAlert() {
        LOGGER.info("accepting alert");
        driver.switchTo().alert().accept();
        return this;
    }

    @Step("accepting alert prompt")
    public AlertTutorialPage acceptAlert(String message) {
        LOGGER.info("accepting alert prompt with a message: " + message);
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(message);
        alert.accept();
        return this;
    }

    @Step("dismissing alert")
    public AlertTutorialPage dismissAlert() {
        LOGGER.info("dismissing alert");
        driver.switchTo().alert().dismiss();
        return this;
    }

    @Step("returning output text")
    public String getOutputText() {
        LOGGER.info("returning output text");
        return outputDiv.getText();
    }
}
