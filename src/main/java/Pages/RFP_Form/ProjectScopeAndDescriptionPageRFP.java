package Pages.RFP_Form;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by sergii.stepanov on 05/08/2015.
 */
public class ProjectScopeAndDescriptionPageRFP extends MainPageRFP {

    public ProjectScopeAndDescriptionPageRFP(WebDriver driver) {
        super(driver);
        waitForAJAXfinished();
    }

    @FindBy (xpath = ".//*[@id='tblPROGRAM_SUMMARY']//td[@class='sutterTd sutterTextTd ']//input")
    private List<WebElement> progSummaryTextFieldsList;
    @FindBy (xpath = ".//*[@id='tblPROGRAM_SUMMARY']//td[@class='sutterTd sutterNumTd']//input")
    private List<WebElement> progSummaryNumbFieldsList;
    @FindBy (xpath = "//iframe[@class='sutterWYSIWYG']")
    private List<WebElement> progSummaryCommentsList;
    @FindBy (xpath = "//table[@id='tblSCOPE_OF_SERVICE']//input[@type='text']")
    private List<WebElement> scopeOfServiceList;
    @FindBy (xpath = "//table[@id='tblASSUMPTIONS_AND_QUALIFICATIONS']//input[@type='text']")
    private List<WebElement> assumptAndQualificList;
    @FindBy (xpath = "//table[@id='tblEXPECTED_SCHEDULE']//td[@class='sutterTd sutterTextTd ']//input")
    private List<WebElement> timelineTextList;
    @FindBy (xpath = "//table[@id='tblEXPECTED_SCHEDULE']//td[@class='sutterTd sutterDateTd']//input")
    private List<WebElement> timelineDateList;

    public void fillRFPProjectScopeAndDescriptionPage(String text){
        plusBtnClick();
        for (int i=progSummaryTextFieldsList.size()-1;i>=progSummaryTextFieldsList.size()-4;i--){
            progSummaryTextFieldsList.get(i).sendKeys(text);
        }
        for (int i=progSummaryNumbFieldsList.size()-1;i>=progSummaryNumbFieldsList.size()-2;i--){
            progSummaryNumbFieldsList.get(i).sendKeys("7");
        }
        progSummaryCommentsList.get(progSummaryCommentsList.size()-1).sendKeys(text);
        waitForAJAXfinished();
        uploadFile(text);
        scopeOfServiceList.get(scopeOfServiceList.size()-1).sendKeys(text+" scope");
        assumptAndQualificList.get(assumptAndQualificList.size()-1).sendKeys(text);
        timelineTextList.get(timelineTextList.size()-1).sendKeys(text);
        for (int i=timelineDateList.size()-1;i>=timelineDateList.size()-2;i--){
            timelineDateList.get(i).sendKeys(getCurrentDate());
        }
    }
}
