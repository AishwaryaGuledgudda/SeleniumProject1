package com.AbstractCompanents;

import com.package1.CartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractCompanent {

    WebDriver driver;
    public AbstractCompanent(WebDriver driver) {
        this.driver = driver ;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css="[routerlink*='cart']")
    WebElement cartButton;

    public void waitForElementToAppear(By findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }
    //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    //wait.until(ExpectedConditions.visibilityOfElementeLocated(By.cssSelector(".mb-3")));

    public CartPage goToCartPage() throws InterruptedException {
        //Coze this cart button is on common header
        Thread.sleep(3000);
        //wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("[routerlink*='cart']"))));
        cartButton.click();
        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }

}
