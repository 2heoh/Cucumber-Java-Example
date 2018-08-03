package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ResultsPage extends StartPage {

    public ResultsPage(WebDriver driver) {
        super(driver);
    }

    public int getFlightsCount() {
        return driver.findElements(By.className("js-select-line")).size();
    }

    public boolean errorFlightNotFoundErrorShowed() {
        By noFlightFound = By.xpath("//*[@data-qa='noSearchResultFlight_popup']");
        return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(noFlightFound)).isDisplayed();
    }

}
