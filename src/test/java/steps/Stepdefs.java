package steps;

import cucumber.api.java.After;
import cucumber.api.java.ru.Допустим;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObject.ResultsPage;
import pageObject.StartPage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertTrue;

public class Stepdefs {
    StartPage startPage;
    ResultsPage resultsPage;

    private WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "bin/chromedriver");

        ChromeDriver driver = new ChromeDriver();

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();

        return driver;
    }

    @Допустим("^пользователь открывет \"([^\"]*)\"$")
    public void openPage(String address) throws Exception {
        startPage = new StartPage(getDriver());
        startPage.openStartPage(address);
    }

    @Когда("^он выбирает поиск из города \"([^\"]*)\" в \"([^\"]*)\" туда \"([^\"]*)\"го обратно \"([^\"]*)\"го$")
    public void searchFlightsByDestinationAndDates(String from, String to, String departure, String arrival) throws Exception {
        resultsPage = startPage
                        .Departure(from)
                        .Arrival(to)
                        .Dates(departure, arrival)
                        .Search();
    }

    @Тогда("^ему отбражется список возможных рейсов$")
    public void checkFlightsFound() throws Exception {
        int foundFlightsCount = resultsPage.getFlightsCount();
        assertTrue(foundFlightsCount > 0);
    }

    @Когда("^он выбирает поиск \"([^\"]*)\" \"([^\"]*)\" через \"([^\"]*)\" дней на \"([^\"]*)\" дней$")
    public void он_выбирает_поиск(String from, String to, int delta, int duration) throws Exception {

        String departure = LocalDate.now().plusDays(delta).format(DateTimeFormatter.ofPattern("dd"));
        String arrival = LocalDate.now().plusDays(delta + duration).format(DateTimeFormatter.ofPattern("dd"));

        resultsPage = startPage.Departure(from).Arrival(to).Dates(departure, arrival).Search();
    }

    @Тогда("^ему отбражется список рейсов$")
    public void checkListOfFlightsNotEmpty() throws Exception {
        int foundFlightsCount = resultsPage.getFlightsCount();
        assertTrue(foundFlightsCount > 0);
    }

    @Тогда("^ему отбражется ничего не найдено$")
    public void ему_отбражется() throws Exception {
        assertTrue(resultsPage.errorFlightNotFoundErrorShowed());
    }


    @After
    public void tearDown() {
        resultsPage.close();
    }

}