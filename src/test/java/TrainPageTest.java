import itma.pages.TrainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static itma.utils.WebDriverFactory.getDriver;

public class TrainPageTest {
    private static final String[] BROWSERS = {"chrome"};
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
    void shouldOpenPageTrain() {
        driverList.forEach(driver -> {
            TrainPage trainPage;
            WebDriverWait wait;

            wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            trainPage = new TrainPage(driver, wait);

            trainPage.open();
        });
    }

    @Test
    void shouldSelectTicket() {
        driverList.forEach(driver -> {
            TrainPage trainPage;
            WebDriverWait wait;

            wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            trainPage = new TrainPage(driver, wait);

            trainPage.open();
            trainPage.fillForm("Москва", "Санкт-Петербург", "11 июн", 2);
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
