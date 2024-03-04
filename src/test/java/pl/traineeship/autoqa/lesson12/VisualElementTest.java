package pl.traineeship.autoqa.lesson12;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static pl.traineeship.autoqa.lesson12.TestUtil.ACCEPT_COOKIES_BUTTON_LOCATOR;
import static pl.traineeship.autoqa.lesson12.TestUtil.ANDERSEN_URL;
import static pl.traineeship.autoqa.lesson12.TestUtil.INSTAGRAM_ICON_LOCATOR;
import static pl.traineeship.autoqa.lesson12.TestUtil.WEB_DEVELOPMENT_LINK_LOCATOR;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
	1. Напишите 2 тест кейса на проверку каких либо визуальных элементов сайта 
		https://andersenlab.com/ 
	(Например отображение кнопок Skype, WatsApp или на переход на 
	страницу проекта Verivox. А лучше придумайте что-нибудь свое)).
 */
public class VisualElementTest {
	private TestUtil testUtil;

	@BeforeClass
	public void setUp() {
		testUtil = new TestUtil();
	}

	@BeforeMethod
	public void setUpBeforeMethod() {
		testUtil.getDriver().get(ANDERSEN_URL);
	}

	// Verifies that user can navigate to the web development service page
	@Test
	public void testWebDevelopmentServiceLink() {
		WebElement acceptCookiesButton = testUtil.getWait()
				.until(ExpectedConditions.visibilityOfElementLocated(ACCEPT_COOKIES_BUTTON_LOCATOR));
		acceptCookiesButton.click();
		testUtil.getDriver().findElement(WEB_DEVELOPMENT_LINK_LOCATOR).click();
		testUtil.getWait().until(ExpectedConditions.urlMatches(ANDERSEN_URL + ".+"));
		String expectedUrl = "https://andersenlab.com/services/web-development";
		String actualUrl = testUtil.getDriver().getCurrentUrl();
		assertEquals(actualUrl, expectedUrl, "URL address is not the same as expected!");
	}

	// Verifies that Instagram link icon is displayed
	@Test
	public void testInstagramIcon() {
		boolean isDisplayed = testUtil.getDriver().findElement(INSTAGRAM_ICON_LOCATOR).isDisplayed();
		assertTrue(isDisplayed, "Instagram link icon is not displayed!");
	}

	@AfterClass
	public void tearDown() {
		testUtil.getDriver().quit();
	}
}
