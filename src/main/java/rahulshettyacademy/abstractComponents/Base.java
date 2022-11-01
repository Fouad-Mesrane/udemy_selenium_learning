package rahulshettyacademy.abstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.OrdersPage;

import java.time.Duration;

public class Base {


        WebDriver driver;
        public Base(WebDriver driver){
            this.driver = driver;
        }

        @FindBy(xpath = "//button[@routerlink='/dashboard/cart']" )
        WebElement cartHeader;

        @FindBy(css = "[routerlink*='myorders']")
        WebElement orderHeader;

        public void waitForElementToAppear(By findBy){
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
        }

        public void waitForWebElementToAppear(WebElement findBy){
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(findBy));
        }
        public void waitForElementToDisappear(WebElement element) throws InterruptedException {

            Thread.sleep(1000);
        /*WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.invisibilityOf(element));*/

        }

        public CartPage goToCartPage(){
            cartHeader.click();

            return new CartPage(driver);
        }

        public OrdersPage goToOrdersPage(){
            orderHeader.click();
            return new OrdersPage(driver);
        }
}
