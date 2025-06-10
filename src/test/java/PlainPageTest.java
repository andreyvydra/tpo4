import itma.pages.PlainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static itma.utils.WebDriverFactory.getDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlainPageTest {
    private static final String[] BROWSERS = {"chrome", "firefox"};
    public List<WebDriver> driverList;
    @BeforeEach
    public void setUp() {
        driverList = new ArrayList<>();
        for (String browser : BROWSERS) {
            WebDriver driver = getDriver(browser);
            driver.manage().window().maximize();
            driverList.add(driver);
        }
    }

    @Test
    void shouldOpenPlainPage() {
        driverList.forEach(driver -> {
            PlainPage plainPage;
            WebDriverWait wait;

            wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            plainPage = new PlainPage(driver, wait);

            plainPage.open();
        });
    }


    @Test
    void shouldSelectTicketsOnPlainPage() {
        driverList.forEach(driver -> {
            PlainPage plainPage;
            WebDriverWait wait;

            wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            plainPage = new PlainPage(driver, wait);

            plainPage.open();
            int initTabCount =plainPage.getInitTabCount();

            plainPage.selectTickets("Санкт-Петербург","июль 2025","1", "июль 2025", "5");
            assertTrue(
                    plainPage.verifyNewTabOpensWithUrl(initTabCount,
                            "/travel/flights/", driver.getWindowHandle())
            );
        });
    }


    @AfterEach
    void teardown() {
        for (WebDriver driver : driverList) {
            if (driver != null) {
                driver.quit();
            }
        }
    }


}
