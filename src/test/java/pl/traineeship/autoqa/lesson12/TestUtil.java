package pl.traineeship.autoqa.lesson12;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestUtil {

	public static final String ANDERSEN_URL = "https://andersenlab.com/";
	public static final String REGISTRATION_URL = "https://qa-course-01.andersenlab.com/registration";
	public static final String GOOGLE_SEARCH_URL = "https://www.google.com/search";
	public static final String GUINNESS_REGISTER_URL = "https://www.guinnessworldrecords.com/account/register?";
	public static final String TUTORIALS_URL = "https://www.hyrtutorials.com/p/alertsdemo.html";
	public static final String FORM_SUBMIT_URL = "https://www.w3schools.com/html/tryit.asp?filename=tryhtml_form_submit";
	public static final String FIRST_NAME = "Sam";
	public static final String LAST_NAME = "Meller";
	public static final String YEAR = "1990";
	public static final String MONTH = "August";
	public static final String EMAIL = "sam@sam.sam";
	public static final String PASSWORD = "password1";
	public static final String COUNTRY_CODE = "PL";
	public static final String CITY = "Krakow";
	public static final String DAY_OF_BIRTH = "20";
	public static final String MONTH_OF_BIRTH = "4";
	public static final String FINAL_STEP_MESSAGE = "Final step of this task";
	public static final By ACCEPT_COOKIES_BUTTON_LOCATOR = By
			.xpath("//div[@class='CookiesPolicy-module--contentWrapper--c9ed1']/button[2]");
	public static final By WEB_DEVELOPMENT_LINK_LOCATOR = By
			.xpath("//section/div/a[@href='/services/web-development']");
	public static final By INSTAGRAM_ICON_LOCATOR = By.xpath("//a/*[@aria-label='Instagram link']");
	public static final By FIRST_NAME_INPUT_LOCATOR = By.xpath("//input[@name='firstName']");
	public static final By LAST_NAME_INPUT_LOCATOR = By.xpath("//input[@name='lastName']");
	public static final By DATE_OF_BIRTH_INPUT_LOCATOR = By.xpath("//input[@name='dateOfBirth']");
	public static final By YEAR_SELECT_LOCATOR = By
			.xpath("//div[@class='react-datepicker__month-container']/div[1]/div[1]/select[1]");
	public static final By MONTH_SELECT_LOCATOR = By
			.xpath("//div[@class='react-datepicker__month-container']/div[1]/div[1]/select[2]");
	public static final By DAY_10_DIV_LOCATOR = By
			.xpath("//div[@class='react-datepicker__month']/div[2]/div[contains(@class,'10')]");
	public static final By EMAIL_INPUT_LOCATOR = By.xpath("//input[@name='email']");
	public static final By PASSWORD_INPUT_LOCATOR = By.xpath("//input[@name='password']");
	public static final By PASSWORD_CONFIRMATION_INPUT_LOCATOR = By.xpath("//input[@name='passwordConfirmation']");
	public static final By SUBMIT_BUTTON_LOCATOR = By.xpath("//button[@type='submit']");
	public static final By DENY_COOKIES_BUTTON_LOCATOR = By.xpath("//button[@id='W0wltc']");
	public static final By TEXT_AREA_LOCATOR = By.xpath("//textarea[@id='APjFqb']");
	public static final By CLEAR_SEARCH_LOCATOR = By.xpath("//div[@class='M2vV3 vOY7J']");
	public static final By GUINNESS_REGISTER_LINK_LOCATOR = By
			.xpath("//div[@id='search']//a[contains(@href,'https://www.guinnessworldrecords.com/account/register')]");
	public static final By TUTORIALS_LINK_LOCATOR = By
			.xpath("//div[@id='search']//a[contains(@href,'https://www.hyrtutorials.com/p/alertsdemo.html')]");
	public static final By ACCEPT_BUTTON_LOCATOR = By.xpath("//div[@id='accept-choices']");
	public static final By FIRST_NAME_INPUT_LOCATOR_2 = By.xpath("//input[@id='fname']");
	public static final By LAST_NAME_INPUT_LOCATOR_2 = By.xpath("//input[@id='lname']");
	public static final By SUBMIT_BUTTON_LOCATOR_2 = By.xpath("//input[@type='submit']");
	public static final By NOTE_DIV_LOCATOR = By.xpath("//body[@class='w3-container']/div[2]");
	public static final By LAST_NAME_INPUT_LOCATOR_3 = By.xpath("//input[@id='LastName']");
	public static final By FIRST_NAME_INPUT_LOCATOR_3 = By.xpath("//input[@id='FirstName']");
	public static final By DAY_OF_BIRTH_LOCATOR = By.xpath("//input[@id='DateOfBirthDay']");
	public static final By MONTH_OF_BIRTH_LOCATOR = By.xpath("//input[@id='DateOfBirthMonth']");
	public static final By YEAR_OF_BIRTH_LOCATOR = By.xpath("//input[@id='DateOfBirthYear']");
	public static final By COUNTRY_SELECT_LOCATOR = By.xpath("//select[@id='Country']");
	public static final By CITY_INPUT_LOCATOR = By.xpath("//input[@id='State']");
	public static final By EMAIL_INPUT_LOCATOR_2 = By.xpath("//input[@id='EmailAddress']");
	public static final By CONFIRM_EMAIL_INPUT_LOCATOR = By.xpath("//input[@id='ConfirmEmailAddress']");
	public static final By PASSWORD_INPUT_LOCATOR_2 = By.xpath("//input[@id='Password']");
	public static final By CONFIRM_PASSWORD_INPUT_LOCATOR = By.xpath("//input[@id='ConfirmPassword']");
	public static final By ERROR_SPAN_LOCATOR = By.xpath("//span[@class='field-validation-error']");
	public static final By CONSENT_BUTTON_LOCATOR = By.xpath("//button[@aria-label='Consent']");
	public static final By OUTPUT_DIV_LOCATOR = By.xpath("//div[@id='output']");
	public static final By CLICK_ME_1_BUTTON_LOCATOR = By.xpath("//button[@id='alertBox']");
	public static final By CLICK_ME_2_BUTTON_LOCATOR = By.xpath("//button[@id='confirmBox']");
	public static final By CLICK_ME_3_BUTTON_LOCATOR = By.xpath("//button[@id='promptBox']");
	private static final Duration DEFAULT_WAIT_TIME = Duration.ofSeconds(5);

	private WebDriver driver;
	private WebDriverWait wait;

	public TestUtil() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebDriverWait getWait() {
		return wait;
	}
}
