import io.qameta.allure.Step;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import pagefiles.InitDriver;
import pagefiles.pages.CalculatorPage;

public class TestFeatures {


    @Step("First test")
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
        Assert.assertFalse(calc.checkRate(11.));

    }

    @AfterClass
    public static void closeDriver(){
        InitDriver.getInstance().close();
    }

}
