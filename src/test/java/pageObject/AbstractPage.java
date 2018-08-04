package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage {

    protected WebDriver driver;
    protected final WebDriverWait webDriverWait;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        this.driver.manage().window().maximize();
        webDriverWait = new WebDriverWait(driver, 20);
    }

    public void close() {
        driver.quit();
    }
}
