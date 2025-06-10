package itma.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TrainPage extends BasePage {
    private final String button = "//div[contains(@class, 'QuickTags__searchTag') and text()='%s']";
    private final By dateButton = By.xpath("//form[@id='RailwaysSearchForm']//button[@name='dates']");
    private final By travellersButton = By.xpath("//form[@id='RailwaysSearchForm']//button[@name='travellers-trigger']");
    private final By submitButton = By.xpath("//form[@id='RailwaysSearchForm']//button[@type='submit']");
    private static final By cityInput = By.xpath("//input[@data-qa-type=\"inputLocation.value.input\"]");

    public TrainPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void open() {
        driver.get("https://www.tbank.ru/travel/trains/");
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
    }

    private void scrollToElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    public void fillForm(String fromValue, String toValue, String data, int persons)  {

        WebElement fromInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='fromText']")));
        fromInput.click();
    }




}
