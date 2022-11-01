package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.abstractComponents.Base;

public class PlaceOrderPage extends Base {

    WebDriver driver;

    public PlaceOrderPage(WebDriver driver) {

        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(css = ".text-validated:first-child")
    WebElement selectCountryField;

    @FindBy(xpath = "//section[@class = 'ta-results list-group ng-star-inserted']/button[1]")
    WebElement selectCountry;

    @FindBy(xpath = "//a[@class = 'btnn action__submit ng-star-inserted']")
    WebElement placeOrder;



    By dropDownBtn = By.cssSelector(".ta-results");

    public void selectCountry(String country) {
        Actions actions = new Actions(driver);
        actions.sendKeys(selectCountryField, country).build().perform();

        waitForElementToAppear(dropDownBtn);
        selectCountry.click();


    }
    public ConfirmationPage submitOrder() throws InterruptedException {
        placeOrder.click();

        return new ConfirmationPage(driver);
    }
}
