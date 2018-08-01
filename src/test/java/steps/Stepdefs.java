package steps;

import cucumber.api.java.After;
import cucumber.api.java.ru.Допустим;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Stepdefs {

    WebDriver driver;
    private WebDriverWait webDriverWait;


    @Допустим("^пользователь открывет \"([^\"]*)\"$")
    public void пользователь_открывет(String address) throws Exception {
        System.setProperty("webdriver.chrome.driver", "bin/chromedriver");
        driver = new ChromeDriver();
        driver.get(address);
        webDriverWait = new WebDriverWait(driver, 10);
    }

    @Когда("^он выбирает поиск из города \"([^\"]*)\" в \"([^\"]*)\" туда \"([^\"]*)\"го обратно \"([^\"]*)\"го$")
    public void он_выбирает_поиск_из_города_в_с_датами_с_по(String depatureFrom, String arivalTo, String dateDeparture, String dateArrival) throws Exception {

        WebElement inputFrom = driver.findElement(By.name("from"));
        inputFrom.clear();
        inputFrom.sendKeys(depatureFrom);

        By popupFrom = By.xpath("//*[@class='ac_match']");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(popupFrom)).click();

        WebElement inputTo = driver.findElement(By.name("to"));
        inputTo.clear();
        inputTo.sendKeys(arivalTo);

        By popupTo = By.xpath("//*[@class='item ac_over']");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(popupTo)).click();

        By dateFrom = By.xpath("//a[text()='" + dateDeparture +"']");

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(dateFrom)).click();

        By dateTo = By.xpath("//a[text()='" + dateArrival + "']");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(dateTo)).click();

        By submit = By.name("search-btn-expand-bot");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(submit)).click();
    }

    @Тогда("^ему отбражется список возможных рейсов$")
    public void ему_отбражется_список_возможных_рейсов() throws Exception {
        int foundFlightsCount = driver.findElements(By.className("select-flights")).size();
        Assert.assertTrue(foundFlightsCount > 0);
    }

    @After
    public void tearDown() {
        driver.close();
    }



}