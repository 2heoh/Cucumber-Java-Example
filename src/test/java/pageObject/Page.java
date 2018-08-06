package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;

    public String getBaseURL() {
        return System.getenv("BASE_URL");
    }

    public Page(WebDriver driver) {
        this.driver = driver;
        this.driver.manage().window().maximize();
        webDriverWait = new WebDriverWait(driver, 20);
    }

    public void close() {
        driver.quit();
    }


}
