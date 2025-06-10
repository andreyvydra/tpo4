package itma.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HotelPage extends BasePage {

    private static final By cityInput = By.xpath("//input[@data-qa-type=\"inputLocation.value.input\"]");
    private static final By dropDownItem = By.xpath("//div[@data-qa-type=\"uikit/popover.popoverBlock\"]//div[@data-qa-file=\"DropdownItem\"]");
    private static final By dateInput = By.xpath("//div[contains(@class, 'DateInputDesktop__inputBox_muoze')]");
    private static final String dateFrom = "//span[@data-date='%s']";
    private static final String dateTo = "//span[@data-date='%s']";
    private static final By searchButton = By.xpath("//span[text()='Искать']");
    private static final By chooseHotel = By.xpath("//span[@data-qa-file='Clickable']//span[text()='Выбрать']");

    public HotelPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void open() {
        driver.get("https://www.tbank.ru/travel/hotels/new/");
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
    }

    public void findHotel(String city, String from, String to) {
        selectCity(city);
        selectData(from, to);
        clickSearch();
    }

    private void selectCity(String city) {
        WebElement inputField = wait.until(ExpectedConditions.elementToBeClickable(
                cityInput
        ));
        inputField.click();

        inputField.clear();
        inputField.sendKeys(city);

        wait.until(ExpectedConditions.elementToBeClickable(
                dropDownItem
        ));

        inputField.sendKeys(Keys.TAB);
    }

    private void selectData(String from, String to) {
        WebElement dateElement = wait.until(ExpectedConditions.elementToBeClickable(
                dateInput
        ));
        dateElement.click();

        WebElement date11 = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(String.format(dateFrom, from))
        ));
        date11.click();

        WebElement date13 = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(String.format(dateTo, to))
        ));
        date13.click();
    }

    private void clickSearch(){
        WebElement searchButtonEl = driver.findElement(searchButton);
        searchButtonEl.click();

        wait.until(ExpectedConditions.elementToBeClickable(
                chooseHotel
        )).click();
    }

}
