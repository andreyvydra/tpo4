package itma.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class PlainPage extends BasePage {
    private static final By inputTo = By.xpath("//input[@aria-labelledby=\"Input_Куда\"]");
    private static final By dropDownItem = By.xpath("//div[@data-qa-file=\"DropdownList\"]//div[@data-qa-file=\"DropdownItem\"]");
    private static final String calendarDay = "//div[@data-qa-file='CalendarMonth' and contains(., '%s')]//table//tbody//td//div[contains(text(),'%s')]";
    private static final By searchButton = By.xpath("//button[@data-qa-file='SearchForm']");

    public PlainPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }


    public void open() {
        driver.get("https://www.tbank.ru/travel/flights");
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
    }

    public void selectTickets(String to, String fromMonth, String fromDay, String toMonth, String toDay) {

        WebElement toInput = wait.until(ExpectedConditions.elementToBeClickable(inputTo));
        toInput.click();
        System.out.println(1);
        toInput.sendKeys(to);
        wait.until(ExpectedConditions.elementToBeClickable(dropDownItem));
        toInput.sendKeys(Keys.TAB);

        selectDate(fromMonth, fromDay);
        selectDate(toMonth, toDay);

        selectFind();
    }

    public void selectDate(String monthYear, String day) {
        String xpath = String.format(
                calendarDay,
                monthYear, day
        );
        WebElement dayElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        dayElement.click();
    }

    private void selectFind(){
        WebElement searchButtonEl = wait.until(ExpectedConditions.elementToBeClickable(
                searchButton
        ));
        searchButtonEl.click();
    }



}
