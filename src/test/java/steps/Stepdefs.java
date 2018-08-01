package steps;

import cucumber.api.java.ru.Допустим;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObject.ResultsPage;
import pageObject.StartPage;

import static org.junit.Assert.assertTrue;

public class Stepdefs {
    StartPage startPage;
    ResultsPage resultsPage;

    private WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "bin/chromedriver");
        return new ChromeDriver();
    }

    @Допустим("^пользователь открывет \"([^\"]*)\"$")
    public void пользователь_открывет(String address) throws Exception {
        startPage = new StartPage(getDriver());
        startPage.openStartPage(address);
    }

    @Когда("^он выбирает поиск из города \"([^\"]*)\" в \"([^\"]*)\" туда \"([^\"]*)\"го обратно \"([^\"]*)\"го$")
    public void он_выбирает_поиск_из_города_в_с_датами_с_по(String from, String to, String departure, String arrival) throws Exception {
        resultsPage = startPage
                        .Departure(from)
                        .Arrival(to)
                        .Dates(departure, arrival)
                        .Search();
    }

    @Тогда("^ему отбражется список возможных рейсов$")
    public void ему_отбражется_список_возможных_рейсов() throws Exception {
        int foundFlightsCount = resultsPage.getFlightsCount();
        assertTrue(foundFlightsCount > 0);
    }

    public void tearDown() {
        resultsPage.close();
    }

}