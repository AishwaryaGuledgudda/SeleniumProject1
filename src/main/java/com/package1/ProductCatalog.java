package com.package1;

import com.AbstractCompanents.AbstractCompanent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalog extends AbstractCompanent {
    WebDriver driver ;

    public ProductCatalog(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver,this);

    }


    //List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
    //When we need list of elements just specify list before WebElements
    @FindBy(css=".mb-3")
    List<WebElement> products;



}
