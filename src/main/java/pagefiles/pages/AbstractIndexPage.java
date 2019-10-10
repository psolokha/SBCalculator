package pagefiles.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pagefiles.InitDriver;

public abstract class AbstractIndexPage {

    WebDriver driver;

    AbstractIndexPage() {
        driver = InitDriver.getInstance();
        PageFactory.initElements(driver, this);
    }

    public void goToMenu(String menuItem, String submenuItem) {
        WebElement menuButton = driver.findElement(By.xpath("//div[@role = 'navigation']//button/span[contains(text(), '" + menuItem + "')]"));
        WebElement submenuButton = driver.findElement(By.xpath("//div[@role = 'navigation']//button/span[contains(text(), '" + menuItem + "')]/ancestor::li//a[contains(text(), '" + submenuItem + "')]"));
        new Actions(driver).moveToElement(menuButton).click().moveToElement(submenuButton).click().build().perform();
    }

    void waitForElementVisibility(WebElement element) {
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(element));
    }

    void waitForElemenIsClickable(WebElement element) {
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(element));
    }

    public void closeCookieWarning() {
        WebElement cookieWarning = driver.findElement(By.xpath("//a[contains(@class, 'cookie')]"));
        waitForElementVisibility(cookieWarning);
        waitForElemenIsClickable(cookieWarning);
        cookieWarning.click();
    }

    void waitForChanges(String value, WebElement element) {
        new WebDriverWait(driver, 3).until(webDriver -> !value.equals(element.getText()));
    }

}
