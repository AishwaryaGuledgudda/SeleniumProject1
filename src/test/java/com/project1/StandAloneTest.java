package com.project1;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        driver.findElement(By.id("userEmail")).sendKeys("abg@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("qwerty123456!A");
        driver.findElement(By.id("login")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement zaraProduct =  products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null); // when each product is iterated it is stored in product variable
        // stream helps to iterate through the list of products
        assert zaraProduct != null;
        zaraProduct.findElement(By.cssSelector(".card-body button:last-of-type")).click();
       Thread.sleep(5000);
        //wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("[routerlink*='cart']"))));
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
       // driver.quit();
    }
}
