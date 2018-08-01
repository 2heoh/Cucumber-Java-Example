package steps;

import cucumber.api.java.ru.Допустим;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObject.ResultsPage;
import pageObject.StartPage;

public class Stepdefs {
    WebDriver driver;
    StartPage startPage;
    ResultsPage resultsPage;

    @Допустим("^пользователь открывет \"([^\"]*)\"$")
    public void пользователь_открывет(String address) throws Exception {

        System.setProperty("webdriver.chrome.driver", "bin/chromedriver");
        driver = new ChromeDriver();

        startPage = new StartPage(driver);
        startPage.openStartPage(address);
    }

    @Когда("^он выбирает поиск из города \"([^\"]*)\" в \"([^\"]*)\" туда \"([^\"]*)\"го обратно \"([^\"]*)\"го$")
    public void он_выбирает_поиск_из_города_в_с_датами_с_по(String depatureFrom, String arrivalTo, String dateDeparture, String dateArrival) throws Exception {
        resultsPage = startPage.find(depatureFrom, arrivalTo, dateDeparture, dateArrival);
    }

    @Тогда("^ему отбражется список возможных рейсов$")
    public void ему_отбражется_список_возможных_рейсов() throws Exception {
        int foundFlightsCount = resultsPage.getFlightsCount();
        Assert.assertTrue(foundFlightsCount > 0);
    }

    public void tearDown() {
        driver.close();
    }

}