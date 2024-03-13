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
public class AndersenHomePage {
    private static final Logger LOGGER = LoggerFactory.getLogger(AndersenHomePage.class);
    private static final String ANDERSEN_URL = "https://andersenlab.com/";

    @FindBy(xpath = "//div[@class='CookiesPolicy-module--contentWrapper--c9ed1']/button[2]")
    private WebElement acceptCookiesButton;
    @FindBy(xpath = "//section/div/a[@href='/services/web-development']")
    private WebElement webDevelopmentLink;
    @FindBy(xpath = "//a/*[@aria-label='Instagram link']")
    private WebElement instagramIcon;

    private WebDriver driver;
    private WebDriverWait wait;

    public AndersenHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
    }

    @Step("opening home page")
    public AndersenHomePage openHomePage() {
        LOGGER.info("opening home page");
        driver.get(ANDERSEN_URL);
        return this;
    }

    @Step("accepting cookies")
    public AndersenHomePage acceptCookies() {
        LOGGER.info("accepting cookies");
        wait.until(ExpectedConditions.visibilityOf(acceptCookiesButton)).click();
        return this;
    }

    @Step("checking if Instagram icon is displayed")
    public boolean isInstagramIconDisplayed() {
        LOGGER.info("checking if Instagram icon is displayed");
        return instagramIcon.isDisplayed();
    }

    @Step("clicking link to web development page")
    public void goToWebDevelopmentPage() {
        LOGGER.info("clicking link to web development page");
        webDevelopmentLink.click();
        wait.until(ExpectedConditions.urlMatches(ANDERSEN_URL + ".+"));
    }
}
