package com.project1Test;

import com.AbstractCompanents.AbstractCompanent;
import com.package1.ProductCatalog;
import com.project1Test.TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidationTest extends BaseTest {
    @Test
    public void loginErrorVal(){
        loginPage.loginApplication("abgg@gmail.com", "123456!A");
        Assert.assertEquals("Incorrect email or password.",loginPage.getErrorMessage());
    }
}
