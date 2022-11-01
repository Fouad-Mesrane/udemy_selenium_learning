package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.abstractComponents.Base;

import java.util.List;

public class CartPage extends Base {


        WebDriver driver;
        public CartPage(WebDriver driver){
            super(driver);
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }

        @FindBy(css = ".cartSection h3")
        private List<WebElement> productTitles;

        @FindBy(css = ".totalRow button")
        WebElement checkoutBtn;

        public boolean verifyProductDisplay(String productName){
            Boolean match = productTitles.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));

            return match;
        }

        public PlaceOrderPage checkout(){
            checkoutBtn.click();

            return new PlaceOrderPage(driver);
        }
}
