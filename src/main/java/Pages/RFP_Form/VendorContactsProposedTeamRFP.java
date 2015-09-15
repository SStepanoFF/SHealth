package Pages.RFP_Form;

import javafx.scene.web.WebView;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by sergii.stepanov on 14/08/2015.
 */
public class VendorContactsProposedTeamRFP extends MainPageRFP {

    public VendorContactsProposedTeamRFP(WebDriver driver) {
        super(driver);
        waitForAJAXfinished();
    }

    @FindBy (id = "goSubmitPage")
    private WebElement submitBtn;

    public void fillVendorContactsProposedTeamTab(String text){
        uploadFile(text);
        submitBtn.click();
    }
}
