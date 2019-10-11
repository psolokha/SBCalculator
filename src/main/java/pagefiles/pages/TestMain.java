package pagefiles.pages;

public class TestMain {

    public static void main(String... args) {
        CalculatorPage calc = new CalculatorPage();
        calc.driver.get("https://www.sberbank.ru/ru/person/credits/home/buying_complete_house");
        calc.switchToCalc();
        calc.setFullPrice(5180000);
        calc.setFirstPayment(3058000);
        calc.setTermPayout(30);




        calc.driver.close();
    }
}
