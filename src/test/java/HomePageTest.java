import itma.pages.HomePage;
import itma.utils.WebDriverFactory;
import org.junit.jupiter.api.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.Context;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.DriverManager;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static itma.utils.WebDriverFactory.getDriver;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePageTest {
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
    void shouldOpenPageHome() {
        driverList.forEach(driver -> {
            HomePage homePage;
            WebDriverWait wait;

            wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            homePage = new HomePage(driver, wait);

            homePage.open();
        });
    }

    @Test
    void shouldOpenTourPage() {
        driverList.forEach(driver -> {
            HomePage homePage;
            WebDriverWait wait;

            wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            homePage = new HomePage(driver, wait);

            homePage.open();
            homePage.clickNavigationMenuItem("Туры");

            assertTrue(homePage.isUrlContainsPartPath("/travel/tours/"));
        });
    }

    @Test
    void shouldOpenHelpPage() {
        driverList.forEach(driver -> {
            HomePage homePage;
            WebDriverWait wait;

            wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            homePage = new HomePage(driver, wait);

            homePage.open();
            int initTabCount =homePage.getInitTabCount();
            homePage.clickToHelpCard("Если отменили рейс");
            assertTrue(
                    homePage.verifyNewTabOpensWithUrl(initTabCount,
                            "/help/travel/planetickets/about/refund", driver.getWindowHandle())
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
