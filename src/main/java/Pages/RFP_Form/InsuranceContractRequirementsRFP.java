package Pages.RFP_Form;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * Created by sergii.stepanov on 14/08/2015.
 */
public class InsuranceContractRequirementsRFP extends MainPageRFP {

    public InsuranceContractRequirementsRFP(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOf(checkBxList.get(0)));
    }

    @FindBy (css = "input[class='cCheckbox']")
    private List<WebElement> checkBxList;
    @FindBy (css = "input[class='inputSingleLine']")
    private List<WebElement> limitsList;

    public void fillInsuranceContractRequirementsRFP(){
        for (WebElement element: checkBxList){
            if(!element.isSelected()){
                element.click();
            }
        }
        fillTextIfEquals(limitsList, "", "77");
    }

}
