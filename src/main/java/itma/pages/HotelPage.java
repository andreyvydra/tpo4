package itma.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HotelPage extends BasePage {

    public HotelPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void open() {
        driver.get("https://www.tbank.ru/travel/hotels/new/");
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
    }

    public void openFavorites() {
        WebElement inputField = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("input[data-qa-type='inputLocation.value.input']")
        ));
        inputField.click();

        inputField.clear();
        inputField.sendKeys("Москва");


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }


        inputField.sendKeys(Keys.TAB);
        selectData();

        clickSearch();

    }

    private void selectData(){
        WebElement dateInput = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class, 'DateInputDesktop__inputBox_muoze')]")
        ));
        dateInput.click();

        WebElement date11 = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@data-date='2025-06-11']")
        ));
        date11.click();

        WebElement date13 = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@data-date='2025-06-13']")
        ));
        date13.click();
    }

    private void clickSearch(){
        WebElement searchButton = driver.findElement(By.xpath("//span[text()='Искать']"));
        searchButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@data-qa-file='Clickable']//span[text()='Выбрать']"))).click();
    }

}
