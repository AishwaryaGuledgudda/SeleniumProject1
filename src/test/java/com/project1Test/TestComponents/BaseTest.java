package com.project1Test.TestComponents;

import com.package1.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
   public WebDriver driver;
   public LoginPage loginPage;

    public WebDriver initializeDriver() throws IOException {
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream("/Users/aishwaryaguledgudda/Projects/SeleniumProject1/src/test/java/com/project1Test/resources/GlobalData.properties");
        properties.load(fis);
        String browserName = properties.getProperty("browser");

        if(browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            //Chrome driver will be automatically downloaded (latest version used on laptop)
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            //invoke firefox driver
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    @BeforeMethod
    public LoginPage launchApp() throws IOException {
        driver= initializeDriver();
        loginPage = new LoginPage(driver);
        loginPage.goTo();
        return loginPage;
    }
}
