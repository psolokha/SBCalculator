package cucumber;

import cucumber.api.java.After;
import cucumber.api.java.ru.Дано;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pagefiles.InitDriver;
import pagefiles.pages.CalculatorPage;

public class ScenarioTest {

    private CalculatorPage calc;
    private WebDriver driver = InitDriver.getInstance();

    @After
    public void afterClass(){
        driver.quit();
    }

    @Дано("Открыть сайт \"Сбербанк для физических лиц\"")
    public void openSite(){
        calc = new CalculatorPage();
//        calc.closeCookieWarning();
    }

    @Дано("В верхнем меню навестись на \"(.*)\" - дождаться открытия выпдающего меню и выбрать \"(.*)\"")
    public void openIpoteka( String menuItem, String submenuItem) {
        calc.goToMenu(menuItem, submenuItem);
    }

    @Дано("Перейти к калькулятору")
    public void goToCalc() {
        calc.switchToCalc();
    }

    @Дано("Заполнить полe \"Стоимость недвижмости\" - (.*)")
    public void fillCost(String cost){
        calc.setFullPrice(Integer.parseInt(cost.substring(0, cost.length()-1).replace(" ", "")));
    }

    @Дано("Заполнить полe \"Первоначальнй взнос\" - (.*)")
    public void fillFirstPayment(String payment){
        calc.setFirstPayment(Integer.parseInt(payment.substring(0, payment.length()-1).replace(" ", "")));
    }

    @Дано("Заполнить полe \"Срок кредита\" - (.*)")
    public void fillTerm(String years){
        calc.setTermPayout(Integer.parseInt(years.substring(0, years.length()-3).replace(" ", "")));
    }

    @Когда("Снять галочкку - \"Eсть зарплатная карта сбербанка\"")
    public void tickHaveCard(){
        calc.tickPaidToSBCard();
    }

    @Тогда("Дождаться появляения \"Есть возможность подтвержить доход справкой\"")
    public void waitForConfirmation(){
        Assert.assertTrue(calc.isVisibleConfirmEarnings());
    }

    @Дано("Поставить галочку \"Молодая семья\"")
    public void youngFamily(){
        calc.tickYoungFamily();
    }

    @Тогда("Проверить значение поля - Сумма кредита (.*)")
    public void checkFieldSum(String value) {
        Assert.assertTrue(calc.checkTotalPriceIs(Integer.parseInt(value.substring(0, value.length()-1).replace(" ", ""))));
    }

    @Тогда("Проверить значение поля - Ежемесячный платеж (.*)")
    public void checkFieldMonth(String value){
        Assert.assertTrue(calc.checkMonthPayment(Integer.parseInt(value.substring(0, value.length()-1).replace(" ", ""))));
    }
    @Тогда("Проверить значение поля - Необходимый доход (.*)")
    public void checkFieldIncome(String value) {
        Assert.assertTrue(calc.checkRequiredIncome(Integer.parseInt(value.substring(0, value.length()-1).replace(" ", ""))));
    }

    @Тогда("Проверить значение поля - Процентная ставка (.*) - тут ошибка")
    public void checkFieldRate(String value){
        Assert.assertFalse(calc.checkRate(Double.parseDouble(value.substring(0,value.length()-1))));
    }

}
