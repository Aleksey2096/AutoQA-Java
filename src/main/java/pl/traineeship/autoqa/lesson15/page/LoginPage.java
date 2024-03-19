package pl.traineeship.autoqa.lesson15.page;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;
import static pl.traineeship.autoqa.lesson15.page.AccountPage.BASE_URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class LoginPage {
    public static final String LOGIN_URL = "https://qa-course-01.andersenlab.com/login";

    private SelenideElement emailInput = $(By.xpath("//input[@name='email']"));
    private SelenideElement passwordInput = $(By.xpath("//input[@name='password']"));
    private SelenideElement signInButton = $(By.xpath("//button[text()='Sign in']"));
    private ElementsCollection wrongEmailOrPasswordSpanCollection = $$(
            By.xpath("//span[text()='Email or password is not valid']"));
    private SelenideElement invalidEmailSpan = $(
            By.xpath("//span[text()='Invalid email address']"));
    private SelenideElement registrationLink = $(By.xpath("//a[text()='Registration']"));

    public LoginPage openLoginPage() {
        open(LOGIN_URL);
        return this;
    }

    public AccountPage waitUntilLoggedIn() {
        webdriver().shouldHave(url(BASE_URL));
        return new AccountPage();
    }

    public LoginPage logIn(String email, String password) {
        return openLoginPage()
                .enterEmail(email)
                .enterPassword(password)
                .clickSignInButton();
    }

    public ElementsCollection getWrongEmailOrPasswordSpanCollection() {
        return wrongEmailOrPasswordSpanCollection.shouldHave(sizeGreaterThan(0));
    }

    public WebElement getInvalidEmailSpan() {
        return invalidEmailSpan.shouldBe(visible);
    }

    public boolean isSignInButtonEnabled() {
        return signInButton.isEnabled();
    }

    public RegistrationPage clickRegistrationLink() {
        registrationLink.click();
        return new RegistrationPage();
    }

    public LoginPage enterEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public LoginPage clickSignInButton() {
        signInButton.getWrappedElement().click();
        return this;
    }
}
