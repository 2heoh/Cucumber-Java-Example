package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SelectPage extends Page {

    @FindBy(xpath = "//*[@data-qa='noSearchResultFlight_popup']")
    private WebElement noFlightFoundMessage;

    public SelectPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public int getFlightsCount() {
        return driver.findElements(By.className("js-select-line")).size();
    }

    public boolean errorFlightNotFoundErrorShowed() {
        webDriverWait.until(ExpectedConditions.visibilityOf(noFlightFoundMessage));
        return noFlightFoundMessage.isDisplayed();
    }

    public boolean isSelectPage() {
        webDriverWait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));

        By selector = By.xpath("//*[@data-qa='select_pageCurrent_progressBar']");

        return driver.findElements(selector).size() == 1;
    }

}
