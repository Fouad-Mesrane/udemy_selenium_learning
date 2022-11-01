package test;

import TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rahulshettyacademy.pageobjects.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap <String,String> input) throws InterruptedException {


        ProductCataloguePage productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

        List<WebElement> products = productCatalogue.getProductList();
        // iterate over product and add item by name into cart
        productCatalogue.addProductToCart( input.get("product"));
        CartPage cartPage = productCatalogue.goToCartPage();

        Boolean match = cartPage.verifyProductDisplay(input.get("product"));
        Assert.assertTrue(match);
        PlaceOrderPage placeOrderPage = cartPage.checkout();

        placeOrderPage.selectCountry(input.get("country"));
        ConfirmationPage confirmationPage = placeOrderPage.submitOrder();
        boolean confirm = confirmationPage.isDisplayedMessage("THANKYOU FOR THE ORDER." );
        Assert.assertTrue(confirm);



    }


    // verify if product is in orders page
    @Test(dependsOnMethods = {"submitOrder"}, dataProvider = "getData")
    public void orderHistoryTest(HashMap<String,String> input){
        ProductCataloguePage productCatalogue = landingPage.loginApplication(input.get("email"),input.get("password"));
        OrdersPage ordersPage = productCatalogue.goToOrdersPage();
        Assert.assertTrue( ordersPage.verifyOrderDisplay(input.get("product")));

    }

    @DataProvider
    public Object [][] getData() throws IOException {



        List<HashMap<String,String>> data = getJasonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\data\\PurchaseOrder.json");

        return new Object[][] {  {data.get(0)}, {data.get(1)}  };
    }
}
