package Pages.RFP_Form;

import org.openqa.selenium.WebDriver;

/**
 * Created by sergii.stepanov on 14/08/2015.
 */
public class PreconstructionAndBaselineSchedulesRFP extends MainPageRFP {

    public PreconstructionAndBaselineSchedulesRFP(WebDriver driver) {
        super(driver);
        waitForAJAXfinished();
    }

    public void fillPreconstructionAndBaselineSchedulesPage(String text){
        uploadFile(text);
    }
}
