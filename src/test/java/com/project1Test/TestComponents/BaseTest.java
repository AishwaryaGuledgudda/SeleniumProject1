package com.project1Test.TestComponents;

import com.package1.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
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

    @AfterMethod
    public void tearDown(){
        driver.close();
    }
    public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir")+ "//reports//" + testCaseName + ".png");
        FileUtils.copyFile(source,file);
        return System.getProperty("user.dir" + "//reports//" + testCaseName + ".png");
    }
}
