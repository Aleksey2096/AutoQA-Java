package pl.traineeship.autoqa.lesson10;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
	3)Написать метод в параметры которого принимаются два ВебЭлемента.
	
	метод выводит в консоль информацию какой из двух элементов располагается выше на странице,
	
	какой из элементов располагается левее на странице,
	
	а также какой из элементов занимает большую площадь.
	
	Параметры метода могут также включать в себя другие аргументы, если это необходимо.
 */
public class WebElementComparator {

	public static void main(String[] args) {
		WebDriver driver = DriverManager.create();
		driver.get("https://www.w3schools.com/");
		driver.findElement(By.id("accept-choices")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement learnToCodeH1 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.learntocodeh1")));
		WebElement learnToCodeH3 = driver.findElement(By.cssSelector("h3.learntocodeh3"));

		compare(learnToCodeH1, learnToCodeH3);
		/*
		 * Element 1 is higher on the page. Both elements are at the same position
		 * horizontally on the page. Element 1 occupies the largest area.
		 */

		driver.quit();
	}

	public static void compare(WebElement elem1, WebElement elem2) {
		int element1X = elem1.getLocation().getX();
		int element1Y = elem1.getLocation().getY();
		int element2X = elem2.getLocation().getX();
		int element2Y = elem2.getLocation().getY();
		int area1 = elem1.getSize().getWidth() * elem1.getSize().getHeight();
		int area2 = elem2.getSize().getWidth() * elem2.getSize().getHeight();

		compareHeight(element1Y, element2Y);
		compareLeftmostness(element1X, element2X);
		compareArea(area1, area2);
	}

	private static void compareHeight(int element1Y, int element2Y) {
		if (element1Y < element2Y) {
			System.out.println("Element 1 is higher on the page.");
		} else if (element1Y > element2Y) {
			System.out.println("Element 2 is higher on the page.");
		} else {
			System.out.println("Both elements are at the same height on the page.");
		}
	}

	private static void compareLeftmostness(int element1X, int element2X) {
		if (element1X < element2X) {
			System.out.println("Element 1 is located to the left on the page.");
		} else if (element1X > element2X) {
			System.out.println("Element 2 is located to the left on the page.");
		} else {
			System.out.println("Both elements are at the same position horizontally on the page.");
		}
	}

	private static void compareArea(int area1, int area2) {
		if (area1 > area2) {
			System.out.println("Element 1 occupies the largest area.");
		} else if (area1 < area2) {
			System.out.println("Element 2 occupies the largest area.");
		} else {
			System.out.println("Both elements occupy the same area.");
		}
	}
}
