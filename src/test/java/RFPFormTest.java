import Pages.RFP_Form.MainPageRFP;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import setup.BaseTest;
import utils.DataBase;

/**
 * Created by sergii.stepanov on 06/08/2015.
 */
public class RFPFormTest extends BaseTest {

    private MainPageRFP mainPageRFP;

    @BeforeClass
    private void setUp() {
        mainPageRFP = new MainPageRFP(driver);
        driver.navigate().to("http://sutterhealth.t1.ssstest.com/agreement/1059/835/05ad8269e13c4bf884757c98bcba05cb");
    }

    @Test
    private void fillRFPFormTest(){
        mainPageRFP.fillRFPForm("vendor");
    }
}
