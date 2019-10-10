package pagefiles;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class InitDriver {

    private static WebDriver driver;
    private static InitDriver instance;

    private InitDriver(){
        ScenarioProperties properties = ScenarioProperties.getInstance();
        System.setProperty(properties.getProperty("driver"), properties.getProperty("path"));
        driver = new ChromeDriver();
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
