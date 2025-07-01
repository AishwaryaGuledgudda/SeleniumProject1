package com.package1;

import com.AbstractCompanents.AbstractCompanent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractCompanent {
    WebDriver driver ;
    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public Boolean verifyProductDisplaying() {
        List<WebElement> orderProducts = driver.findElements(By.cssSelector("tr td:nth-child(3)"));
        Boolean match = orderProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase("ZARA COAT 3"));
        //anyMatch looks for the element if matches it returns Boolean value
        return match;
    }
}
