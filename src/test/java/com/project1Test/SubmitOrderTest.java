package com.project1Test;

import com.package1.*;
import com.project1Test.TestComponents.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    @Test(dataProvider = "getData")
            public void submitOrder(String email, String password, String productName) throws IOException, InterruptedException {
        // Import WebDriverManager from github link its not available on MavenRepo
        //*****Login*****//
        ProductCatalog productCatalog = loginPage.loginApplication(email, password);
        //*****ProductCatalog*****//
        List<WebElement>products = productCatalog.getProductList();
        productCatalog.getProductByName(productName);
        productCatalog.addProductToCart(productName);
        //*****AbstractCompanent*****//
        //calling the method using child object
        CartPage cartPage = productCatalog.goToCartPage();
        //*****CartPage*****//
        Boolean match = cartPage.verifyProductDisplaying();
        //anyMatch looks for the element if matches it returns Boolean value
        Assert.assertTrue(match);
        CheckOutPage checkOutPage = cartPage.goToCheckOut();
        //Using Action Class to send keys to dropdown
        //*****CheckOutPage*****//
        checkOutPage.selectCountry("India");
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ta-results")));
        Thread.sleep(5000);
        ConfirmationPage confirmationPage = checkOutPage.submitOrder();
        //*****ConfirmationPage*****//
        String confirmMsg = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    //selenium makes sure that the submitOrder() method is ran before running this method dependsOnMethods = "submitOrder"
    @Test(dependsOnMethods = "submitOrder")
    public void orderHistoryTest() throws InterruptedException {
        ProductCatalog productCatalog = loginPage.loginApplication("abg@gmail.com", "qwerty123456!A");
        OrderPage orderPage = productCatalog.goToOrderPage();
        Assert.assertTrue(orderPage.verifyProductDisplaying());
    }

    @DataProvider
    public Object[][] getData(){
        return new Object[][] {{"abg@gmail.com","qwerty123456!A","ZARA COAT 3"}};
    }

}
