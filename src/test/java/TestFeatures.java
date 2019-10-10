import org.junit.Assert;
import org.junit.Test;
import pagefiles.pages.CalculatorPage;

public class TestFeatures {

    @Test
    public void simpleTest() {

        CalculatorPage calc = new CalculatorPage();
        calc.closeCookieWarning();
        calc.goToMenu("Ипотека", "готовое жильё");
        calc.switchToCalc();
        calc.setFullPrice(5180000);
        calc.setFirstPayment(3058000);
        calc.setTermPayout(30);
        calc.tickPaidToSBCard();
        Assert.assertTrue(calc.isVisibleConfirmEarnings());
        calc.tickYoungFamily();
        Assert.assertTrue(calc.checkTotalPriceIs(2122000));
        Assert.assertTrue(calc.checkMonthPayment(17998));
        Assert.assertTrue(calc.checkRequiredIncome(29997));
        Assert.assertTrue(calc.checkRate(11.));

    }

}
