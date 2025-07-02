package com.project1Test.TestComponents;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.project1Test.resources.ExtentReporterNG;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {
    ExtentTest test ;
    ExtentReports extent = ExtentReporterNG.getReporter();
    public void onTestStart(ITestResult result) {
       test = extent.createTest(result.getMethod().getMethodName());

    }

    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS,"Test Passed");
    }

    public void onTestFailure(ITestResult result) {
        String filePath=null;
        test.fail(result.getThrowable());
        //take screenshot and attach it to report
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        try {
            filePath = getScreenShot(result.getMethod().getMethodName(),driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        test.addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());

    }

    public void onFinish(ITestContext context) {
        extent.flush();

    }

}
