package com.package1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver ;
    public LoginPage(WebDriver driver) {

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

    public void loginApplication(String email, String password){
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        loginButton.click();
    }
    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client");
    }


}
