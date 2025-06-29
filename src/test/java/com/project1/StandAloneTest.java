package com.project1;

import com.package1.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.swing.*;
import java.time.Duration;
import java.util.List;

public class StandAloneTest {
    public static void main(String[] args) throws InterruptedException {
        // Import WebDriverManager from github link its not available on MavenRepo
        WebDriverManager.chromedriver().setup();
        //Chrome driver will be automatically downloaded (latest version used on laptop)
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client");
        LoginPage loginPage = new LoginPage(driver);
        driver.findElement(By.id("userEmail")).sendKeys("abg@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("qwerty123456!A");
        driver.findElement(By.id("login")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement zaraProduct =  products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null); // when each product is iterated it is stored in product variable
        // stream helps to iterate through the list of products
        assert zaraProduct != null;
        zaraProduct.findElement(By.cssSelector(".card-body button:last-of-type")).click();
        Thread.sleep(5000);
        //wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("[routerlink*='cart']"))));
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase("ZARA COAT 3"));
        //anyMatch looks for the element if matches it returns Boolean value
        Assert.assertTrue(match);
        //if match has "true" value then test passses if "false" then the TC fails
        driver.findElement(By.cssSelector(".totalRow button")).click();

        //Using Action Class to send keys to dropdown
        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"india").build().perform();
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ta-results")));
        Thread.sleep(5000);
        driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
        driver.findElement(By.cssSelector(".action__submit")).click();
        String confirmMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        driver.close();


    }
}
