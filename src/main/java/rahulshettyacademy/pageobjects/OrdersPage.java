package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.abstractComponents.Base;

import java.util.List;

public class OrdersPage extends Base {


        WebDriver driver;
        public OrdersPage(WebDriver driver){
            super(driver);
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }

        @FindBy(css = "tr td:nth-child(3)")
        List<WebElement> productNames;

        public boolean verifyOrderDisplay(String productName){
            Boolean match = productNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
            return match;
        }
}
