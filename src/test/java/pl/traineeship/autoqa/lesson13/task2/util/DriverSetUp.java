package pl.traineeship.autoqa.lesson13.task2.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverSetUp {
	private static WebDriver driver;

	public static WebDriver getInstance() {
		if (driver == null) {
			driver = setUpDriver();
		}
		return driver;
	}

	private static WebDriver setUpDriver() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		return driver;
	}
}
