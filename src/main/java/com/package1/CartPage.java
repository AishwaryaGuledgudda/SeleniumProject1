package com.package1;

import com.AbstractCompanents.AbstractCompanent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractCompanent {
    WebDriver driver ;
    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css=".totalRow button")
    WebElement checkOutele;

    public Boolean verifyProductDisplaying(){
        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase("ZARA COAT 3"));
        //anyMatch looks for the element if matches it returns Boolean value
        return match;
    }

    public CheckOutPage goToCheckOut(){
        //if match has "true" value then test passses if "false" then the TC fails
        checkOutele.click();
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        return checkOutPage;

    }
}
