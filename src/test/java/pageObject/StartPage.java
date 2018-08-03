package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StartPage extends AbstractPage {


    public StartPage(WebDriver driver) {
        super(driver);

    }

    public StartPage openStartPage(String url) {
        driver.navigate().to(url);

        return new StartPage(driver);
    }

    public StartPage Departure(String departureFrom) {

        By inputFromBy = By.name("from");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(inputFromBy));
        WebElement inputFrom = driver.findElement(inputFromBy);

        inputFrom.clear();
        inputFrom.sendKeys(departureFrom);

        By popupFrom = By.xpath("//*[@class='ac_match']");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(popupFrom)).click();

        return this;
    }

    public StartPage Arrival(String arrivalTo) {
        WebElement inputTo = driver.findElement(By.name("to"));
        inputTo.clear();
        inputTo.sendKeys(arrivalTo);

        By popupTo = By.xpath("//*[@class='item ac_over']");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(popupTo)).click();

        return this;
    }

    public StartPage Dates (String dateDeparture, String dateArrival) {

        By dateFrom = By.xpath("//a[text()='" + dateDeparture + "']");

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(dateFrom)).click();

        By dateTo = By.xpath("//a[text()='" + dateArrival + "']");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(dateTo)).click();
        return this;
    }

    public ResultsPage Search() {
        By submit = By.name("search-btn-expand-bot");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(submit)).click();

        return new ResultsPage(driver);
    }
}
