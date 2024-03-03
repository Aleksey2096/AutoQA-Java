package pl.traineeship.autoqa.lesson10;

import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

/*
	 2)Написать программу, которая будет открывать пять различных страниц в новых окнах:
	http://www.automationpractice.pl/index.php
	https://zoo.waw.pl/
	https://www.w3schools.com/
	https://www.clickspeedtester.com/click-counter/
	https://andersenlab.com/
	
	Прописать цикл, который будет переключаться поочередно через все страницы,
	
	для каждой страницы выводить в консоль название и ссылку на эту страницу.
	
	И будет закрывать ту страницу в названии которой есть слово "Zoo".
 */
public class WebPageIterator {

	private static final String[] WEB_PAGES = {
			"http://www.automationpractice.pl/index.php",
			"https://zoo.waw.pl/",
			"https://www.w3schools.com/",
			"https://www.clickspeedtester.com/click-counter/",
			"https://andersenlab.com/"
	};

	private static final Pattern ZOO_PATTERN = Pattern.compile(Pattern.quote("Zoo"), Pattern.CASE_INSENSITIVE);

	public static void main(String[] args) {

		WebDriver driver = DriverManager.create();

		String zooPageHandle = null;

		for (int i = 0; i < WEB_PAGES.length; ++i) {
			if (i == 0) {
				driver.get(WEB_PAGES[i]);
			} else {
				driver.switchTo().newWindow(WindowType.WINDOW);
				driver.get(WEB_PAGES[i]);
			}
			System.out.println(driver.getTitle());
			System.out.println(driver.getCurrentUrl());
			if (ZOO_PATTERN.matcher(WEB_PAGES[i]).find()) {
				zooPageHandle = driver.getWindowHandle();
			}
		}

		if (zooPageHandle != null) {
			driver.switchTo().window(zooPageHandle);
			driver.close();
		}

		driver.quit();
	}
}
