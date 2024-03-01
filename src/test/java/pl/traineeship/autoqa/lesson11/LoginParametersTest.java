package pl.traineeship.autoqa.lesson11;

import static pl.traineeship.autoqa.lesson11.TestUtil.BASE_URL;
import static pl.traineeship.autoqa.lesson11.TestUtil.MY_ACCOUNT_HEADER_LOCATOR;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/*
	Task5: Решить предыдущую задачу используя аннотацию @Parameters. А также создать 
	для работы с данным тестом дополнительный xml файл testngParametersHome.xml. 
 */
// Run this test class using 'testngParametersHome.xml'
public class LoginParametersTest {
	/*
	 * The 'testUtil' variable is declared as static to ensure a single instance is
	 * used when running 'testLogin' multiple times with different parameters within
	 * a single suite.
	 */
	private static TestUtil testUtil;
	private static SoftAssert softAssert = new SoftAssert();

	@BeforeSuite
	public void setUp() {
		testUtil = new TestUtil();
		testUtil.createTestAccounts();
	}

	// Verifies that user can login with valid credentials
	@Test
	@Parameters({ "email", "password" })
	public void testLogin(String email, String password) {
		testUtil.logIn(email, password);
		String expectedHeaderText = "My account";
		String actualHeaderText = testUtil.getDriver().findElement(MY_ACCOUNT_HEADER_LOCATOR).getText();
		softAssert.assertEquals(expectedHeaderText, actualHeaderText,
				"Personal account header is not the same as expected!");
		String expectedUrl = BASE_URL;
		String actualUrl = testUtil.getDriver().getCurrentUrl();
		softAssert.assertEquals(expectedUrl, actualUrl, "URL after login is not the same as expected!");
		softAssert.assertAll();
		testUtil.logOut();
	}

	@AfterSuite
	public void tearDown() {
		testUtil.deleteTestAccounts();
		testUtil.getDriver().quit();
	}
}