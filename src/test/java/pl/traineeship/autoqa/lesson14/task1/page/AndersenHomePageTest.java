package pl.traineeship.autoqa.lesson14.task1.page;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.TmsLink;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.CustomAttribute;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pl.traineeship.autoqa.lesson14.task2.page.BaseTest;
import pl.traineeship.autoqa.lesson14.task2.util.AllureScreenshotListener;
import pl.traineeship.autoqa.lesson14.task2.util.ScreenshotListener;
import pl.traineeship.autoqa.lesson14.task2.util.TestRailListener;

/*
    Добавьте аллюр-репортинг к нашим тестам: своему проекту.

	1. Проведите рефакторинг в соответствии с PageObject.

	1. Напишите 2 тест кейса на проверку каких либо визуальных элементов сайта 
		https://andersenlab.com/ 
	(Например отображение кнопок Skype, WatsApp или на переход на 
	страницу проекта Verivox. А лучше придумайте что-нибудь свое)).
*/
@Listeners({ScreenshotListener.class, AllureScreenshotListener.class, TestRailListener.class})
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

    @TmsLink("https://autoqa96.testrail.io/index.php?/cases/view/1")
    @Feature("Link testing")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verifies that user can navigate to the web development service page")
    @Test(description = "Verifies that user can navigate to the web development service page",
            attributes = {@CustomAttribute(name = "testRailId", values = "1")})
    public void testWebDevelopmentServiceLink() {
        andersenHomePage
                .acceptCookies()
                .goToWebDevelopmentPage();

        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://andersenlab.com/services/web-development";
        assertEquals(actualUrl, expectedUrl, "URL address is not the same as expected!");
    }

    @TmsLink("https://autoqa96.testrail.io/index.php?/cases/view/2")
    @Feature("Element display testing")
    @Severity(SeverityLevel.MINOR)
    @Description("Verifies that Instagram link icon is displayed")
    @Test(description = "Verifies that Instagram link icon is displayed",
            attributes = {@CustomAttribute(name = "testRailId", values = "2")})
    public void testInstagramIcon() {
        boolean isDisplayed = andersenHomePage.isInstagramIconDisplayed();
        assertTrue(isDisplayed, "Instagram link icon is not displayed!");
    }
}
