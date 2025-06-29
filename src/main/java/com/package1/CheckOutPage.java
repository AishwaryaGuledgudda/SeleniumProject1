package com.package1;

import com.AbstractCompanents.AbstractCompanent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage extends AbstractCompanent {
    WebDriver driver ;
    public CheckOutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="input[placeholder='Select Country']")
    WebElement selectCountry;

    @FindBy(css=".action__submit")
    WebElement submit;

    public void selectCountry(String countryName) throws InterruptedException {
        Actions a = new Actions(driver);
        a.sendKeys(selectCountry,countryName).build().perform();
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ta-results")));
        Thread.sleep(5000);
        driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
    }

    public void submitOrder(){
        submit.click();
    }


}
