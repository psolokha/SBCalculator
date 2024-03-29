package pagefiles.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

//    void scrollTo(WebElement element) {
//        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
//    }

//       public void scrollDown() {
//           ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,30)");
//        }

//    void goTo(WebElement element) {
//        new Actions(driver).moveToElement(element).pause(100).build().perform();
//    }

    void goTo(WebElement element) {
        new Actions(driver).moveToElement(element).pause(200).perform();
    }

    public void closeCookieWarning() {
        checkOtherWindow();
        WebElement cookieWarning = driver.findElement(By.xpath("//a[contains(@class, 'cookie')]"));
        waitForElementVisibility(cookieWarning);
        waitForElemenIsClickable(cookieWarning);
        if (cookieWarning.isDisplayed()) cookieWarning.click();
        else closeCookieWarning();
    }

    void waitForChanges(String value, WebElement element) {
        new WebDriverWait(driver, 5).until(webDriver -> !value.equals(element.getText()));
    }

    private void checkOtherWindow() {
        if (driver.getWindowHandles().size() > 1) driver.close();

    }

}
