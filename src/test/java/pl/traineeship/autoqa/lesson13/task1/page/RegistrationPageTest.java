package pl.traineeship.autoqa.lesson13.task1.page;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pl.traineeship.autoqa.lesson13.task2.page.BaseTest;
import pl.traineeship.autoqa.lesson13.task2.page.RegistrationPage;

/*
	1. Проведите рефакторинг в соответствии с PageObject.

	3. Необходимо автоматизировать сценарий, который показан 
	на видео "Сценарий для автоматизации Лекция 12ч2.mp4".
		https://qa-course-01.andersenlab.com/registration
*/
public class RegistrationPageTest extends BaseTest {

	private static RegistrationPage registrationPage;

	@BeforeClass
	public void setUpClass() {
		registrationPage = new RegistrationPage(driver);
	}

	@Test
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
