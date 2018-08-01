package pageObject;

import org.openqa.selenium.WebDriver;

public class AbstractPage {

    protected WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    public StartPage openStartPage(String url) {
        driver.navigate().to(url);

        return new StartPage(driver);
    }

    public void close() {
        driver.quit();
    }
}
