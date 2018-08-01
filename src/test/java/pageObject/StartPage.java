package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StartPage extends AbstractPage {

    private final WebDriverWait webDriverWait;

    public StartPage(WebDriver driver) {
        super(driver);
        webDriverWait = new WebDriverWait(driver, 10);
    }

    public ResultsPage find(String departureFrom, String arrivalTo, String dateDeparture, String dateArrival) {

        WebElement inputFrom = driver.findElement(By.name("from"));

        inputFrom.clear();
        inputFrom.sendKeys(departureFrom);

        By popupFrom = By.xpath("//*[@class='ac_match']");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(popupFrom)).click();

        WebElement inputTo = driver.findElement(By.name("to"));
        inputTo.clear();
        inputTo.sendKeys(arrivalTo);

        By popupTo = By.xpath("//*[@class='item ac_over']");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(popupTo)).click();

        By dateFrom = By.xpath("//a[text()='" + dateDeparture + "']");

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(dateFrom)).click();

        By dateTo = By.xpath("//a[text()='" + dateArrival + "']");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(dateTo)).click();

        By submit = By.name("search-btn-expand-bot");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(submit)).click();

        return new ResultsPage(driver);
    }
}
