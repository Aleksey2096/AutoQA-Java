package pl.traineeship.autoqa.lesson15.page;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;
import static pl.traineeship.autoqa.lesson15.page.AccountPage.BASE_URL;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

public class RegistrationPage {
    private static final String REGISTRATION_URL
            = "https://qa-course-01.andersenlab.com/registration";

    private SelenideElement firstNameInput = $(By.xpath("//input[@name='firstName']"));
    private SelenideElement lastNameInput = $(By.xpath("//input[@name='lastName']"));
    private SelenideElement dateOfBirthInput = $(By.xpath("//input[@name='dateOfBirth']"));
    private SelenideElement emailInput = $(By.xpath("//input[@name='email']"));
    private SelenideElement passwordInput = $(By.xpath("//input[@name='password']"));
    private SelenideElement passwordConfirmationInput = $(
            By.xpath("//input[@name='passwordConfirmation']"));
    private SelenideElement submitButton = $(By.xpath("//button[@type='submit']"));

    public RegistrationPage openRegistrationPage() {
        open(REGISTRATION_URL);
        return this;
    }

    public AccountPage waitUntilAccountCreated() {
        webdriver().shouldHave(url(BASE_URL));
        return new AccountPage();
    }

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

    private RegistrationPage enterFirstName(String firstName) {
        firstNameInput.sendKeys(firstName);
        return this;
    }

    private RegistrationPage enterLastName(String lastName) {
        lastNameInput.sendKeys(lastName);
        return this;
    }

    private RegistrationPage enterDateOfBirth(String dateOfBirth) {
        dateOfBirthInput.sendKeys(dateOfBirth);
        dateOfBirthInput.pressEnter();
        return this;
    }

    private RegistrationPage enterEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    private RegistrationPage enterPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    private RegistrationPage enterPasswordConfirmation(String password) {
        passwordConfirmationInput.sendKeys(password);
        return this;
    }

    private RegistrationPage clickSubmitButton() {
        submitButton.click();
        return this;
    }
}
