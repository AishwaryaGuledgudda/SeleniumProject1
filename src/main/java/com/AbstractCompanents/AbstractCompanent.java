package com.AbstractCompanents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AbstractCompanent {

    WebDriver driver;
    public AbstractCompanent(WebDriver driver) {

        this.driver = driver ;
        PageFactory.initElements(driver,this);
    }

    public void waitForElementToAppear(){

    }
    //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    //wait.until(ExpectedConditions.visibilityOfElementeLocated(By.cssSelector(".mb-3")));

}
