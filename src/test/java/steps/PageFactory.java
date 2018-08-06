package steps;

import org.openqa.selenium.WebDriver;
import pageObject.SelectPage;
import pageObject.StartPage;

public class PageFactory {

    public static SelectPage getSelectPage(WebDriver driver) throws Exception {

        SelectPage page = new SelectPage(driver);

        if(!page.isSelectPage()) {
            throw new Exception("This is not a select page!");
        }

        return page;
    }

    public static StartPage getStartPage(WebDriver driver) throws Exception {
        StartPage page = new StartPage(driver);

        page.openStartPage();

        if(!page.isSearchPage()) {
            throw new Exception("This is not a start page!");
        }

        return page;

    }
}
