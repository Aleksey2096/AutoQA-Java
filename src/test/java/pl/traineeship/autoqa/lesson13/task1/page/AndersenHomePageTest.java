package pl.traineeship.autoqa.lesson13.task1.page;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pl.traineeship.autoqa.lesson13.task2.page.BaseTest;

/*
	1. Проведите рефакторинг в соответствии с PageObject.

	1. Напишите 2 тест кейса на проверку каких либо визуальных элементов сайта 
		https://andersenlab.com/ 
	(Например отображение кнопок Skype, WatsApp или на переход на 
	страницу проекта Verivox. А лучше придумайте что-нибудь свое)).
*/
public class AndersenHomePageTest extends BaseTest {
	private static AndersenHomePage andersenHomePage;

	@BeforeClass
	public void setUpClass() {
		andersenHomePage = new AndersenHomePage(driver);
	}

	@BeforeMethod
	public void setUpMethod() {
		andersenHomePage.openHomePage();
	}

	@Test(description = "Verifies that user can navigate to the web development service page")
	public void testWebDevelopmentServiceLink() {
		andersenHomePage
				.acceptCookies()
				.goToWebDevelopmentPage();

		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = "https://andersenlab.com/services/web-development";
		assertEquals(actualUrl, expectedUrl, "URL address is not the same as expected!");
	}

	@Test(description = "Verifies that Instagram link icon is displayed")
	public void testInstagramIcon() {
		boolean isDisplayed = andersenHomePage.isInstagramIconDisplayed();
		assertTrue(isDisplayed, "Instagram link icon is not displayed!");
	}
}
