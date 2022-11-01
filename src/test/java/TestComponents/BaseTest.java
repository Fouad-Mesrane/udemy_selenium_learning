package TestComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import rahulshettyacademy.pageobjects.LandingPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {

    public  WebDriver driver;
    public LandingPage landingPage;

    public  WebDriver initilizeDriver() throws IOException {

        //properties class

        Properties prop = new Properties();
        String relPath = System.getProperty("user.dir");

        FileInputStream fis = new FileInputStream(relPath + "\\src\\main\\resources\\globalData.properties");
        prop.load(fis);
        String browserName = System.getProperty("browser")!= null ? System.getProperty("browser") : prop.getProperty("browser");


        if (browserName.contains("chrome")) {
            ChromeOptions options = new ChromeOptions();

            WebDriverManager.chromedriver().setup();
            if (browserName.contains("headless")) {
                options.addArguments("headless");
            }
            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1440,900)); // fullscreen

        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();

        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public  LandingPage launchApplication() throws IOException {
        driver = initilizeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();

    }


    // json reader method
    public List<HashMap<String,String>> getJasonDataToMap(String filePath) throws IOException {

        //read json to string
        String jsonContent =  FileUtils.readFileToString(new File(filePath),
                StandardCharsets.UTF_8);

        //convert string to hashmap
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});

        return data;

    }

    public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String fullpath = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
        File file = new File(fullpath);
        FileUtils.copyFile(source,file);
        return fullpath ;
    }
}
