package pagefiles.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class CalculatorPage extends AbstractIndexPage{


    @FindBy(xpath = "//input[@id = 'estateCost']")
    private WebElement totalPriceField;

    @FindBy(xpath = "//input[@id = 'initialFee']")
    private WebElement firstPaymentField;

    @FindBy(xpath = "//input[@id = 'creditTerm']")
    private WebElement termField;

    @FindBy(xpath = "//span[@data-test-id = 'amountOfCredit']")
    private WebElement confirmTotalPrice;

    @FindBy(xpath = "//span[@data-test-id = 'monthlyPayment']")
    private WebElement checkMonthPayment;

    @FindBy(xpath = "//span[@data-test-id = 'requiredIncome']")
    private WebElement checkRequiredIncome;

    @FindBy(xpath = "//span[@data-test-id = 'rate']")
    private WebElement checkRate;

    @FindBy(xpath = "//input[@data-test-id = 'paidToCard']/ancestor::label/span[@class = 'dcCalc_switch__control']")
    private WebElement paidToSBCardCheckbox;

    @FindBy(xpath = "//div[contains(text(), 'Есть возможность подтвердить доход справкой')]")
    private WebElement confirmEarningsCheckbox;

    @FindBy(xpath = "//input[@data-test-id = 'youngFamilyDiscount']/ancestor::label/span[@class = 'dcCalc_switch__control']")
    private WebElement youngFamilyCheckbox;

    public void switchToCalc() {
        driver.switchTo().frame(0);
    }

    public void tickYoungFamily() {
        String tmpClass = driver.findElement(By.xpath("//input[@data-test-id = 'youngFamilyDiscount']/ancestor::label")).getAttribute("class");
        String tmpValue = checkRate.getText();
        waitForElemenIsClickable(youngFamilyCheckbox);
        youngFamilyCheckbox.click();
        new Actions(driver).pause(500);
        waitForChanges(tmpValue, checkRate);
        new Actions(driver).pause(500);
        if (tmpClass.equals(driver.findElement(By.xpath("//input[@data-test-id = 'youngFamilyDiscount']/ancestor::label")).getAttribute("class"))) tickYoungFamily();
    }

    public void tickPaidToSBCard() {
        String tmpClass = driver.findElement(By.xpath("//input[@data-test-id = 'paidToCard']/ancestor::label")).getAttribute("class");
        String tmpValue = checkRate.getText();
        waitForElemenIsClickable(paidToSBCardCheckbox);
        paidToSBCardCheckbox.click();
        new Actions(driver).pause(500);
        waitForElementVisibility(confirmEarningsCheckbox);
        waitForChanges(tmpValue, checkRate);
        new Actions(driver).pause(500);
        if (tmpClass.equals(driver.findElement(By.xpath("//input[@data-test-id = 'paidToCard']/ancestor::label")).getAttribute("class"))) tickPaidToSBCard();
    }

    public boolean isVisibleConfirmEarnings() {
        return confirmEarningsCheckbox.isDisplayed();
    }



    public void setFullPrice(int price) {
        String tmpValue = confirmTotalPrice.getText();
        totalPriceField.clear();
        totalPriceField.sendKeys(String.valueOf(price));
        new Actions(driver).pause(500);
        waitForChanges(tmpValue, confirmTotalPrice);
        new Actions(driver).pause(500);
        if(Integer.parseInt(totalPriceField.getAttribute("value").substring(0, totalPriceField.getAttribute("value").length()-1).replace(" ", "")) != price) setFullPrice(price);
    }

    public void setFirstPayment(int payment) {
        String tmpValue = confirmTotalPrice.getText();
        firstPaymentField.clear();
        firstPaymentField.sendKeys(String.valueOf(payment));
        new Actions(driver).pause(500);
        waitForChanges(tmpValue, confirmTotalPrice);
        new Actions(driver).pause(500);
        if(Integer.parseInt(firstPaymentField.getAttribute("value").substring(0, firstPaymentField.getAttribute("value").length()-1).replace(" ", "")) != payment) setFirstPayment(payment);
    }

    public void setTermPayout(int term) {
        String tmpValue = confirmTotalPrice.getText();
        termField.clear();
        termField.sendKeys(String.valueOf(term));
        new Actions(driver).pause(500);
        waitForChanges(tmpValue, confirmTotalPrice);
        new Actions(driver).pause(500);
        if(Integer.parseInt(termField.getAttribute("value").substring(0, termField.getAttribute("value").length()-3).replace(" ", "")) != term) setTermPayout(term);
    }

    public boolean checkTotalPriceIs(int value) {
        new Actions(driver).pause(500);
        return Integer.parseInt(confirmTotalPrice.getText().substring(0, confirmTotalPrice.getText().length()-1).replace(" ", "")) == value;
    }

    public boolean checkMonthPayment(int value) {
        new Actions(driver).pause(500);
        return Integer.parseInt(checkMonthPayment.getText().substring(0, checkMonthPayment.getText().length()-1).replace(" ", "")) == value;
    }

    public boolean checkRequiredIncome(int value) {
        new Actions(driver).pause(500);
        return Integer.parseInt(checkRequiredIncome.getText().substring(0, checkRequiredIncome.getText().length()-1).replace(" ", "")) == value;
    }

    public boolean checkRate(Double value) {
        new Actions(driver).pause(500);
        return Double.parseDouble(checkRate.getText().substring(0, checkRate.getText().length()-1).replace(" ", "").replace(',', '.')) == value;
    }

}