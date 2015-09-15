package Pages.RFP_Form;


import framework.GlobalVariables;
import org.openqa.selenium.By;
import webDriver.DriverOperations;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import java.util.List;

/**
 * Created by sergii.stepanov on 05/08/2015.
 */
public class MainPageRFP extends DriverOperations{

    public MainPageRFP(WebDriver driver) {
        super(driver);
    }

    @FindBy (id = "goNextPage")
    protected WebElement nextPageBtn;

    //region Common page's elements
    @FindBy (css = "input[type='file']")
    private List<WebElement> uploadFileBtn;
    @FindBy (css = "input[title='Document name']")
    private List<WebElement> docName;
    @FindBy (css = "input[title='Date']")
    private List<WebElement> docDate;
    @FindBy (css = "input[title='Provide comments on the file']")
    private List<WebElement> commentToFile;
    @FindBy (xpath = "//input[@value=' + ']")
    private List<WebElement> plusBtnList;
    //endregion

    //region Tabs buttons
    @FindBy (xpath = "//li[@class='tub2']//*")
    private WebElement projScopeAndDescrTab;
    @FindBy (xpath = "//li[@class='tub3']//*")
    private WebElement scopeOfServicesTab;
    @FindBy (xpath = "//li[@class='tub4']//*")
    private WebElement proposedPriceTab;
    @FindBy (xpath = "//li[@class='tub5']//*")
    private WebElement preconstrAndBaselineSchedulesTab;
    @FindBy (xpath = "//li[@class='tub6']//*")
    private WebElement insuranceAndContractReqTab;
    @FindBy (xpath = "//li[@class='tub7']//*")
    private WebElement vendorContactAndProposedTeamTab;
    //endregion

    public void uploadFile(String text){
        if (uploadFileBtn.size()!=0) {
            for (WebElement element : uploadFileBtn) {
                element.sendKeys(GlobalVariables.FILE_PATH + "Owners program.csv");
                waitForAJAXfinished();
            }
        }
        fillTextIfEquals(docName, "", text);
        fillTextIfEquals(docDate, "", getCurrentDate());
        if (commentToFile.size()!=0) {
            fillText(commentToFile, text);
        }
    }
    public void plusBtnClick(){
        for (WebElement element:plusBtnList){
            element.click();
            waitForAJAXfinished();
        }
    }

    public void fillRFPForm(String text){
        if(!isElementPresent(By.xpath("//li[@class='tub2']//*"))){
            throw new RuntimeException("This form was submitted!");
        }

        CoverPageRFP coverPageRFP= new CoverPageRFP(driver);
        coverPageRFP.fillCoverPage(text);

        ProjectScopeAndDescriptionPageRFP rfpProjectScopeAndDescriptionPage=openProjScopeAndDescrTab();
        rfpProjectScopeAndDescriptionPage.fillRFPProjectScopeAndDescriptionPage(text);

        ScopeOfServicesPageRFP scopeOfServicesPage= openScopeOfServicesTab();
        scopeOfServicesPage.fillScopeOfServicesPage(text);

        ProposedPricePageRFP proposedPricePageRFP=openProposedPricePage();
        proposedPricePageRFP.fillProposedPricePage(text);

        PreconstructionAndBaselineSchedulesRFP preconstructionAndBaselineSchedulesRFP=openPreconstructionAndBaselineSchedulesRFP();
        preconstructionAndBaselineSchedulesRFP.fillPreconstructionAndBaselineSchedulesPage(text);

        InsuranceContractRequirementsRFP insuranceContractRequirementsRFP=openInsuranceContractRequirementsRFP();
        insuranceContractRequirementsRFP.fillInsuranceContractRequirementsRFP();

        VendorContactsProposedTeamRFP vendorContactsProposedTeamRFP=openVendorContactsProposedTeamtab();
        vendorContactsProposedTeamRFP.fillVendorContactsProposedTeamTab(text);
        waitForAJAXfinished();
    }

    private ProjectScopeAndDescriptionPageRFP openProjScopeAndDescrTab(){
        projScopeAndDescrTab.click();
        return new ProjectScopeAndDescriptionPageRFP(driver);
    }

    private ScopeOfServicesPageRFP openScopeOfServicesTab(){
        scopeOfServicesTab.click();
        return  new ScopeOfServicesPageRFP(driver);
    }

    private ProposedPricePageRFP openProposedPricePage(){
        proposedPriceTab.click();
        return new ProposedPricePageRFP(driver);
    }

    private PreconstructionAndBaselineSchedulesRFP openPreconstructionAndBaselineSchedulesRFP(){
        preconstrAndBaselineSchedulesTab.click();
        return new PreconstructionAndBaselineSchedulesRFP(driver);
    }

    private InsuranceContractRequirementsRFP openInsuranceContractRequirementsRFP(){
        insuranceAndContractReqTab.click();
        return new InsuranceContractRequirementsRFP(driver);
    }

    private VendorContactsProposedTeamRFP openVendorContactsProposedTeamtab(){
        vendorContactAndProposedTeamTab.click();
        return new VendorContactsProposedTeamRFP(driver);
    }
}
