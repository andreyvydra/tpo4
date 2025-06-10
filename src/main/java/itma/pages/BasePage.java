package itma.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public boolean verifyNewTabOpensWithUrl(int initialTabCount,String expectedUrlPart, String originalWindow) {

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(d -> d.getWindowHandles().size() > initialTabCount);

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains(expectedUrlPart));

        String currentUrl = driver.getCurrentUrl();

        boolean fl = currentUrl.contains(expectedUrlPart);

        driver.close();
        driver.switchTo().window(originalWindow);

        return fl;


    }

    public int getInitTabCount(){
        return driver.getWindowHandles().size();
    }

    public boolean isUrlContainsPartPath(String partPath) {
        return driver.getCurrentUrl().contains(partPath);
    }
}
