package pl.traineeship.autoqa.lesson12;

import static org.testng.Assert.assertTrue;
import static pl.traineeship.autoqa.lesson12.TestUtil.DATE_OF_BIRTH_INPUT_LOCATOR;
import static pl.traineeship.autoqa.lesson12.TestUtil.DAY_10_DIV_LOCATOR;
import static pl.traineeship.autoqa.lesson12.TestUtil.EMAIL;
import static pl.traineeship.autoqa.lesson12.TestUtil.EMAIL_INPUT_LOCATOR;
import static pl.traineeship.autoqa.lesson12.TestUtil.FIRST_NAME;
import static pl.traineeship.autoqa.lesson12.TestUtil.FIRST_NAME_INPUT_LOCATOR;
import static pl.traineeship.autoqa.lesson12.TestUtil.LAST_NAME;
import static pl.traineeship.autoqa.lesson12.TestUtil.LAST_NAME_INPUT_LOCATOR;
import static pl.traineeship.autoqa.lesson12.TestUtil.MONTH;
import static pl.traineeship.autoqa.lesson12.TestUtil.MONTH_SELECT_LOCATOR;
import static pl.traineeship.autoqa.lesson12.TestUtil.PASSWORD;
import static pl.traineeship.autoqa.lesson12.TestUtil.PASSWORD_CONFIRMATION_INPUT_LOCATOR;
import static pl.traineeship.autoqa.lesson12.TestUtil.PASSWORD_INPUT_LOCATOR;
import static pl.traineeship.autoqa.lesson12.TestUtil.REGISTRATION_URL;
import static pl.traineeship.autoqa.lesson12.TestUtil.SUBMIT_BUTTON_LOCATOR;
import static pl.traineeship.autoqa.lesson12.TestUtil.YEAR;
import static pl.traineeship.autoqa.lesson12.TestUtil.YEAR_SELECT_LOCATOR;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/*
	3. Необходимо автоматизировать сценарий, который показан 
	на видео "Сценарий для автоматизации Лекция 12ч2.mp4".
		https://qa-course-01.andersenlab.com/registration
 */
public class RegistrationScriptTest {
	private TestUtil testUtil;

	@BeforeClass
	public void setUp() {
		testUtil = new TestUtil();
	}

	@Test
	public void testFillingRegistrationForm() {
		testUtil.getDriver().get(REGISTRATION_URL);
		testUtil.getDriver().findElement(FIRST_NAME_INPUT_LOCATOR).sendKeys(FIRST_NAME);
		testUtil.getDriver().findElement(LAST_NAME_INPUT_LOCATOR).sendKeys(LAST_NAME);

		// picking date of birth
		WebElement dateInput = testUtil.getDriver().findElement(DATE_OF_BIRTH_INPUT_LOCATOR);
		dateInput.click();
		new Select(testUtil.getDriver().findElement(YEAR_SELECT_LOCATOR)).selectByValue(YEAR);
		new Select(testUtil.getDriver().findElement(MONTH_SELECT_LOCATOR)).selectByValue(MONTH);
		testUtil.getDriver().findElement(DAY_10_DIV_LOCATOR).click();
		dateInput.sendKeys(Keys.RETURN);

		testUtil.getDriver().findElement(EMAIL_INPUT_LOCATOR).sendKeys(EMAIL);
		testUtil.getDriver().findElement(PASSWORD_INPUT_LOCATOR).sendKeys(PASSWORD);
		testUtil.getDriver().findElement(PASSWORD_CONFIRMATION_INPUT_LOCATOR).sendKeys(PASSWORD);

		boolean isSubmitButtonEnabled = testUtil.getDriver().findElement(SUBMIT_BUTTON_LOCATOR).isEnabled();
		assertTrue(isSubmitButtonEnabled, "Submit button is not enabled!");
	}

	@AfterClass
	public void tearDown() {
		testUtil.getDriver().quit();
	}
}
