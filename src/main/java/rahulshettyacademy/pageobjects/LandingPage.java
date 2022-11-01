package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.abstractComponents.Base;

public class LandingPage extends Base {


        WebDriver driver;
        public LandingPage(WebDriver driver){
            super(driver);
            // initialization
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }


        // PageFactory
        @FindBy(id = "userEmail")
        WebElement userEmail;

        @FindBy(id = "userPassword")
        WebElement pwd;

        @FindBy(id = "login")
        WebElement submit;
        @FindBy(css = "[class*='flyInOut']" )
        WebElement errorMessage;


        public ProductCataloguePage loginApplication(String email, String password){
            userEmail.sendKeys(email);
            pwd.sendKeys(password);
            submit.click();

            return new ProductCataloguePage(driver);
        }

        public String getErrorMessage(){
            waitForWebElementToAppear(errorMessage);
            return errorMessage.getText();
        }

        public void goTo(){
            driver.get("https://rahulshettyacademy.com/client");
        }
}
