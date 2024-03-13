package pl.traineeship.autoqa.lesson14.task1.page;

import static org.testng.Assert.assertTrue;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pl.traineeship.autoqa.lesson14.task2.page.BaseTest;
import pl.traineeship.autoqa.lesson14.task2.page.RegistrationPage;
import pl.traineeship.autoqa.lesson14.task2.util.AllureScreenshotListener;
import pl.traineeship.autoqa.lesson14.task2.util.ScreenshotListener;

/*
	Добавьте аллюр-репортинг к нашим тестам: своему проекту.

	1. Проведите рефакторинг в соответствии с PageObject.

	3. Необходимо автоматизировать сценарий, который показан 
	на видео "Сценарий для автоматизации Лекция 12ч2.mp4".
		https://qa-course-01.andersenlab.com/registration
*/
@Listeners({ScreenshotListener.class, AllureScreenshotListener.class})
public class RegistrationPageTest extends BaseTest {

    private static RegistrationPage registrationPage;

    @BeforeClass
    public void setUpClass() {
        registrationPage = new RegistrationPage(driver);
    }

    @Feature("filling form testing")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verifies that submit button is enabled after filling whole form")
    @Test(description = "Verifies that submit button is enabled after filling whole form")
    public void testFillingRegistrationForm() {
        boolean isSubmitButtonEnabled = registrationPage
                .openRegistrationPage()
                .enterFirstName(FIRST_NAME)
                .enterLastName(LAST_NAME)
                .enterDateOfBirth(DATE_OF_BIRTH)
                .enterEmail(EMAIL)
                .enterPassword(PASSWORD)
                .enterPasswordConfirmation(PASSWORD)
                .isSubmitButtonEnabled();

        assertTrue(isSubmitButtonEnabled, "Submit button is not enabled!");
    }
}
