package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResultsPage extends StartPage {

    public ResultsPage(WebDriver driver) {
        super(driver);
    }

    public int getFlightsCount() {
        return driver.findElements(By.className("select-flights")).size();
    }

}
