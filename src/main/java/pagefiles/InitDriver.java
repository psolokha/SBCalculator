package pagefiles;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class InitDriver {

    private static WebDriver driver;
    private static InitDriver instance;

    private InitDriver(){
        ScenarioProperties properties = ScenarioProperties.getInstance();
        if (System.getProperties().getProperty("browser").equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (System.getProperties().getProperty("browser").equals("chrome")){
            System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(properties.getProperty("timeout.pageLoad")), TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(properties.getProperty("timeout.global")), TimeUnit.SECONDS);
        driver.get(properties.getProperty("url"));
    }

    public static WebDriver getInstance() {
        if (instance == null) instance = new InitDriver();
        return driver;
    }

}
