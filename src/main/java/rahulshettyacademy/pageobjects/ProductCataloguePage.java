package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.abstractComponents.Base;

import java.util.List;

public class ProductCataloguePage extends Base {
    WebDriver driver;

    public ProductCataloguePage(WebDriver driver) {
        // initialization
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // list of webElements
    @FindBy(css = ".col-lg-4")
    List<WebElement> products;
    @FindBy(css = ".ng-animating")
    WebElement spinner;

    By productBy = By.cssSelector(".col-lg-4");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.cssSelector("#toast-container");

    public List<WebElement> getProductList() {
        waitForElementToAppear(productBy);
        return products;
    }


    // selecting the product name by getting element text
    public WebElement getProductName(String productName) {
        WebElement prod = getProductList().stream().filter(product ->
                product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        return prod;
    }

    //


    public void addProductToCart(String productName) throws InterruptedException {
        WebElement prod = getProductName(productName);
        prod.findElement(addToCart).click();
        waitForElementToAppear(toastMessage);
        waitForElementToDisappear(spinner);

    }
}
