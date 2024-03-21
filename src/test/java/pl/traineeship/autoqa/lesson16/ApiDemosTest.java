package pl.traineeship.autoqa.lesson16;

import static org.testng.Assert.assertEquals;

import java.time.LocalDate;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ApiDemosTest extends BaseTest {

	private static ApiDemos apiDemos;

	@BeforeClass
	public void setUpClass() {
		apiDemos = new ApiDemos(driver);
	}

	@BeforeMethod
	public void setUpMethod() {
		apiDemos.clickOnViewsButton();
	}

	@Test(description = "Verifies that number of menu items on views page is 42.")
	public void testCheckNumberOfMenuItemsOnViewsPage() {
		int actualNumberOfMenuItems = apiDemos.countViewsMenuItems();
		assertEquals(actualNumberOfMenuItems, EXPECTED_NUMBER_OF_MENU_ITEMS,
				"Number of menu items on views page is not the same as expected.");
	}

	@Test(description = "Verifies that date can be set to the following day and time can be set to 11:11 PM.")
	public void testSettingDateAndTimeOnDialogPage() {
		apiDemos
				.clickOnDateWidgetsButton()
				.clickOnDialogButton()
				.clickOnChangeDateButton()
				.pickFollowingDay()
				.clickOnChangeTimeSpinnerButton()
				.setTime(HOURS, MINUTES, TIME_INDICATOR);

		String actualTimeString = apiDemos.getTimeString();
		String actualSelectedDay = apiDemos.getSelectedDay();
		String expectedSelectedDay = String.valueOf(LocalDate.now().getDayOfMonth() + 1);

		softAssert.assertEquals(actualTimeString, EXPECTED_TIME_STRING, "Selected time is not the same as expected.");
		softAssert.assertTrue(actualSelectedDay.equals(expectedSelectedDay) || actualSelectedDay.equals(FIRST_DAY),
				"Selected day is not the same as expected.");
		softAssert.assertAll();
	}

	@Test(description = "Verifies that counter increments after each click on 'next' button.")
	public void testCounterOnTextSwitcherPage() {
		int actualCounterValue = apiDemos
				.openTextSwitcherPage()
				.clickOnNextButton(EXPECTED_COUNTER_VALUE)
				.getCounterValue();
		assertEquals(actualCounterValue, EXPECTED_COUNTER_VALUE, "Counter value is not the same as expected.");
	}

	@AfterMethod
	public void tearDownMethod() {
		driver.closeApp();
		driver.launchApp();
	}
}
