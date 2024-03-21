package pl.traineeship.autoqa.lesson15.page;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;
import static pl.traineeship.autoqa.lesson15.page.LoginPage.LOGIN_URL;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

public class EditAccountPage {

    private SelenideElement deleteAccountElement = $(By.xpath("//p[text()='Delete account']"));
    private SelenideElement confirmAccountDeletionButton = $(By.xpath("//button[text()='Yes']"));

    public EditAccountPage clickDeleteAccountButton() {
        deleteAccountElement.click();
        return this;
    }

    public EditAccountPage clickConfirmAccountDeletionButton() {
        confirmAccountDeletionButton.click();
        return this;
    }

    public LoginPage waitUntilAccountDeleted() {
        webdriver().shouldHave(url(LOGIN_URL));
        return new LoginPage();
    }
}
