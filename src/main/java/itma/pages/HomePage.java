package itma.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class HomePage extends BasePage {

    private final String navigationButton = "//a[@data-test='clickableArea' and contains(@href,'/travel/')]//p[text()='%s']";
    private final String helpCard = "//a[contains(@href, '/help/travel/planetickets/about/refund/') and .//b[text()='%s']]";


    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clickNavigationMenuItem(String itemName) {
        By itemLocator = By.xpath(String.format(navigationButton, itemName));
        wait.until(ExpectedConditions.elementToBeClickable(itemLocator)).click();
    }

    public boolean isUrlContainsPartPath(String partPath) {
        System.out.println(driver.getCurrentUrl());
        return driver.getCurrentUrl().contains(partPath);
    }

    public void open() {
        driver.get("https://www.tbank.ru/travel/");
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
    }

    public void clickToHelpCard(String itemName) {
        By itemLocator = By.xpath(String.format(helpCard, itemName));

        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(itemLocator));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);

        wait.until(ExpectedConditions.elementToBeClickable(element));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public int getInitTabCount(){
        return driver.getWindowHandles().size();
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

    


}
