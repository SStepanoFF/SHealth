package Pages.RFP_Form;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * Created by sergii.stepanov on 10/08/2015.
 */
public class ProposedPricePageRFP extends MainPageRFP {

    public ProposedPricePageRFP(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOf(proposedPrice));
    }

    @FindBy (css = "input[title='Proposed Price.']")
    private WebElement proposedPrice;
    @FindBy (xpath = "//input[@title='Preconstruction Services (Not-to-Exceed Amount):']")
    private WebElement preconstrServices;
    @FindBy (xpath = ".//*[@id='tblAGR_BILL_PRECONSTR_STAGE']//td[@class='sutterTd sutterTextTd ']//input")
    private List<WebElement> preconstrStageTextList;
    @FindBy (xpath = ".//*[@id='tblAGR_BILL_PRECONSTR_STAGE']//td[@class='sutterTd sutterNumTd']//input")
    private List<WebElement> preconstrStageNumbList;
    @FindBy (xpath = ".//*[@id='tblAGR_BILL_CONSTR_STAGE']//td[@class='sutterTd sutterTextTd ']//input")
    private List<WebElement> constrStageTextList;
    @FindBy (xpath = ".//*[@id='tblAGR_BILL_CONSTR_STAGE']//td[@class='sutterTd sutterNumTd']//input")
    private List<WebElement> constrStageNumbList;
    @FindBy (xpath = "//table[@id='tblexhibit_3a']//td[@class='sutterTd sutterTextTd ']//input")
    private List<WebElement> constractServList;
    @FindBy (xpath = "//table[@id='tblexhibit_3a']//td[@class='sutterTd sutterNumTd']//input")
    private List<WebElement> constrRateAndHrsList;
    @FindBy (xpath = ".//*[@id='tblAGREED_EQU_RATES']//td[@class='sutterTd sutterTextTd ']//input")
    private List<WebElement> equipmentList;
    @FindBy (xpath = ".//*[@id='tblAGREED_EQU_RATES']//td[@class='sutterTd sutterNumTd']//input")
    private List<WebElement> equipmentNumbList;
    @FindBy (xpath = "//input[contains(@title,'%')]")
    private List<WebElement> proposedGmpList;
    @FindBy (xpath = "//input[contains(@title,'a.')]")
    private WebElement aGMP;
    @FindBy (xpath = "//input[contains(@title,'d.')]")
    private WebElement dMarckUp;
    @FindBy (xpath = "//input[contains(@title,'iv.')]")
    private WebElement ivGMP;

    public void fillProposedPricePage(String text){
        sendKeyIfEquals(proposedPrice, "$0.00", "7");
        preconstrServices.sendKeys(text);
        plusBtnClick();
        fillText(preconstrStageTextList, text);
        fillTextIfEquals(preconstrStageNumbList, "$0.00", "7");
        fillText(constrStageTextList, text);
        fillTextIfEquals(constrStageNumbList, "$0.00", "7");
        fillTextIfEquals(constractServList, "", text);
        for (WebElement element: constrRateAndHrsList){
            if (element.isEnabled()){
                sendKeyIfEquals(element, "$0.00", "9");
                sendKeyIfEquals(element, "0", "7");
            }
        }
        uploadFile(text);
        fillText(equipmentList, text);
        fillTextIfEquals(equipmentNumbList, "$0.00", "7");
        fillTextIfEquals(proposedGmpList, "", "11");
        sendKeyIfEquals(aGMP, "", "11");
        sendKeyIfEquals(dMarckUp, "", "11");
        sendKeyIfEquals(ivGMP, "", "11");
    }
}
