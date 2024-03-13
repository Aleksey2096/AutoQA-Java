package pl.traineeship.autoqa.lesson14.task1.page;

import static pl.traineeship.autoqa.lesson14.task2.page.LoginPage.DEFAULT_WAIT_TIME;

import java.util.Set;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
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
public class GoogleSearchPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleSearchPage.class);

    private static final String GOOGLE_SEARCH_URL = "https://www.google.com/search";
    private static final String FORM_SUBMIT_URL
            = "https://www.w3schools.com/html/tryit.asp?filename=tryhtml_form_submit";

    private static String originalWindowHandle;
    private static String guinnessWindowHandle;
    private static String tutorialWindowHandle;

    @FindBy(xpath = "//div[3]/div[1]/button[1]")
    private WebElement denyCookiesButton;
    @FindBy(xpath = "//div[3]/div[1]/div/span")
    private WebElement clearSearchSpan;
    private By textAreaLocator = By.xpath("//textarea[@role='combobox']");
    private By firstFoundLinkLocator = By.xpath("//div[@id='search']//a");

    private WebDriver driver;
    private WebDriverWait wait;

    public GoogleSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
    }

    @Step("returning guinness window handle")
    public static String getGuinnessWindowHandle() {
        LOGGER.info("returning guinness window handle");
        return guinnessWindowHandle;
    }

    @Step("returning tutorial window handle")
    public static String getTutorialWindowHandle() {
        LOGGER.info("returning tutorial window handle");
        return tutorialWindowHandle;
    }

    @Step("opening google search page")
    public GoogleSearchPage openSearchPage() {
        LOGGER.info("opening google search page");
        driver.get(GOOGLE_SEARCH_URL);
        return this;
    }

    @Step("setting original window handle")
    public GoogleSearchPage setOriginalWindowHandle() {
        LOGGER.info("setting original window handle");
        originalWindowHandle = driver.getWindowHandle();
        return this;
    }

    @Step("denying cookies")
    public GoogleSearchPage denyCookies() {
        LOGGER.info("denying cookies");
        wait.until(ExpectedConditions.visibilityOf(denyCookiesButton)).click();
        return this;
    }

    @Step("entering text into search bar")
    public GoogleSearchPage enterIntoSearchBar(String url) {
        LOGGER.info("entering text into search bar: " + url);
        WebElement textArea = driver.findElement(textAreaLocator);
        textArea.sendKeys(url);
        textArea.sendKeys(Keys.ENTER);
        return this;
    }

    @Step("opening first found link in a new tab")
    public GoogleSearchPage openFirstLinkInNewTab() {
        LOGGER.info("opening first found link in a new tab");
        WebElement firstLink = driver.findElement(firstFoundLinkLocator);
        if (System.getProperty("os.name").contains("Windows")) {
            firstLink.sendKeys(Keys.chord(Keys.CONTROL, Keys.RETURN));
        } else {
            firstLink.sendKeys(Keys.chord(Keys.COMMAND, Keys.RETURN));
        }
        return this;
    }

    @Step("setting guinness window handle")
    public GoogleSearchPage setGuinnessWindowHandle() {
        LOGGER.info("setting guinness window handle");
        Set<String> windowHandles = driver.getWindowHandles();
        windowHandles.remove(originalWindowHandle);
        guinnessWindowHandle = windowHandles.iterator().next();
        return this;
    }

    @Step("clearing search bar")
    public GoogleSearchPage clearSearchBar() {
        LOGGER.info("clearing search bar");
        clearSearchSpan.click();
        return this;
    }

    @Step("setting tutorial window handle")
    public GoogleSearchPage setTutorialWindowHandle() {
        LOGGER.info("setting tutorial window handle");
        Set<String> windowHandles = driver.getWindowHandles();
        windowHandles.removeAll(Set.of(originalWindowHandle, guinnessWindowHandle));
        tutorialWindowHandle = windowHandles.iterator().next();
        return this;
    }

    @Step("opening form submit page")
    public FormSubmitPage openFormSubmitPage() {
        LOGGER.info("opening form submit page");
        driver.get(FORM_SUBMIT_URL);
        return new FormSubmitPage(driver);
    }
}
