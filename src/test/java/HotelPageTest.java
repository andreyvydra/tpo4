import itma.pages.HotelPage;
import itma.pages.TrainPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static itma.utils.WebDriverFactory.getDriver;

public class HotelPageTest {
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
    public void hotelPageTest() {
        driverList.forEach(driver -> {
            HotelPage hotelPage;
            WebDriverWait wait;

            wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            hotelPage = new HotelPage(driver, wait);

            hotelPage.open();
            hotelPage.openFavorites();
        });
    }
}
