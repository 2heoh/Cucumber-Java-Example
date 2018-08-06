package steps;

import cucumber.api.java.After;
import cucumber.api.java.ru.Допустим;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObject.SelectPage;
import pageObject.StartPage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertTrue;

public class Stepdefs {
    private StartPage startPage;
    private SelectPage resultsPage;

    private WebDriver driver;

    private WebDriver getDriver() {

        if(driver == null) {
            System.setProperty("webdriver.chrome.driver", "bin/chromedriver");
            driver = new ChromeDriver();
        }

        return driver;
    }

    @Допустим("^мне нужно найти рейс$")
    public void openPage() throws Exception {
        startPage = PageFactory.getStartPage(getDriver());
    }

    @Когда("^я ищу рейс из \"([^\"]*)\" в \"([^\"]*)\" туда \"([^\"]*)\"го обратно \"([^\"]*)\"го$")
    public void searchFlightsByDestinationAndDates(String from, String to, String departure, String arrival) {
        startPage.Departure(from)
            .Arrival(to)
            .Dates(departure, arrival)
            .Search();
    }

    @Тогда("^мне отбражется список возможных рейсов$")
    public void checkFlightsFound() throws Exception {

        resultsPage = PageFactory.getSelectPage(getDriver());

        int foundFlightsCount = resultsPage.getFlightsCount();
        assertTrue(foundFlightsCount > 0);
    }

    @Когда("^я ищу рейс (\\S+) (\\S+) (\\d+\\s\\S+) (\\d+\\s\\S+)$")
    public void он_выбирает_поиск(String from, String to, String delta, String duration) {
        int deltaDays = Integer.parseInt(delta.replaceAll("\\D+",""));
        int durationDays = Integer.parseInt(duration.replaceAll("\\D+",""));

        String departure = LocalDate.now().plusDays(deltaDays).format(DateTimeFormatter.ofPattern("dd"));
        String arrival = LocalDate.now().plusDays((deltaDays + durationDays)).format(DateTimeFormatter.ofPattern("dd"));

        resultsPage = startPage.Departure(from).Arrival(to).Dates(departure, arrival).Search();
    }

    @Тогда("^мне отбражется список рейсов$")
    public void checkListOfFlightsNotEmpty() {
        int foundFlightsCount = resultsPage.getFlightsCount();
        assertTrue(foundFlightsCount > 0);
    }

    @Тогда("^мне отбражется ничего не найдено$")
    public void ему_отбражется() {
        assertTrue(resultsPage.errorFlightNotFoundErrorShowed());
    }

    @After
    public void tearDown() {
        resultsPage.close();
    }

}