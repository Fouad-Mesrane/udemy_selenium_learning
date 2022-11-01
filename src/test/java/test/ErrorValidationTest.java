package test;

import TestComponents.BaseTest;
import TestComponents.RetryFailedTests;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCataloguePage;

import java.io.IOException;
import java.util.List;

public class ErrorValidationTest extends BaseTest {

    @Test(groups = {"ErrorValidation"}, retryAnalyzer = RetryFailedTests.class)
    public void loginErrorValidation() throws InterruptedException, IOException {

        landingPage.loginApplication("fouad@gmail.com", "Test14dfd");

        Assert.assertEquals( landingPage.getErrorMessage(), "Incorrect email or password.");


    }


    @Test
    public void productErrorValidation() throws InterruptedException, IOException {


        String productName = "ZARA COAT 3";

        ProductCataloguePage productCatalogue = landingPage.loginApplication("fouad@gmail.com", "Test1234");

        List<WebElement> products = productCatalogue.getProductList();
        // iterate over product and add item by name into cart
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();

        Boolean match = cartPage.verifyProductDisplay("productName");
        Assert.assertFalse(match);




    }
}
