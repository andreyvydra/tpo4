package itma.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class TrainPage extends BasePage {
    private final String button = "//div[contains(@class, 'QuickTags__searchTag') and text()='%s']";
    private final By dateButton = By.xpath("//form[@id='RailwaysSearchForm']//button[@name='dates']");
    private final By travellersButton = By.xpath("//form[@id='RailwaysSearchForm']//button[@name='travellers-trigger']");
    private final By submitButton = By.xpath("//form[@id='RailwaysSearchForm']//button[@type='submit']");

    public TrainPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void open() {
        driver.get("https://www.tbank.ru/travel/trains/");
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("form")));
    }

    private void scrollToElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    public void fillForm(String from, String to, String dateText, int adults) {

        List<WebElement> wrappers = driver.findElements(
                By.xpath("//div[contains(@class, 'ott-quick-tags-QuickTags__tagsWrapper')]")
        );

        try {
            for (WebElement wrapper : wrappers) {
                // Внутри каждого ищем "Москва" или "Санкт-Петербург"
                List<WebElement> cities = wrapper.findElements(
                        By.xpath(".//div[contains(@class, 'QuickTags__searchTag') and (text()='Москва' or text()='Санкт-Петербург')]")
                );

                // Кликаем на первый подходящий, если найден
                if (!cities.isEmpty()) {
                    cities.get(0).click();
                    Thread.sleep(500); // Пауза для стабильности
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
