package pl.traineeship.autoqa.lesson11;

import static pl.traineeship.autoqa.lesson11.TestUtil.BASE_URL;
import static pl.traineeship.autoqa.lesson11.TestUtil.MY_ACCOUNT_HEADER_LOCATOR;
import static pl.traineeship.autoqa.lesson11.TestUtil.TEST_EMAIL_1;
import static pl.traineeship.autoqa.lesson11.TestUtil.TEST_EMAIL_2;
import static pl.traineeship.autoqa.lesson11.TestUtil.TEST_EMAIL_3;
import static pl.traineeship.autoqa.lesson11.TestUtil.TEST_PASSWORD_1;
import static pl.traineeship.autoqa.lesson11.TestUtil.TEST_PASSWORD_2;
import static pl.traineeship.autoqa.lesson11.TestUtil.TEST_PASSWORD_3;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/*
	Task4: Заранее создать трех пользователей для нашего сайта. Написать тест 
	используя @DataProvider который будет проверять логин этих трех пользователей.
 */
public class LoginDataProviderTest {
	private TestUtil testUtil;
	private SoftAssert softAssert = new SoftAssert();

	@BeforeClass
	public void setUp() {
		testUtil = new TestUtil();
		testUtil.createTestAccounts();
	}

	// Verifies that user can login with valid credentials
	@Test(dataProvider = "loginParameters")
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

	@DataProvider(name = "loginParameters")
	public Object[][] createData() {
		return new Object[][] {
				{ TEST_EMAIL_1, TEST_PASSWORD_1 },
				{ TEST_EMAIL_2, TEST_PASSWORD_2 },
				{ TEST_EMAIL_3, TEST_PASSWORD_3 }
		};
	}

	@AfterClass
	public void tearDown() {
		testUtil.deleteTestAccounts();
		testUtil.getDriver().quit();
	}
}
