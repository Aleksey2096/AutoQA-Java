package pl.traineeship.autoqa.lesson15.page;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;
import static pl.traineeship.autoqa.lesson15.page.LoginPage.LOGIN_URL;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

public class AccountPage {
    public static final String BASE_URL = "https://qa-course-01.andersenlab.com/";

    private SelenideElement logOutElement = $(By.xpath("//p[text()='Logout']"));
    private SelenideElement confirmLogOutButton = $(By.xpath("//button[text()='Yes']"));
    private SelenideElement editAccountLink = $(By.xpath("//a[text()='Edit account']"));
    private SelenideElement myAccountHeader = $(By.xpath("//h1[text()='My account']"));

    public AccountPage openAccountPage() {
        open(BASE_URL);
        return this;
    }

    public LoginPage waitUntilLoggedOut() {
        webdriver().shouldHave(url(LOGIN_URL));
        return new LoginPage();
    }

    public AccountPage logOut() {
        return openAccountPage()
                .clickLogOutButton()
                .clickConfirmLogOutButton();
    }

    public EditAccountPage deleteAccount() {
        return openAccountPage()
                .clickEditAccountButton()
                .clickDeleteAccountButton()
                .clickConfirmAccountDeletionButton();
    }

    public String getTextOfMyAccountHeader() {
        return myAccountHeader.getText();
    }

    private EditAccountPage clickEditAccountButton() {
        editAccountLink.shouldBe(visible).click();
        return new EditAccountPage();
    }

    private AccountPage clickLogOutButton() {
        logOutElement.shouldBe(visible).click();
        return this;
    }

    private AccountPage clickConfirmLogOutButton() {
        confirmLogOutButton.click();
        return this;
    }
}
