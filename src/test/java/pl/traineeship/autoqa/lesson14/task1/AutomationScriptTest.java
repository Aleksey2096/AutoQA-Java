package pl.traineeship.autoqa.lesson14.task1;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pl.traineeship.autoqa.lesson14.task1.page.AlertTutorialPage;
import pl.traineeship.autoqa.lesson14.task1.page.FormSubmitPage;
import pl.traineeship.autoqa.lesson14.task1.page.GoogleSearchPage;
import pl.traineeship.autoqa.lesson14.task1.page.GuinnessRegisterPage;
import pl.traineeship.autoqa.lesson14.task2.page.BaseTest;
import pl.traineeship.autoqa.lesson14.task2.util.AllureScreenshotListener;
import pl.traineeship.autoqa.lesson14.task2.util.ScreenshotListener;

/*
	Добавьте аллюр-репортинг к нашим тестам: своему проекту.

	1. Проведите рефакторинг в соответствии с PageObject.

	2. Необходимо автоматизировать сценарий, который показан 
	на видео “Сценарий для автоматизации Лекция 12.mp4”.
*/
@Listeners({ScreenshotListener.class, AllureScreenshotListener.class})
public class AutomationScriptTest extends BaseTest {
    private static GoogleSearchPage googleSearchPage;

    @BeforeClass
    public void setUpClass() {
        googleSearchPage = new GoogleSearchPage(driver);
    }

    @Feature("Multiple pages testing")
    @Severity(SeverityLevel.TRIVIAL)
    @Description("testing multiple pages from 'Сценарий для автоматизации Лекция 12.mp4'")
    @Test(description = "testing multiple pages from 'Сценарий для автоматизации Лекция 12.mp4'")
    public void testAutomationScript() {

        // 1. open google search page
        googleSearchPage
                .openSearchPage()
                .setOriginalWindowHandle();

        // deny cookies
        googleSearchPage.denyCookies();

        /*
         * 2. Then enter the following link into the search bar:
         * https://www.guinnessworldrecords.com/account/register?
         */
        googleSearchPage.enterIntoSearchBar(GUINNESS_REGISTER_URL);

        // Open a suitable link in a new tab, which will be displayed in the results.
        googleSearchPage
                .openFirstLinkInNewTab()
                .setGuinnessWindowHandle();

        /*
         * 3. Then enter the following link into the search bar:
         * https://www.hyrtutorials.com/p/alertsdemo.html
         */
        googleSearchPage
                .clearSearchBar()
                .enterIntoSearchBar(TUTORIALS_URL);

        // Open a suitable link in a new tab, which will be displayed in the results.
        googleSearchPage
                .openFirstLinkInNewTab()
                .setTutorialWindowHandle();

        /*
         * 4. Open the following link in the active tab:
         * https://www.w3schools.com/html/tryit.asp?filename=tryhtml_form_submit
         */
        FormSubmitPage formSubmitPage = googleSearchPage.openFormSubmitPage();

        // accept cookies
        formSubmitPage.acceptCookies();

        /*
         * In the open tab, fill in the fields with your first and last name and click
         * the ‘Submit’ button.
         */
        String noteText = formSubmitPage
                .switchToInnerFrame()
                .enterFirstName(FIRST_NAME)
                .enterLastName(LAST_NAME)
                .clickSubmitButton()
                .getNoteText();

        // Then output the text of the 'Note' element to the console:
        System.out.println(noteText);
        /*
         * Note: This tutorial will not teach you how servers are processing input.
         * Processing input is explained in our PHP tutorial.
         */
        softAssert.assertEquals(noteText,
                "Note: This tutorial will not teach you how servers are processing input. Processing input is explained in our PHP tutorial.",
                "Text of the 'Note' element is not the same as expected.");

        /*
         * 5. Switch to a tab with the following link open:
         * https://www.guinnessworldrecords.com/account/register? In this window, fill
         * in all fields with the appropriate information. In the Password and Confirm
         * Password fields, enter various random passwords. In order for a notification
         * to appear that these passwords do not match.
         */
        GuinnessRegisterPage guinnessRegisterPage = formSubmitPage.openGuinnessRegisterPage();

        String errorText = guinnessRegisterPage
                .enterLastName(LAST_NAME)
                .enterFirstName(FIRST_NAME)
                .enterDateOfBirth(DAY_OF_BIRTH, MONTH_OF_BIRTH, YEAR_OF_BIRTH)
                .selectCountry(COUNTRY_CODE)
                .enterCity(CITY)
                .enterEmail(EMAIL)
                .enterConfirmEmail(EMAIL)
                .enterPassword(PASSWORD)
                .enterConfirmPassword(PASSWORD + PASSWORD)
                .getErrorText();

        // Print this notification to the console.
        System.out.println(errorText); // 'Confirm password' and 'Password' do not match.
        softAssert.assertEquals(errorText, "'Confirm password' and 'Password' do not match.",
                "Notification text is not the same as expected.");

        /*
         * 6. Switch to a tab with the following link open:
         * https://www.hyrtutorials.com/p/alertsdemo.html
         */
        AlertTutorialPage alertTutorialPage = guinnessRegisterPage.openAlertTutorialPage();

        // consent to data use
        alertTutorialPage.consentToDataUse();

        /*
         * Press 'Click me' buttons in turn. After clicking each button, modal windows
         * will appear on the screen.
         *
         * After clicking on the first button, click “Ok” on the modal window and
         * display a message in the console in the “Popup box output” module.
         */
        String firstOutput = alertTutorialPage
                .clickFirstButton()
                .acceptAlert()
                .getOutputText();

        System.out.println(firstOutput); // You selected alert popup
        softAssert.assertEquals(firstOutput, "You selected alert popup",
                "Output message is not the same as expected.");

        /*
         * After clicking the second button, click “Cancel” on the modal window and
         * display a message in the console in the “Popup box output” module.
         */
        String secondOutput = alertTutorialPage
                .clickSecondButton()
                .dismissAlert()
                .getOutputText();

        System.out.println(secondOutput); // You pressed Cancel in confirmation popup
        softAssert.assertEquals(secondOutput, "You pressed Cancel in confirmation popup",
                "Output message is not the same as expected.");

        /*
         * After clicking on the third button, enter the text “Final step of this task”
         * in the modal window and click “Ok”. Print a message to the console in the
         * “Popup box output” module.
         */
        String thirdOutput = alertTutorialPage
                .clickThirdButton()
                .acceptAlert(FINAL_STEP_MESSAGE)
                .getOutputText();

        System.out.println(thirdOutput); // You entered text Final step of this task in propmt popup
        softAssert.assertEquals(thirdOutput,
                "You entered text Final step of this task in propmt popup",
                "Output message is not the same as expected.");
        softAssert.assertAll();
    }
}
