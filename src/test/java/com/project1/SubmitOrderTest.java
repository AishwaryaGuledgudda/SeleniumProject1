package com.project1;

import com.package1.LoginPage;
import com.package1.ProductCatalog;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class SubmitOrderTest {
    public static void main(String[] args) throws InterruptedException {
        // Import WebDriverManager from github link its not available on MavenRepo
        WebDriverManager.chromedriver().setup();
        //Chrome driver will be automatically downloaded (latest version used on laptop)
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        LoginPage loginPage = new LoginPage(driver);
        //Login
        loginPage.goTo();
        loginPage.loginApplication("abg@gmail.com", "qwerty123456!A");
        //ProductCatalog
        ProductCatalog productCatalog = new ProductCatalog(driver);
        List<WebElement>products = productCatalog.getProductList();
        productCatalog.getProductByName("ZARA COAT 3");
        productCatalog.addProductToCart("ZARA COAT 3");
        //AbstractCompanent
        //calling the method using child object
        productCatalog.goToCartPage();
       // driver.quit();
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
