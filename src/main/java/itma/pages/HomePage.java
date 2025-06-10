package itma.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


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





    


}
