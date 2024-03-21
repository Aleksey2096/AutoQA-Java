package pl.traineeship.autoqa.lesson15.util;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverUtil {

    public static void configureDriver() {
        Configuration.browser = Browsers.CHROME;
        Configuration.timeout = 5000;
        Configuration.reportsFolder = "target/reports";
        Configuration.downloadsFolder = "target/downloads";
//        Configuration.browserSize = "1600x900";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-fullscreen", "--start-incognito", "--disable-notifications");
        Configuration.browserCapabilities = options;
    }
}
