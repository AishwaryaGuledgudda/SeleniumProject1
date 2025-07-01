package com.package1;

import com.AbstractCompanents.AbstractCompanent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends AbstractCompanent {
    WebDriver driver ;
    public LoginPage(WebDriver driver) {
        //inorder to send the driver as argument to Parent AbstractCompanent user Super
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }

   // WebElement userEmail = driver.findElement(By.id("userEmail"));
    @FindBy(id="userEmail")
    WebElement userEmail;

    //driver.findElement(By.id("userPassword"));
    @FindBy(id = "userPassword")
    WebElement userPassword;

    //driver.findElement(By.id("login"))
    @FindBy(id="login")
    WebElement loginButton ;

    //error message when wrong email and passwprd
    @FindBy(css="[class*='flyInOut']")
    WebElement errorMessage;

    public ProductCatalog loginApplication(String email, String password){
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        loginButton.click();
        ProductCatalog productCatalog = new ProductCatalog(driver);
        return productCatalog;
    }
    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client");

    }

    public String getErrorMessage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText();
    }


}
