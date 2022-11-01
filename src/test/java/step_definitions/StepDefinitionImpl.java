package step_definitions;

import TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import rahulshettyacademy.pageobjects.*;

import java.io.IOException;
import java.util.List;

public class StepDefinitionImpl extends BaseTest {
    public LandingPage landingPage;
    ProductCataloguePage productCatalogue;
    ConfirmationPage confirmationPage;
    @Given("I landed on Ecommerce Page")
    public void i_landed_on_Ecommerce_Page() throws IOException {
          landingPage =  launchApplication();
    }
    @Given("^Logged in with username (.+) and password (.+)$")
    public void login(String email, String password){
       productCatalogue   = landingPage.loginApplication(email,password);
    }

    @When("^I add product (.+) from cart$")
    public void addProductToCart(String productName) throws InterruptedException {
        List<WebElement> products = productCatalogue.getProductList();
        // iterate over product and add item by name into cart
        productCatalogue.addProductToCart( productName);
    }

    @And("^Checkout (.+) and submit the order$")
    public void checkout_submitOrder(String productName) throws InterruptedException {
        CartPage cartPage = productCatalogue.goToCartPage();
        Boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);
        PlaceOrderPage placeOrderPage = cartPage.checkout();

        placeOrderPage.selectCountry("United States");
        confirmationPage = placeOrderPage.submitOrder();

    }

    @Then("{string} is displayed")
    public void confiramtionMessage(String string){

        boolean confirm = confirmationPage.isDisplayedMessage(string);
        Assert.assertTrue(confirm);
        driver.quit();
    }

    @Then("^\"([^\"]*)\" message is displayed$")
    public void something_message_is_displayed(String str) throws Throwable {
        Assert.assertEquals( landingPage.getErrorMessage(), str);

        driver.quit();
    }



}
