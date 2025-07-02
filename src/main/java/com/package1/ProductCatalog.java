package com.package1;

import com.AbstractCompanents.AbstractCompanent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalog extends AbstractCompanent {
    WebDriver driver ;

    public ProductCatalog(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    //List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
    //******When we need list of elements just specify list before WebElements****//
    @FindBy(css=".mb-3")
    List<WebElement> products;


    By productsBY = By.cssSelector(".mb-3");

    //Action Methods
    public List<WebElement> getProductList(){
        waitForElementToAppear(productsBY); //Producs loads and appears
        return products; //Once products appears, collect them and return
    }
    public WebElement getProductByName(String productName){ //User can send the productName and this method finds the product using the name
        WebElement zaraProduct =  products.stream().filter(product->
                product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        // stream helps to iterate through the list of products
        // when each product is iterated it is stored in product variable
        return zaraProduct;
    }
    public void addProductToCart(String productName){
       WebElement prod =  getProductByName(productName);
       assert prod != null;
       prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
    }


}
